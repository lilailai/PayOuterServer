/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: TbvFixMerchantLog.java .</br>
 *  Package Name:com.imobpay.base.entity .</br>
 *  Date          			Author      Changes .</br>
 *  20161102 031105    Lance.Wu     Create  .</br>
 *  Description: 当前类是通过工具生成的.</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.entity;

/**
 * <pre>
 * ClassName: TbvFixMerchantLog <br/> 
 * date: 20161102 031105 <br/> 
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public class TbvFixMerchantLog extends BaseEntity {

    /** 序号 */
    private static final long serialVersionUID = 1L;

    /** 属性描述：内部机构号 */
    private String            agencyId;
    /** 属性描述： */
    private String            orderid;
    /** 属性描述：应答时间 */
    private String            restime;
    /** 属性描述：请求时间 */
    private String            reqtime;
    /** 属性描述：流水号 */
    private String            merchantcode;

    /**
     * 描述：获取属性值-内部机构号.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getAgencyId() {
        return agencyId;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param agencyId :内部机构号 设置值. <br/>
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param orderid : 设置值. <br/>
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    /**
     * 描述：获取属性值-应答时间.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getRestime() {
        return restime;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param restime :应答时间 设置值. <br/>
     */
    public void setRestime(String restime) {
        this.restime = restime;
    }

    /**
     * 描述：获取属性值-请求时间.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getReqtime() {
        return reqtime;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param reqtime :请求时间 设置值. <br/>
     */
    public void setReqtime(String reqtime) {
        this.reqtime = reqtime;
    }

    /**
     * 描述：获取属性值-流水号.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getMerchantcode() {
        return merchantcode;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param merchantcode :流水号 设置值. <br/>
     */
    public void setMerchantcode(String merchantcode) {
        this.merchantcode = merchantcode;
    }

}