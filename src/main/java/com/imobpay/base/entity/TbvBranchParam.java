/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: TbvBranchParam.java .</br>
 *  Package Name:com.imobpay.base.entity .</br>
 *  Date          			Author      Changes .</br>
 *  20161021 031012    Lance.Wu     Create  .</br>
 *  Description: 当前类是通过工具生成的.</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.entity;

/**
 * <pre>
 * ClassName: TbvBranchParam <br/> 
 * date: 20161021 031012 <br/> 
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public class TbvBranchParam extends BaseEntity {

    /** 序号 */
    private static final long serialVersionUID = 1L;

    /** 属性描述： */
    private String            createtime;
    /** 属性描述： */
    private String            token;
    /** 属性描述： */
    private String            ticket;
    /** 属性描述： */
    private String            tokenUrl;
    /** 属性描述： */
    private String            ticketUrl;
    /** 属性描述：公众号连接地址 */
    private String            taurl;
    /** 属性描述：机构号 */
    private String            branchid;
    /** 属性描述：公众号 */
    private String            pubaccount;
    /** 属性描述： */
    private String            appid;
    /** 属性描述：秘钥 */
    private String            secret;
    /** 属性描述：公众号描述 */
    private String            tadesc;
    /** 属性描述：状态0无效,1有效 */
    private String            status;

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param createtime : 设置值. <br/>
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getToken() {
        return token;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param token : 设置值. <br/>
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param ticket : 设置值. <br/>
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getTokenUrl() {
        return tokenUrl;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param tokenUrl : 设置值. <br/>
     */
    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getTicketUrl() {
        return ticketUrl;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param ticketUrl : 设置值. <br/>
     */
    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    /**
     * 描述：获取属性值-公众号连接地址.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getTaurl() {
        return taurl;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param taurl :公众号连接地址 设置值. <br/>
     */
    public void setTaurl(String taurl) {
        this.taurl = taurl;
    }

    /**
     * 描述：获取属性值-机构号.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getBranchid() {
        return branchid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param branchid :机构号 设置值. <br/>
     */
    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    /**
     * 描述：获取属性值-公众号.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getPubaccount() {
        return pubaccount;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param pubaccount :公众号 设置值. <br/>
     */
    public void setPubaccount(String pubaccount) {
        this.pubaccount = pubaccount;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param appid : 设置值. <br/>
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * 描述：获取属性值-秘钥.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getSecret() {
        return secret;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param secret :秘钥 设置值. <br/>
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * 描述：获取属性值-公众号描述.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getTadesc() {
        return tadesc;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param tadesc :公众号描述 设置值. <br/>
     */
    public void setTadesc(String tadesc) {
        this.tadesc = tadesc;
    }

    /**
     * 描述：获取属性值-状态0无效,1有效.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getStatus() {
        return status;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param status :状态0无效,1有效 设置值. <br/>
     */
    public void setStatus(String status) {
        this.status = status;
    }

}