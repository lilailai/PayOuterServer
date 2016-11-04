/**
 *  <pre>	
 *  Project Name:UserDServer .</br>
 *  File: Test.java .</br>
 *  Package Name:com.imobpay.text .</br>
 *  Date      Author       Changes .</br>
 *  2016年6月1日   Lance.Wu     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.imobpay.base.entity.ReultErrorBean;

/**
 * <pre>
 * ClassName: Test <br/> 
 * date: 2016年6月1日 下午4:53:16 <br/> 
 * 
 * @author Lance.Wu . <br/> 
 * @version   . <br/> 
 * @since JDK 1.6 UserDServer 1.0 . <br/>
 * </pre>
 */
public class Test {

    /**
     * 
     * main:(这里用一句话描述这个方法的作用). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param args
     * <br/>
     * @since JDK 1.6 PayUserServer 1.0 <br/>
     */
    @SuppressWarnings("all")
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-context.xml" });

        ReultErrorBean bean = (ReultErrorBean) context.getBean("resultBean");

        System.out.println(bean.returnBeanJson("123", "12312"));

        // UserServer us = (UserServer) context.getBean("userServerImpl");

        // Map<String, Object> item = new HashMap<String, Object>();
        // 调用用户登录组装报文
        // packageAppLogin(item);
        // 组装查询用户信息接口
        // packageUserInfo(item);
        // Map<String, Object> execute = us.executeJson(json)(item);
        // JSONObject json = (JSONObject) JSONObject.toJSON(execute);
        // System.out.println(json.toString());
    }

}
