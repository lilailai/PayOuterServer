/**
 *  Project Name:PayUrlShort
 *  File: ResultPojo.java
 *  Package Name:com.imobpay.base.entity
 *  Date      Author       Changes
 *  2016年7月27日   madman     Create
 *  Description:
 *  Copyright 2014-2015 QIANTUO FINANCE Services Co.,Ltd. All rights reserved.
 */
package com.imobpay.base.pojo;

import java.io.Serializable;

/**
 * ClassName: ResultPojo <br/>
 * Function: 返回结果pojo <br/>
 * date: 2016年7月27日 下午4:41:45 <br/>
 * 
 * @author madman
 * @version
 * @since JDK 1.6 PayUrlShort 1.0
 */
public class ResultPojo implements Serializable {
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;
    /**
     * 返回码
     */
    public String             msgcode;
    /***
     * 返回描述
     */
    public String             msgtext;

    /**
     * 返回结果
     */
    public String             resultBean;

    /**
     * 
     * ResultPojo:默认构造方法. <br/>
     * 
     * @author madman 返回码 返回描述
     * @since JDK 1.6 PayTACard 1.0
     */
    public ResultPojo() {
        this.msgcode = "0000";
        this.msgtext = "交易成功";
        this.resultBean = "{}";

    }

    /**
     * @return the msgcode
     */
    public String getMsgcode() {
        return msgcode;
    }

    /**
     * msgcode.
     * 
     * @param msgcode
     *            the msgcode to set
     * @since JDK 1.6 PayTACard
     */
    public void setMsgcode(String msgcode) {
        this.msgcode = msgcode;
    }

    /**
     * @return the msgtext
     */
    public String getMsgtext() {
        return msgtext;
    }

    /**
     * msgtext.
     * 
     * @param msgtext
     *            the msgtext to set
     * @since JDK 1.6 PayTACard
     */
    public void setMsgtext(String msgtext) {
        this.msgtext = msgtext;
    }

    /**
     * @return the resultBean
     */
    public String getResultBean() {
        return resultBean;
    }

    /**
     * resultBean.
     * 
     * @param resultBean
     *            the resultBean to set
     * @since JDK 1.6 PayTACard
     */
    public void setResultBean(String resultBean) {
        this.resultBean = resultBean;
    }

}
