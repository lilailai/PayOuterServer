/**
 *  <pre>	
 *  Project Name:PaySmsServer .</br>
 *  File: ServicesSimpleSms.java .</br>
 *  Package Name:com.imobpay.base.services.impl .</br>
 *  Date      Author       Changes .</br>
 *  2016年6月14日   Lance.Wu     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.test;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.console.Console_Server_Jym;
import com.imobpay.base.services.PayOuterServer;
import com.imobpay.base.util.Tools;

/** 
 * <pre>
 * ClassName: ServicesSimpleSms <br/> 
 * date: 2016年6月14日 下午5:54:21 <br/> 
 * @author Lance.Wu . <br/> 
 * @version   . <br/> 
 * @since JDK 1.6 PaySmsServer 1.0 . <br/> 
 * </pre>
 */
public class ServicesTest {

    static {
        String workdir = System.getProperty("user.dir");
        System.setProperty("workdir", workdir);
    }

    /**
     * 方法名： main.<br/>
     * author：曹文军.<br/>
     * 创建日期：2016年8月5日.<br/>
     * 创建时间：下午1:44:42.<br/>
     * 参数或异常：@param args .<br/>
     * 其它内容： JDK 1.6 PayNoteServer 1.0.<br/>
     */
    @SuppressWarnings("all")
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("workdir"));
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-context.xml" });
        PayOuterServer us = (PayOuterServer) context.getBean("weiXinServerImpl");
        JSONObject item = new JSONObject();
        packageVerifyCreditRank(item);
        String execute = us.execute(item.toString());
        System.out.println(execute);
    }

    /**
     * author：曹文军.<br/>
     * 创建日期：2016年8月5日.<br/>
     * 创建时间：下午1:45:02.<br/>
     * 参数或异常：@param item
     * 参数或异常：@return .<br/>
     * 返回结果：JSONObject.<br/>
     * 其它内容： JDK 1.6 PayNoteServer 1.0.<br/>
     */
    public static JSONObject packageVerifyCreditRank(JSONObject item) {

        item.put(Console_Column.SMS_SERVERJYM, Console_Server_Jym.JYM_SEARCHORDERID);
//        item.put(Console_Column.SMS_SERVERJYM, Console_Server_Jym.JYM_SEARCHORDERID);
//        // item.put(Console_Column.SMS_SERVERJYM,
//        // Console_Server_Jym.JYM_SMZF100001);
//        item.put("REQMSGID", "2016083118425400000077");
//        item.put("WX_MSG_TEM_CONTENT_COUNT", "3");
//        item.put("WX_MSG_TRADE_TYPE", "WxMsgTrade");
//        // item.put("P_TRANCODE", "SMZF100001");
//        item.put("SCENE", "1");
//        item.put("TOTALAMOUNT", "1");
//        // item.put("ORIREQMSGID", "201609011740079E");
//        item.put("REQMSGID", Tools.getOnlyPK());
//        item.put("MERCHANTCODE", "qt0000000000000");
//        item.put("SUBJECT", "测试订单");
//        item.put("MSGTYPE", "01");
//        item.put("SHOPNAME", "01");
//        item.put("TOTALFEE", "100");
//        item.put("REQDATE", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
//        item.put("PAYWAY", "WXZF");
        
        
        
        item.put("ORDERID", "WXZF");
        item.put("MERCHANTCODE", "WXZF");
        item.put("MSGTYPE", "WXZF");
      
        return item;
    }
}
