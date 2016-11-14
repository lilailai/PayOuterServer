package com.imobpay.base.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.dao.TbvFixMerchantSafeDao;
import com.imobpay.base.entity.TbvFixMerchantSafe;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.services.PayOuterServer;
import com.imobpay.base.services.util.EmptyChecker;
import com.imobpay.base.services.util.Tools;
import com.imobpay.base.util.Des3Util;
import com.imobpay.base.util.MD5;

/**
 * 
 * ClassName: PayServerServlet <br/>
 * 
 * @author CAOWENJUN
 * @version
 * @since JDK 1.6
 */
@Service
public class PayServerServlet extends HttpServlet {

    /** 上下文对象 */
    @Resource
    private ApplicationContext                        applicationContext;

    /** 微信参数表 */
    private TbvFixMerchantSafeDao<TbvFixMerchantSafe> tbvFixMerchantSafeDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        applicationContext = (ApplicationContext) (this.getServletContext().getAttribute("applicationContext"));
    }

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long reqRime = System.currentTimeMillis();
        String respData = "";
        String extDes3Key = "";
        String extToken = "";
        byte[] iv = null;
        String resp = "";
        JSONObject json = new JSONObject();
        try {
            /** 添加线程号 */
            Thread.currentThread().setName(Tools.getOnlyPK());
            LogPay.debug("---->SessionId-->" + request.getSession().getId());

            String data = request.getParameter(Console_Column.RECEIVEDATA);
            String sign = request.getParameter(Console_Column.RECEIVESIGN);
            String bid = request.getParameter(Console_Column.RECEIVEBRD);

            /** 判断必填参数 */
            if (EmptyChecker.isEmpty(data) || EmptyChecker.isEmpty(sign) || EmptyChecker.isEmpty(bid)) {
                throw new QTException(Console_ErrCode.RESP_CODE_03_ERR_PRARM, Console_ErrCode.NECESSARYPARAM);
            }
            LogPay.info("接收未转换报文内容：data【" + data + "】sign【" + sign + "】");

            /** 获取密钥对像 */
            TbvFixMerchantSafe tbvFixMerchantSafe = new TbvFixMerchantSafe();
            tbvFixMerchantSafe.setAgencyId(bid);
            tbvFixMerchantSafeDao = (TbvFixMerchantSafeDao<TbvFixMerchantSafe>) applicationContext.getBean("tbvFixMerchantSafeDao");
            tbvFixMerchantSafe = tbvFixMerchantSafeDao.selectById(tbvFixMerchantSafe);
            if (EmptyChecker.isEmpty(tbvFixMerchantSafe)) {
                LogPay.error("数据配置异常：未配置参数密钥对象");
                throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, Console_ErrCode.TRANS_ERROR);
            }

            extDes3Key = tbvFixMerchantSafe.getExtdes3key();
            extDes3Key = EmptyChecker.isNotEmpty(extDes3Key) ? extDes3Key : "123456789123456789123456";
            extToken = tbvFixMerchantSafe.getExttoken();
            extToken = EmptyChecker.isNotEmpty(extToken) ? extToken : "123456";
            String byteiv = tbvFixMerchantSafe.getByteiv();
            iv = new byte[] {};
            if (EmptyChecker.isNotEmpty(byteiv)) {
                iv = byteiv.getBytes();
            }

            /** 获取密钥 */
            LogPay.info("解密密钥 =" + extDes3Key);
            String str = "";
            /** data 加解密Des3Util.dencrypt */
            try {
                str = Des3Util.Decrypt(data, extDes3Key, iv);
            } catch (Exception e) {
                throw new QTException(Console_ErrCode.RESP_CODE_02_ERR_PARSE, "发送报文异常：报文解析失败");
            }

            /** 转换成xml */
            JSONObject reqJson = JSONObject.parseObject(str);
            if (EmptyChecker.isEmpty(reqJson)) {
                throw new QTException(Console_ErrCode.RESP_CODE_02_ERR_PARSE, "发送报文异常：报文解析失败");
            }
            Object branchid = reqJson.get(Console_Column.P_BRDID);
            Object tranCode = reqJson.get(Console_Column.SMS_SERVERJYM);

            /** 判断必填参数 */
            if (EmptyChecker.isEmpty(branchid) || EmptyChecker.isEmpty(tranCode)) {
                throw new QTException(Console_ErrCode.RESP_CODE_03_ERR_PRARM, "参数错误：缺少必须参数、或必须参数不合法");
            }
            if (!checkSign(str, extToken, sign)) {
                LogPay.error("发送验签数据发送[" + sign + "]" + "内容使用：[" + (MD5.md5(data + extToken)) + "]");
                throw new QTException(Console_ErrCode.RESP_CODE_12_ERR_SIGN, "数据被篡改,验签失败.");
            }

            /** 调用服务处理业务 */
            PayOuterServer weiXinServer = (PayOuterServer) applicationContext.getBean("payOuterServerImpl");
            respData = weiXinServer.execute(reqJson.toString());
            if (EmptyChecker.isEmpty(respData)) {
                LogPay.error("调用服务" + tranCode + "未返回数据");
                throw new QTException(Console_ErrCode.RESP_CODE_88_ERR_TXN, Console_ErrCode.TRANS_ERROR);
            }

            /** 加密结果 */
            try {
                json = JSONObject.parseObject(respData);
                response.setCharacterEncoding("UTF-8");
                resp = json.toString();
                LogPay.info("返回数据：" + resp);
                resp = Des3Util.Encrypt(resp, extDes3Key, iv);
                resp = java.net.URLEncoder.encode(resp, "UTF-8");
            } catch (Exception e) {
                LogPay.error("系统异常:" + e.getMessage(), e);
                throw new QTException(Console_ErrCode.RESP_CODE_12_ERR_SIGN, e.getMessage());
            }
        } catch (QTException e) {
            LogPay.error("系统异常:" + e.getMessage(), e);
            JSONObject jsonResp = new JSONObject();
            jsonResp.put(Console_Column.P_MSG_CODE, e.getRespCode());
            jsonResp.put(Console_Column.P_MSG_TEXT, e.getRespMsg());
            resp = jsonResp.toJSONString();
        } finally {
            byte[] respByte = resp.getBytes(Des3Util.getcodingType());
            ServletOutputStream out = response.getOutputStream();
            out.write(respByte);
            LogPay.info("当前业务处理耗时:" + (System.currentTimeMillis() - reqRime));
            out.flush();
            out.close();
        }
    }

    /**
     * 
     * @Title: checkSign
     * 
     * @Description: 验证MD5签名
     * @param jsonStr
     *            jsonStr
     * @param token
     *            token
     * @param sign
     *            sign
     * @return boolean
     */
    public boolean checkSign(String jsonStr, String token, String sign) {
        try {
            if (Tools.isBlank(jsonStr) || Tools.isBlank(token)) {
                return false;
            }
            if (Des3Util.SignMd5(jsonStr + token, sign)) {
                LogPay.debug("MD5校验成功");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LogPay.error(e.getMessage(), e);
        }
        return false;
    }

}
