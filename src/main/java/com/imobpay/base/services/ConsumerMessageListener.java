/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: Snippet.java .</br>
 *  Package Name:com.imobpay.base.services.jms .</br>
 *  Date      Author       Changes .</br>
 *  2016年6月8日   Lance.Wu     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.services;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.config.JymFindServerConfig;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.entity.ReultErrorBean;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.iface.BusinessInterface;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.util.EmptyChecker;
import com.imobpay.base.util.Tools;
import com.tibco.tibjms.TibjmsQueue;

/**
 * <pre>
 * ClassName: Snippet <br/> 
 * Function: 接收队列数据. <br/> 
 * date: 2016年6月8日 下午3:35:27 <br/> 
 * 
 * @author Lance.Wu . <br/> 
 * @version   . <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public class ConsumerMessageListener implements MessageListener {

    /**
     * 对列操作对像
     */
    @Resource
    private JmsTemplate jmsTemplate;

    /** 发送队列 */
    private String      sendTibcoOutName = "";
    /** 编码格式 */
    private String      encode           = "GBK";
    /** 是否返回 */
    private String      flagReturn       = "true";

    /** Spring对像 */
    @Resource
    ApplicationContext  applicationContext;

    /**
     * 描述：功能藐视：当前方法实现方法.<br/>
     * 创建人：Lance.Wu .<br/>
     * 创建时间：2016年6月8日 下午3:37:27 .<br/>
     * 
     * @param message
     *            message
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    @Override
    public void onMessage(Message message) {
        long l = System.currentTimeMillis();
        String respStr = "";
        String correlationID = "";
        String jsonStr = "";
        Object jym=null;
        try {
            if (message instanceof javax.jms.TextMessage) {
                TextMessage textMsg = (TextMessage) message;
                correlationID = textMsg.getJMSCorrelationID();
                jsonStr = textMsg.getText();
            } else if (message instanceof BytesMessage) {
                BytesMessage bmsg = (BytesMessage) message;
                correlationID = bmsg.getJMSCorrelationID();
                StringBuffer stringBuffer = new StringBuffer();
                byte[] buffer = new byte[2048];
                int count = 0;
                while (-1 != (count = bmsg.readBytes(buffer))) {
                    stringBuffer.append(new String(buffer, 0, count, encode));
                }
                jsonStr = stringBuffer.toString();
            } else {
                LogPay.error("暂时不支持除Text和Byte之的其它格式");
                throw new QTException(Console_ErrCode.SYSERROR, Console_ErrCode.JYMNULLDESC);
            }

            JSONObject jsons = (JSONObject) JSONObject.parse(jsonStr);
            jym = jsons.get(Console_Column.SERVERJYM);

            String logKey = jsons.get(Console_Column.LOGKEYALL) + "";
            if (EmptyChecker.isEmpty(logKey) || "null".equals(logKey)) {
                logKey = Tools.getOnlyPK();
            }
            Thread.currentThread().setName(logKey);
            LogPay.info("请求内容:" + jsons.toString());
            /** 判断交易码 */
            if (EmptyChecker.isEmpty(jym)) {
                throw new QTException(Console_ErrCode.PARAM_EMPTY, Console_ErrCode.JYMNULLDESC);
            }

            String jymStr = JymFindServerConfig.getServerObject(jym.toString());

            if (EmptyChecker.isEmpty(jymStr)) {
                LogPay.error("未配置[JymFindServerConfig]中的交易码");
                throw new QTException(Console_ErrCode.PARAM_EMPTY, Console_ErrCode.SYSNOSERVEDESC);
            }

            Object serverObj = applicationContext.getBean(jymStr);

            if (EmptyChecker.isEmpty(serverObj)) {
                LogPay.error("[未定义" + serverObj + "]的对像或者没有注解");
                throw new QTException(Console_ErrCode.PARAM_EMPTY, Console_ErrCode.SYSNOSERVEDESC);
            }

            BusinessInterface bean = (BusinessInterface) serverObj;

            long lb = System.currentTimeMillis();
            respStr = bean.execute(jsons.toJSONString());
            LogPay.info("执行业务时间[" + (System.currentTimeMillis() - lb) + "]");
        } catch (QTException e) {
            LogPay.error(e.getMessage(), e);
            String reutlBeanType = JymFindServerConfig.getServerResultType(jym);
            ReultErrorBean serverObj = (ReultErrorBean)applicationContext.getBean(reutlBeanType);
            respStr = serverObj.returnBeanJson(e.getRespCode(), e.getRespMsg());
        }   catch (Exception e) {
            LogPay.error(e.getMessage(), e);
            String reutlBeanType = JymFindServerConfig.getServerResultType(jym);
            ReultErrorBean serverObj = (ReultErrorBean)applicationContext.getBean(reutlBeanType);
            respStr = serverObj.returnBeanJson(Console_ErrCode.SYSERROR, Console_ErrCode.SYSNOERRORDESC);
        } catch (Throwable e) {
            LogPay.error(e.getMessage(), e);
            String reutlBeanType = JymFindServerConfig.getServerResultType(jym);
            ReultErrorBean serverObj = (ReultErrorBean)applicationContext.getBean(reutlBeanType);
            respStr = serverObj.returnBeanJson(Console_ErrCode.SYSERROR, Console_ErrCode.SYSNOERRORDESC);
        }  finally {
            if ("true".equalsIgnoreCase(flagReturn)) {
                JSONObject json = (JSONObject) JSONObject.parseObject(respStr);
                byte[] content = null;
                try {
                    content = json.toString().getBytes(encode);
                } catch (UnsupportedEncodingException e) {
                    content = new byte[512];
                    LogPay.info("返回内容[" + (System.currentTimeMillis() - l) + "]:" + JSONObject.parseObject(respStr).toString());
                }
                Destination destion = new TibjmsQueue(sendTibcoOutName);
                sendStreamMessage(destion, content, correlationID);
                LogPay.info("返回内容[" + (System.currentTimeMillis() - l) + "]:" + JSONObject.parseObject(respStr).toString());
            }
        }

    }

    /**
     * 
     * sendStreamMessage:(这里用一句话描述这个方法的作用). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param destination
     *            发送队列<br/>
     * @param content
     *            发送内容
     * @param correlationID
     *            对应编号内容 <br/>
     * @since JDK 1.6 PayUserServer 1.0 <br/>
     */
    public void sendStreamMessage(Destination destination, final byte[] content, final String correlationID) {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                BytesMessage message = session.createBytesMessage();
                message.setJMSCorrelationID(correlationID);
                message.setIntProperty("length", content.length);
                message.writeBytes(content);
                return message;
            }
        });
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 上午11:11:16 <br/>
     * 参数: @param sendTibcoOutName 设置值. <br/>
     */
    public void setSendTibcoOutName(String sendTibcoOutName) {
        this.sendTibcoOutName = sendTibcoOutName;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 上午11:54:22 <br/>
     * 参数: @param encode 设置值. <br/>
     */
    public void setEncode(String encode) {
        this.encode = encode;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月14日 下午6:05:17 <br/>
     * 参数: @param  flagReturn 设置值.  <br/>
     */
    public void setFlagReturn(String flagReturn) {
        this.flagReturn = flagReturn;
    }

}
