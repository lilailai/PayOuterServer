/** 
 * 包名: package com.imobpay.text; <br/> 
 * 添加时间: 2016年6月17日 下午5:30:03 <br/> 
 */
/**
 *  Project Name:PayUserServer
 *  File: ServicesUserRegisterTest.java
 *  Package Name:com.imobpay.text
 *  Date      Author       Changes
 *  2016年6月17日   madman     Create
 *  Description:
 *  Copyright 2014-2015 QIANTUO FINANCE Services Co.,Ltd. All rights reserved.
 */
package com.imobpay.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.services.util.TiboJmsUntil;
import com.imobpay.base.util.Tools;

/**
 * 类名: ServicesUserRegisterTest <br/>
 * 作用：TODO(简单一句话描述)<br/>
 * 方法：TODO(简单描述方法)<br/>
 * 创建者: madman. <br/>
 * 添加时间: 2016年6月17日 下午5:30:03 <br/>
 * 版本： JDK 1.6 PayUserServer 1.0
 */
public class TibcoTest {
    static {
        String workdir = System.getProperty("user.dir");
        System.setProperty("workdir", workdir);

    }

    /**
     * 测试队列 收发 tibcoTest:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author madman
     * @since JDK 1.6 PayUserServer 1.0
     */
    @SuppressWarnings("all")
    @Test
    public void tibcoTest() {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-context.xml" });
            JSONObject content = new JSONObject();
            content.put(Console_Column.HOST_MERCHANT_ID, "A000000111111");
            content.put(Console_Column.TX_PAY_TYPE, "01");
            content.put(Console_Column.TX_BRANCH_ID, "00800075");
            content.put(Console_Column.TX_MERCHANT_ID, "0009000004");
            content.put(Console_Column.TX_PRODUCT_ID, "0000000012");
            content.put(Console_Column.TX_ORDER_AMOUNT, "10000");
            content.put(Console_Column.OUTSIDE_ORDER, Tools.getOnlyPK());
            content.put(Console_Column.WX_COOPER_URL, "baicu.com");
            TiboJmsUntil jmsUntil = (TiboJmsUntil) context.getBean("tiboJmsUntil");
            jmsUntil.sendStreamMessage("WECHATPRO_WECHATSDK.IN", "WECHATSDK_WECHATPRO.OUT", true, content.toString(), System.currentTimeMillis() + "", "GBK");
          
            
            
//            TiboJmsUntil jmsUntil2 = (TiboJmsUntil) context.getBean("tiboJmsUntil");
//            jmsUntil2.sendStreamMessage("UserDubboServerxx.IN", "UserDubboServerxx.OUT", true, js.toString(), System.currentTimeMillis() + "", "UTF-8");
//            // TiboJmsUntil jmsUntil3 = (TiboJmsUntil)
            // context.getBean("tiboJmsUntil");
            // jmsUntil3.sendStreamMessage("UserDubboServerxx.IN",
            // "UserDubboServerxx.OUT", true, js.toString(),
            // System.currentTimeMillis() + "", "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
