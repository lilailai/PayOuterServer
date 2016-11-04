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
package com.imobpay.base.services.util;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.imobpay.base.exception.QTException;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.util.EmptyChecker;
import com.tibco.tibjms.TibjmsQueue;

/**
 * 
 * ClassName: TiboJmsUntil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年6月21日 上午10:45:19 <br/>
 * tibco 队列发送工具
 * 
 * @author madman
 * @version
 * @since JDK 1.6 PayUserServer 1.0
 */
@Component
@Scope("prototype")
public class TiboJmsUntil {

    /**
     * 对列操作对像 (多例模式 )
     */
    @Resource
    private JmsTemplate jmsTemplate;
    /** 编码格式 */
    private String      encode = "GBK";

    /**
     * 
     * sendStreamMessage:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author madman
     * @param sendQueue
     *            队列目的地
     * @param receiveQueue
     *            接收返回队列
     * @param receiveFlag
     *            是否接收返回
     * @param content
     *            发送内容
     * @param correlationID
     *            消息唯一标识
     * @param inEncode
     *            消息编码
     * @throws Exception
     *             抛出异常
     * @return 返回结果
     * @since JDK 1.6 PayUserServer 1.0
     */
    public String sendStreamMessage(String sendQueue, String receiveQueue, boolean receiveFlag, final String content, final String correlationID, String inEncode) throws Exception {
        try {
            if (EmptyChecker.isNotEmpty(inEncode)) {
                encode = inEncode;
            }
            /** 发送消息 */
            Destination destination = new TibjmsQueue(sendQueue);
            jmsTemplate.send(destination, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    BytesMessage message = session.createBytesMessage();
                    message.setJMSCorrelationID(correlationID);
                    try {
                        message.setIntProperty("length", content.getBytes(encode).length);
                        message.writeBytes(content.getBytes(encode));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return message;
                }
            });
            /** 是否需要同步接收等待 */
            if (receiveFlag) {
                if (EmptyChecker.isEmpty(receiveQueue)) {
                    throw new QTException();
                }
                String messageSelector = "JMSCorrelationID= '" + correlationID + "'";
                Destination receiveTibco = new TibjmsQueue(receiveQueue);
                jmsTemplate.setReceiveTimeout(28000);

                BytesMessage receiveMessage = (BytesMessage) jmsTemplate.receiveSelected(receiveTibco, messageSelector);
                if (receiveMessage == null) {
                    LogPay.error("队列同步接收为空:" + receiveMessage);
                }
                StringBuffer stringBuffer = new StringBuffer();
                byte[] buffer = new byte[2048];
                int count = 0;
                while (-1 != (count = receiveMessage.readBytes(buffer))) {
                    stringBuffer.append(new String(buffer, 0, count, encode));
                }
                LogPay.info("队列同步接收结果:" + stringBuffer);
                return stringBuffer.toString();
            }
            return null;
        } catch (Exception e) {
            throw e;
        } finally {
            /** 资源回收 */
            jmsTemplate = null;
        }

    }
}
