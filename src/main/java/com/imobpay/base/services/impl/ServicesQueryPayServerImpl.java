package com.imobpay.base.services.impl;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.dao.TbvFixMerchantLogDao;
import com.imobpay.base.dao.TbvOutsideOrderDao;
import com.imobpay.base.dao.TbvSysParamDao;
import com.imobpay.base.entity.TbvFixMerchantLog;
import com.imobpay.base.entity.TbvOutsideOrder;
import com.imobpay.base.entity.TbvSysParam;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.iface.BusinessInterface;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.services.util.EmptyChecker;

/**
 * ClassName: ServicesReqPayServerImpl <br/>
 * Function: 查询订单接口. <br/>
 * date: 2016年10月24日 下午2:56:35 <br/>
 * 
 * @author CAOWENJUN
 * @version
 * @since JDK 1.6
 */
@Service
public class ServicesQueryPayServerImpl implements BusinessInterface {
    /** 微信参数表 */
    @Resource
    TbvSysParamDao<TbvSysParam>             tbvSysParamDao;
    /** 日志表 */
    @Resource
    TbvFixMerchantLogDao<TbvFixMerchantLog> tbvFixMerchantLogDao;
    /** TbvOutsideOrder信息 */
    @Resource
    TbvOutsideOrderDao<TbvOutsideOrder>     tbvOutsideOrderDao;
    /** 上下文对象 */
    @Resource
    private ApplicationContext              applicationContext;

    @Override
    public String execute(String reqParame) throws QTException {
        JSONObject reqJson = JSONObject.parseObject(reqParame);
        EmptyChecker.checkEmptyJson(reqJson, Console_Column.P_BRDID, Console_Column.P_TRANCODE, Console_Column.O_SERIALID, Console_Column.O_REQ_TRANDATE, Console_Column.O_REQ_TRANTIME,
                Console_Column.O_VERSION, Console_Column.MSGTYPE, Console_Column.ORDERID, Console_Column.MERCHANTCODE);
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

        /** 根据外部订单号查询内部订单号 */
        TbvOutsideOrder tbvOutsideOrder = new TbvOutsideOrder();
        tbvOutsideOrder.setOutsideOrderId(ordrId);
        tbvOutsideOrder = tbvOutsideOrderDao.selectById(tbvOutsideOrder);
        if (EmptyChecker.isEmpty(tbvOutsideOrder)) {
            LogPay.error("订单" + ordrId + "不存在");
            throw new QTException(Console_ErrCode.RESP_CODE_88_ERR_TXN, "订单不存在");
        }
        String innerOrder = tbvOutsideOrder.getOrderId();

        reqJson.put(Console_Column.ORI_REQMSGID, innerOrder);
        reqJson.put(Console_Column.REQDATE, oReqTrandate + oReqTrantime);
        Object obj = applicationContext.getBean("servicesWeiXinQueryImpl");
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
        String totalAmount = "";
        String oriRespType = "";
        String code = retJson.getString(Console_Column.P_MSG_CODE);
        String ptxt = retJson.getString(Console_Column.P_MSG_TEXT);
        if (Console_ErrCode.SUCCESS.equals(code)) {
            pcode = Console_ErrCode.SUCCESS;
            oriRespType = retJson.getString("oriRespType");
            totalAmount = retJson.getString("totalAmount");
        } else {
            oriRespType = "3";
            totalAmount = "";
            pcode = Console_ErrCode.RESP_CODE_88_ERR_TXN;
        }
        /** 组装返回报文 */
        JSONObject retData = new JSONObject();
        retData.put(Console_Column.ORDERID, ordrId);
        retData.put(Console_Column.P_MSG_CODE, pcode);
        retData.put(Console_Column.P_MSG_TEXT, ptxt);
        retData.put(Console_Column.O_SERIALID, oSerialId);
        retData.put(Console_Column.TOTALAMOUNT, totalAmount);
        if ("S".equalsIgnoreCase(oriRespType)) {
            retData.put(Console_Column.TRANS_STATUS, "1");
        } else if ("E".equalsIgnoreCase(oriRespType)) {
            retData.put(Console_Column.TRANS_STATUS, "2");
        } else {
            retData.put(Console_Column.TRANS_STATUS, "3");
        }
        return retData.toString();
    }
}
