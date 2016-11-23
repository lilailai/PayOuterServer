/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: Payuser.java .</br>
 *  Package Name:com.imobpay.base.entity .</br>
 *  Date          			Author      Changes .</br>
 *  20160907 100927    Lance.Wu     Create  .</br>
 *  Description: 当前类是通过工具生成的.</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.entity;

/**
 * <pre>
 * ClassName: Payuser <br/> 
 * date: 20160907 100927 <br/> 
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public class Payuser extends BaseEntity {

    /** 序号 */
    private static final long serialVersionUID = 1L;

    /** 属性描述： */
    private String            logintag;
    /** 属性描述：信用卡高级认证是否需要验证验证码 0：不需要 1：需要 */
    private String            ifmobilecode;
    /** 属性描述： */
    private String            timestamp;
    /** 属性描述： */
    private String            branchid;
    /** 属性描述： */
    private String            userid;
    /** 属性描述： */
    private String            customerid;
    /** 属性描述： */
    private String            useridtype;
    /** 客户姓名 */
    public String             customername;
    /** 用户姓名 */
    public String             username;
    /** 客户审核标志 */
    public String             customertag;
    /** 用户头像 */
    public String             mugshoturl;

    /**
     * @return the mugshoturl
     */
    public String getMugshoturl() {
        return mugshoturl;
    }

    /**
     * mugshoturl.
     * 
     * @param mugshoturl
     *            the mugshoturl to set
     * @since JDK 1.6 PayTACard
     */
    public void setMugshoturl(String mugshoturl) {
        this.mugshoturl = mugshoturl;
    }

    /**
     * @return the customername
     */
    public String getCustomername() {
        return customername;
    }

    /**
     * customername.
     * 
     * @param customername
     *            the customername to set
     * @since JDK 1.6 PayTACard
     */
    public void setCustomername(String customername) {
        this.customername = customername;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * username.
     * 
     * @param username
     *            the username to set
     * @since JDK 1.6 PayTACard
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the customertag
     */
    public String getCustomertag() {
        return customertag;
    }

    /**
     * customertag.
     * 
     * @param customertag
     *            the customertag to set
     * @since JDK 1.6 PayTACard
     */
    public void setCustomertag(String customertag) {
        this.customertag = customertag;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getLogintag() {
        return logintag;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param logintag : 设置值. <br/>
     */
    public void setLogintag(String logintag) {
        this.logintag = logintag;
    }

    /**
     * 描述：获取属性值-信用卡高级认证是否需要验证验证码 0：不需要 1：需要.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getIfmobilecode() {
        return ifmobilecode;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param ifmobilecode :信用卡高级认证是否需要验证验证码 0：不需要 1：需要 设置值. <br/>
     */
    public void setIfmobilecode(String ifmobilecode) {
        this.ifmobilecode = ifmobilecode;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param timestamp : 设置值. <br/>
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getBranchid() {
        return branchid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param branchid : 设置值. <br/>
     */
    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param userid : 设置值. <br/>
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param customerid : 设置值. <br/>
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getUseridtype() {
        return useridtype;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param useridtype : 设置值. <br/>
     */
    public void setUseridtype(String useridtype) {
        this.useridtype = useridtype;
    }

}