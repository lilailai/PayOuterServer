package com.imobpay.base.services.impl;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.dao.PayproductDao;
import com.imobpay.base.dao.TbvFixMerchantLogDao;
import com.imobpay.base.dao.TbvSysParamDao;
import com.imobpay.base.dao.TmpcwjDao;
import com.imobpay.base.entity.Payproduct;
import com.imobpay.base.entity.TbvFixMerchantLog;
import com.imobpay.base.entity.TbvSysParam;
import com.imobpay.base.entity.Tmpcwj;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.iface.BusinessInterface;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.services.util.EmptyChecker;
import com.imobpay.base.services.util.TiboJmsUntil;
import com.imobpay.base.util.DateUtil;

/**
 * ClassName: ServicesReqPayServerImpl <br/>
 * Function: 请求订单接口. <br/>
 * date: 2016年10月24日 下午2:56:35 <br/>
 * 
 * @author CAOWENJUN
 * @version
 * @since JDK 1.6
 */
@Service
public class ServicesReqPayServerImpl implements BusinessInterface {
    /** 微信参数表 */
    @Resource
    TbvSysParamDao<TbvSysParam>             tbvSysParamDao;
    /** 日志表 */
    @Resource
    TbvFixMerchantLogDao<TbvFixMerchantLog> tbvFixMerchantLogDao;
    /** PAYPRODUCT信息 */
    @Resource
    PayproductDao<Payproduct>               payproductDao;
    /** PAYPRODUCT信息 */
    @Resource
    TmpcwjDao<Tmpcwj>                       mpcwjDao;
    /** 上下文对象 */
    @Resource
    private ApplicationContext              applicationContext;

    @Override
    public String execute(String reqParame) throws QTException {
        JSONObject reqJson = JSONObject.parseObject(reqParame);
        EmptyChecker.checkEmptyJson(reqJson, Console_Column.MERCHANTCODE, Console_Column.TOTALAMOUNT, Console_Column.PAYWAY, Console_Column.SCENE, Console_Column.SUBJECTTITLE,
                Console_Column.PAY_CALLBACKURL, Console_Column.MSGTYPE, Console_Column.SHOPNAME, Console_Column.P_BRDID, Console_Column.P_TRANCODE, Console_Column.O_SERIALID, Console_Column.ORDERID,
                Console_Column.O_REQ_TRANDATE, Console_Column.O_REQ_TRANTIME, Console_Column.O_VERSION);
        /** 外部机构生产的订单号 */
        String ordrId = reqJson.getString(Console_Column.ORDERID);
        /** 外部机构生产的流水号 */
        String oSerialId = reqJson.getString(Console_Column.O_SERIALID);
        /** 外部机构请求日期 */
        String oReqTrandate = reqJson.getString(Console_Column.O_REQ_TRANDATE);
        /** 外部机构请求时间 */
        String oReqTrantime = reqJson.getString(Console_Column.O_REQ_TRANTIME);
        /** 内部机构号 */
        String pBrdid = reqJson.getString(Console_Column.P_BRDID);
        /** 商户编号 */
        String merchantCode = reqJson.getString(Console_Column.MERCHANTCODE);
        /** 支付方式 */
        String payWay = reqJson.getString(Console_Column.PAYWAY);
        /** 交易金额 */
        String totalAmount = reqJson.getString(Console_Column.TOTALAMOUNT);
        /** 回调地址 */
        String payCallbackurl = reqJson.getString(Console_Column.PAY_CALLBACKURL);

        /** 查询订单是否重复 */
        TbvFixMerchantLog selTbvFixMerchantLog = new TbvFixMerchantLog();
        selTbvFixMerchantLog.setMerchantcode(oSerialId);
        selTbvFixMerchantLog = tbvFixMerchantLogDao.selectById(selTbvFixMerchantLog);
        if (EmptyChecker.isNotEmpty(selTbvFixMerchantLog)) {
            throw new QTException(Console_ErrCode.RESP_CODE_88_ERR_TXN, "请求流水重复");
        }

        /** 记录请求日志 */
        TbvFixMerchantLog tbvFixMerchantLog = new TbvFixMerchantLog();
        tbvFixMerchantLog.setAgencyId(pBrdid);
        tbvFixMerchantLog.setMerchantcode(oSerialId);
        tbvFixMerchantLog.setOrderid(ordrId);
        tbvFixMerchantLog.setReqtime(oReqTrandate + oReqTrantime);
        tbvFixMerchantLogDao.insert(tbvFixMerchantLog);

        /** 查询参数表TBV_SYS_PARAM-PAY_WEIXIN_MERCHANTID */
        TbvSysParam tbvSysParamPc = new TbvSysParam();
        tbvSysParamPc.setParamname(Console_Column.PAY_WEIXIN_MERCHANTID);
        tbvSysParamPc = tbvSysParamDao.selectById(tbvSysParamPc);
        if (EmptyChecker.isEmpty(tbvSysParamPc)) {
            LogPay.error("数据配置异常：未配置参数PAY_WEIXIN_MERCHANTID");
            throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "未知系统异常");
        }
        String payWeixinMerchantid = tbvSysParamPc.getParamvalue();

        /** 查询参数表TBV_SYS_PARAM-PAY_WEIXIN_MERCHANTID */
        tbvSysParamPc.setParamname(Console_Column.PAY_WEIXIN_PRODUCTID + "_" + payWay);
        tbvSysParamPc = tbvSysParamDao.selectById(tbvSysParamPc);
        if (EmptyChecker.isEmpty(tbvSysParamPc)) {
            LogPay.error("数据配置异常：未配置参数PAY_WEIXIN_MERCHANTID");
            throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "未知系统异常");
        }
        String payWeixinProductid = tbvSysParamPc.getParamvalue();

        /** 调取核心记录交易流水信息 */
        TiboJmsUntil jmsUntil = (TiboJmsUntil) applicationContext.getBean("tiboJmsUntil");

        /** 查询参数表TBV_SYS_PARAM-获取发送队列PAY_SEND_QUEUE */
        TbvSysParam tbvSysParam = new TbvSysParam();
        tbvSysParam.setParamname("PAY_SEND_QUEUE");
        tbvSysParam = tbvSysParamDao.selectById(tbvSysParam);
        if (EmptyChecker.isEmpty(tbvSysParam)) {
            LogPay.error("数据配置异常：未配置参数PAY_SEND_QUEUE");
            throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "未知系统异常");
        }
        String paySendQueue = tbvSysParam.getParamvalue();

        /** 查询参数表TBV_SYS_PARAM-获取发送队列PAY_RECEIV_QUEUE */
        tbvSysParam.setParamname("PAY_RECEIV_QUEUE");
        tbvSysParam = tbvSysParamDao.selectById(tbvSysParam);
        if (EmptyChecker.isEmpty(tbvSysParam)) {
            LogPay.error("数据配置异常：未配置参数PAY_RECEIV_QUEUE");
            throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "未知系统异常");
        }
        String payReceivQueue = tbvSysParam.getParamvalue();

        /** 请求核心报文 */
        JSONObject content = new JSONObject();
        content.put(Console_Column.HOST_MERCHANT_ID, merchantCode);
        content.put(Console_Column.TX_BRANCH_ID, pBrdid);
        content.put(Console_Column.TX_MERCHANT_ID, payWeixinMerchantid);
        content.put(Console_Column.TX_PRODUCT_ID, payWeixinProductid);
        content.put(Console_Column.TX_ORDER_AMOUNT, totalAmount);
        content.put(Console_Column.OUTSIDE_ORDER, ordrId);
        content.put(Console_Column.WX_COOPER_URL, payCallbackurl);
        if (Console_Column.WXZF.equals(payWay)) {
            content.put(Console_Column.TX_PAY_TYPE, "12");
        } else {
            content.put(Console_Column.TX_PAY_TYPE, "32");
        }
        String orderMsg = "";
        try {
            LogPay.info("请求核心订单数据：" + content.toString());
            orderMsg = jmsUntil.sendStreamMessage(paySendQueue, payReceivQueue, true, content.toString(), System.currentTimeMillis() + "", "");
        } catch (Exception e) {
            LogPay.error("请求核心订单异常：" + e.getMessage());
            throw new QTException(Console_ErrCode.RESP_CODE_88_ERR_TXN, Console_ErrCode.TRANS_ERROR);
        }

        /** 核心返回数据判空 */
        if (EmptyChecker.isEmpty(orderMsg)) {
            LogPay.error("请求核心订单异常：核心返回数据为空");
            throw new QTException(Console_ErrCode.RESP_CODE_88_ERR_TXN, Console_ErrCode.TRANS_ERROR);
        }

        /** 核心返回数据处理 */
        JSONObject oderMsgJson = JSONObject.parseObject(orderMsg);
        String oderMsgJsonCode = oderMsgJson.getString(Console_Column.P_MSG_CODE);
        String oderMsgJsonText = oderMsgJson.getString(Console_Column.P_MSG_TEXT);
        String reqMsgId = "";
        String totalFee = "";
        if (Console_ErrCode.SUCCESSCODE.equals(oderMsgJsonCode)) {
            reqMsgId = oderMsgJson.getString(Console_Column.ORDERID);
            totalFee = oderMsgJson.getString(Console_Column.TOTALFEE);
        } else {
            LogPay.error("请求核心订单异常：" + oderMsgJsonText);
            throw new QTException(Console_ErrCode.RESP_CODE_88_ERR_TXN, oderMsgJsonText);
        }
        reqJson.put(Console_Column.SUBJECT, reqMsgId);
        reqJson.put(Console_Column.REQMSGID, reqMsgId);
        reqJson.put(Console_Column.TOTALFEE, totalFee);
        reqJson.put(Console_Column.REQDATE, oReqTrandate + oReqTrantime);
        Object obj = applicationContext.getBean("servicesWeiXinSFZFImpl");
        if (EmptyChecker.isEmpty(obj)) {
            LogPay.error("[未定义" + obj + "]的对像或者没有注解");
            throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "未知系统异常");
        }
        BusinessInterface bean = (BusinessInterface) obj;
        String result = bean.execute(reqJson.toString());
        if (EmptyChecker.isEmpty(result)) {
            throw new QTException(Console_ErrCode.RESP_CODE_88_ERR_TXN, Console_ErrCode.TRANS_ERROR);
        }

        /** 处理返回报文 */
        JSONObject retJson = JSONObject.parseObject(result);
        String pcode = "";
        String ptxt = "";
        String qrCode = "";
        String code = retJson.getString(Console_Column.P_MSG_CODE);
        if (Console_ErrCode.SUCCESS.equals(code)) {
            pcode = Console_ErrCode.SUCCESS;
            ptxt = Console_ErrCode.SUCCESSDESC;
            qrCode = retJson.getString("qrCode");
        } else {
            pcode = Console_ErrCode.RESP_CODE_88_ERR_TXN;
            ptxt = Console_ErrCode.TRANS_ERROR;
        }
        /** 组装返回报文 */
        JSONObject retData = new JSONObject();
        retData.put(Console_Column.ORDERID, ordrId);
        retData.put(Console_Column.P_MSG_CODE, pcode);
        retData.put(Console_Column.P_MSG_TEXT, ptxt);
        retData.put(Console_Column.O_SERIALID, ordrId);
        retData.put(Console_Column.QRCODE, qrCode);
        tbvFixMerchantLog.setRestime(DateUtil.getCurrDate() + DateUtil.getCurrTime());
        tbvFixMerchantLogDao.update(tbvFixMerchantLog);
        return retData.toString();
    }
}
