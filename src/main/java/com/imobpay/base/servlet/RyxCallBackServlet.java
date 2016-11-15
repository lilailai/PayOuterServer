package com.imobpay.base.servlet;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.dao.TbvFixMerchantLogDao;
import com.imobpay.base.dao.TbvFixMerchantSafeDao;
import com.imobpay.base.dao.TbvOutsideOrderDao;
import com.imobpay.base.dao.TbvSysParamDao;
import com.imobpay.base.entity.TbvFixMerchantLog;
import com.imobpay.base.entity.TbvFixMerchantSafe;
import com.imobpay.base.entity.TbvOutsideOrder;
import com.imobpay.base.entity.TbvSysParam;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.iface.BusinessInterface;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.services.util.PubWeiXin;
import com.imobpay.base.services.util.TiboJmsUntil;
import com.imobpay.base.util.Des3Util;
import com.imobpay.base.util.EmptyChecker;
import com.imobpay.base.util.HttpHelper;
import com.imobpay.base.util.Tools;

/**
 * 
 * <pre>
 * 【类型】: RyxCallBackServlet <br/> 
 * 【作用】: jetty  servlet  test  madman <br/>  
 * 【时间】：2016年10月14日 上午10:46:48 <br/> 
 * 【作者】：madman <br/>
 * </pre>
 */
public class RyxCallBackServlet extends HttpServlet {
    /** 微信参数表 */
    private TbvSysParamDao<TbvSysParam>               tbvSysParamDao;
    /** 关联表 */
    private TbvOutsideOrderDao<TbvOutsideOrder>       tbvOutsideOrderDao;
    /** 关联表 */
    private TbvFixMerchantSafeDao<TbvFixMerchantSafe> tbvFixMerchantSafeDao;
    /** 流水表 */
    private TbvFixMerchantLogDao<TbvFixMerchantLog>   tbvFixMerchantLogDao;

    /** 上下文对象 */
    @Resource
    private ApplicationContext                        applicationContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        applicationContext = (ApplicationContext) (this.getServletContext().getAttribute("applicationContext"));
    }

    /** (一句话描述) */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 请求流水号 */
        String reqMsgId = "";
        /** 外部订单号 */
        String outerOrder = "";
        /**回到地址 */
        String callBackUrl = "";
        /**交易金额 */
        String totalAmount = "";
        /** 对账日期 */
        String settleDate = "";
        /** 调用核心的队列 */
        String sendtcp = "";
        /** 返回结果集 */
        JSONObject resultData = new JSONObject();
        try {
            /** 添加线程号 */
            Thread.currentThread().setName(Tools.getOnlyPK());
            /**设置请求参数编码格式y */
            request.setCharacterEncoding("UTF8");
            /** 获取报文内容 请求数据，key，交易码 */
            String encryptData = request.getParameter("encryptData");
            String encryptKey = request.getParameter("encryptKey");
            String tranCode = request.getParameter("tranCode");
            reqMsgId = request.getParameter("reqMsgId");

            /** 组装报文为json */
            JSONObject result = new JSONObject();
            result.put("encryptData", encryptData);
            result.put("encryptKey", encryptKey);
            result.put("tranCode", tranCode);
            result.put("reqMsgId", reqMsgId);
            LogPay.info("未解析的回调结果:" + result);
            String callData = "";

            /** 存在回调数据时应答瑞银信 */
            if (EmptyChecker.isNotEmpty(encryptData)) {
                callData = result.toString();
                response.getWriter().write("000000");
                LogPay.info("交易：" + reqMsgId + " 应答瑞银信回调");
            } else {
                LogPay.info("回调未返回数据");
                throw new QTException(Console_ErrCode.RESP_CODE_88_ERR_TXN, "交易失败");
            }

            /** 获取系统参数DAO对象 */
            tbvSysParamDao = (TbvSysParamDao<TbvSysParam>) applicationContext.getBean("tbvSysParamDao");
            /** 瑞银信的公钥 */
            TbvSysParam tbvSysParam = new TbvSysParam();
            tbvSysParam.setParamname("PUBLICKKEY");
            tbvSysParam = tbvSysParamDao.selectById(tbvSysParam);
            if (EmptyChecker.isEmpty(tbvSysParam)) {
                LogPay.error("数据配置异常：未配置参数PUBLICKKEY");
                throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "未知系统异常");
            }
            String publickKey = tbvSysParam.getParamvalue();

            /** 私钥 */
            tbvSysParam.setParamname("PRIVATEKEY");
            tbvSysParam = tbvSysParamDao.selectById(tbvSysParam);
            if (EmptyChecker.isEmpty(tbvSysParam)) {
                LogPay.error("数据配置异常：未配置参数PRIVATEKEY");
                throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "未知系统异常");
            }
            String privateKey = tbvSysParam.getParamvalue();

            /** 获取公私钥对象 */
            Map<String, Object> keyMaps = new HashMap<String, Object>();
            keyMaps.put("publickKey", publickKey);
            keyMaps.put("privateKey", privateKey);
            Map<String, Object> keys = PubWeiXin.getKeys(keyMaps);
            PrivateKey hzfPriKey = (PrivateKey) keys.get("hzfPriKey");

            /** 解密回到结果 */
            String callJStr = PubWeiXin.analyData(callData, hzfPriKey);
            LogPay.info("交易" + reqMsgId + "的回调结果：" + callJStr.toString());

            /**回调结果转为json */
            JSONObject callJson = JSONObject.parseObject(callJStr);
            /** 获取返回码 */
            String resultCode = callJson.getString("respCode");
            String respType = "";
            /** 成功时获取返回类型和对账期日，返回类型 */
            if ("000000".equals(resultCode)) {
                totalAmount = callJson.getString("totalAmount");
                settleDate = callJson.getString("settleDate");
                respType = callJson.getString("respType");
            }

            /** 调用查询接口 */
            JSONObject sendData = new JSONObject();
            sendData.put("ORIREQMSGID", reqMsgId);
            sendData.put("MSGTYPE", "01");
            sendData.put("REQDATE", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
            Object obj = applicationContext.getBean("servicesWeiXinQueryImpl");
            if (EmptyChecker.isEmpty(obj)) {
                LogPay.error("[未定义" + obj + "]的对像或者没有注解");
                throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "未知系统异常");
            }
            BusinessInterface bean = (BusinessInterface) obj;
            String queryResult = bean.execute(sendData.toString());
            if (EmptyChecker.isEmpty(queryResult)) {
                throw new QTException(Console_ErrCode.RESP_CODE_88_ERR_TXN, Console_ErrCode.TRANS_ERROR);
            }

            /** 查询返回的结果 */
            JSONObject resp = JSONObject.parseObject(queryResult);
            String resCode = resp.getString(Console_Column.P_MSG_CODE);
            String oriRespType = "";
            String queryRespCode = "";
            if ("0000".equals(resCode)) {
                queryRespCode = resp.getString("resultCode");
                oriRespType = resp.getString("oriRespType");
            }

            /** 根据外部订单号查询内部订单号 */
            TbvOutsideOrder tbvOutsideOrder = new TbvOutsideOrder();
            tbvOutsideOrder.setOrderId(reqMsgId);
            tbvOutsideOrderDao = (TbvOutsideOrderDao<TbvOutsideOrder>) applicationContext.getBean("tbvOutsideOrderDao");
            tbvOutsideOrder = tbvOutsideOrderDao.selectById(tbvOutsideOrder);
            if (EmptyChecker.isNotEmpty(tbvOutsideOrder)) {
                outerOrder = tbvOutsideOrder.getOutsideOrderId();
                callBackUrl = tbvOutsideOrder.getUrl();
            }

            /** 查询参数表TBV_SYS_PARAM-wxPaySendTcp */
            tbvSysParam.setParamname("wxPaySendTcp");
            tbvSysParam = tbvSysParamDao.selectById(tbvSysParam);
            if (EmptyChecker.isEmpty(tbvSysParam)) {
                LogPay.error("数据配置异常：未配置参数wxPaySendTcp");
                throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, Console_ErrCode.NO_DBPARAM);
            }
            sendtcp = tbvSysParam.getParamvalue();

            /** 如果两次返回码均为成功 交易成功 */
            if ("000000".equals(resultCode) && "000000".equals(queryRespCode)) {
                /** 返回类型必须为s */
                if ("s".equalsIgnoreCase(oriRespType)) {
                    /** 回调和查询必须返回类型一致 */
                    if (oriRespType.equals(respType)) {
                        LogPay.info("交易：" + reqMsgId + "回调与查询结果一致");
                        resultData.put(Console_Column.P_MSG_CODE, "0000");
                        resultData.put(Console_Column.P_MSG_TEXT, "交易成功");
                        resultData.put(Console_Column.ORDERID, reqMsgId);
                        LogPay.info("发送队列名:" + sendtcp);

                        /** 调取核心记录交易流水信息 */
                        TiboJmsUntil jmsUntil = (TiboJmsUntil) applicationContext.getBean("tiboJmsUntil");
                        try {
                            jmsUntil.sendStreamMessage(sendtcp, "", false, resultData.toString(), System.currentTimeMillis() + "", "");
                        } catch (Exception e) {
                            LogPay.error("回调核心队列失败：" + e.getMessage());
                            throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, e.getMessage());
                        }
                        
                        /** 如果不是合作方调用，则调用下推 */
                        if (EmptyChecker.isEmpty(tbvOutsideOrder)) {
                            /** 调用消息下推 */
                            JSONObject pushData = new JSONObject();
                            pushData.put("P_TRANCODE", "WxMsgSend");
                            pushData.put(Console_Column.REQMSGID, reqMsgId);
                            pushData.put(Console_Column.WX_MSG_TRADE_TYPE, "WxMsgTrade");
                            pushData.put(Console_Column.WX_MSG_TEM_CONTENT_COUNT, "3");
                            Object downPushObj = applicationContext.getBean("servicesWeiXinMsgPush");
                            if (EmptyChecker.isEmpty(downPushObj)) {
                                LogPay.error("[未定义" + downPushObj + "]的对像或者没有注解");
                                throw new QTException(Console_ErrCode.PARAM_EMPTY, Console_ErrCode.SYSNOSERVEDESC);
                            }
                            BusinessInterface downPushBean = (BusinessInterface) obj;
                            String rs = downPushBean.execute(sendData.toString());
                            if (EmptyChecker.isEmpty(rs)) {
                                LogPay.info("消息下推无返回");
                            } else {
                                LogPay.info("消息下推返回：" + rs);
                            }
                        }
                    }
                }
            } else {
                /** 交易失败 */
                resultData.put("ORDERID", reqMsgId);
                resultData.put(Console_Column.P_MSG_CODE, "0088");
                resultData.put(Console_Column.P_MSG_TEXT, "交易失败");
                /** 如果是外部订单，需要回调核心 */
                TiboJmsUntil jmsUntil = (TiboJmsUntil) applicationContext.getBean("tiboJmsUntil");
                try {
                    jmsUntil.sendStreamMessage(sendtcp, "", false, resultData.toString(), System.currentTimeMillis() + "", "");
                } catch (Exception e) {
                    LogPay.error("回调核心队列失败：" + e.getMessage());
                }
            }

        } catch (QTException e) {
            LogPay.error("获取内容失败:" + e.getMessage(), e);
            resultData.put(Console_Column.P_MSG_CODE, e.getRespCode());
            resultData.put(Console_Column.P_MSG_TEXT, e.getMessage());
        } finally {
            try {
                /** 如果是外部订单，需要回调合作方 */
                if (EmptyChecker.isNotEmpty(outerOrder)) {
                    /** 查询日志报，取合作方的流水号 */
                    TbvFixMerchantLog tbvFixMerchantLog = new TbvFixMerchantLog();
                    tbvFixMerchantLog.setOrderid(outerOrder);
                    tbvFixMerchantLogDao = (TbvFixMerchantLogDao<TbvFixMerchantLog>) applicationContext.getBean("tbvFixMerchantLogDao");
                    tbvFixMerchantLog = tbvFixMerchantLogDao.selectById(tbvFixMerchantLog);
                    if (EmptyChecker.isEmpty(tbvFixMerchantLog)) {
                        throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, Console_ErrCode.NO_DBPARAM);
                    }
                    String oSerialid = tbvFixMerchantLog.getMerchantcode();
                    
                    /** 填充返回给合作方的报文 流水号，金额，订单号，对账日期*/
                    resultData.put(Console_Column.O_SERIALID, oSerialid);
                    resultData.put(Console_Column.TOTALAMOUNT, totalAmount);
                    resultData.put("ORDERID", outerOrder);
                    resultData.put("SETTLEDATE", settleDate);
                    /** 设置返回编码 */
                    response.setCharacterEncoding("UTF-8");
                    String resp = JSONObject.toJSONString(resultData, SerializerFeature.WriteMapNullValue);
                    LogPay.info("返回合作方数据：" + resp);
                    
                    /** 根据机构号获取密钥对像 */
                    String agentId = tbvFixMerchantLog.getAgencyId();
                    TbvFixMerchantSafe tbvFixMerchantSafe = new TbvFixMerchantSafe();
                    tbvFixMerchantSafe.setAgencyId(agentId);
                    tbvFixMerchantSafeDao = (TbvFixMerchantSafeDao<TbvFixMerchantSafe>) applicationContext.getBean("tbvFixMerchantSafeDao");
                    tbvFixMerchantSafe = tbvFixMerchantSafeDao.selectById(tbvFixMerchantSafe);
                    if (EmptyChecker.isEmpty(tbvFixMerchantSafe)) {
                        LogPay.error("数据配置异常：未配置参数密钥对象");
                        throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, Console_ErrCode.NO_DBPARAM);
                    }
                    
                    /** 获取3deskey */
                    String extDes3Key = tbvFixMerchantSafe.getExtdes3key();
                    extDes3Key = EmptyChecker.isNotEmpty(extDes3Key) ? extDes3Key : "123456789123456789123456";
                    String extToken = tbvFixMerchantSafe.getExttoken();
                    extToken = EmptyChecker.isNotEmpty(extToken) ? extToken : "123456";
                    String byteiv = tbvFixMerchantSafe.getByteiv();
                    byte[] iv = new byte[] {};
                    if (EmptyChecker.isNotEmpty(byteiv)) {
                        iv = byteiv.getBytes();
                    }

                    /** 获取密钥 */
                    LogPay.info("解密密钥 =" + extDes3Key);
                    resp = Des3Util.Encrypt(resp, extDes3Key, iv);
                    resp = java.net.URLEncoder.encode(resp, "UTF-8");

                    LogPay.info("回调合作方地址:" + callBackUrl);
                    HttpHelper.send(callBackUrl, resp);
                }
            } catch (Exception e) {
                LogPay.error("回调第三方异常");
            }
        }
    }
}
