/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: TbvOutsideOrder.java .</br>
 *  Package Name:com.imobpay.base.entity .</br>
 *  Date          			Author      Changes .</br>
 *  20161103 111131    Lance.Wu     Create  .</br>
 *  Description: 当前类是通过工具生成的.</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.entity;

/**
 * <pre>
 * ClassName: TbvOutsideOrder <br/> 
 * date: 20161103 111131 <br/> 
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public class TbvOutsideOrder extends BaseEntity {

    /** 序号 */
    private static final long serialVersionUID = 1L;

    /** 属性描述：客户编号 */
    private String            customerid;
    /** 属性描述：实际到账金额 */
    private Integer           issusingAmount;
    /** 属性描述：订单日期 */
    private String            orderDate;
    /** 属性描述：订单时间 */
    private String            orderTime;
    /** 属性描述：合作方的回调地址 */
    private String            url;
    /** 属性描述：订单金额 */
    private Integer           orderAmount;
    /** 属性描述：手续费 */
    private Integer           fee;
    /** 属性描述：外部订单号 */
    private String            outsideOrderId;
    /** 属性描述：订单号 */
    private String            orderId;

    /**
     * 描述：获取属性值-客户编号.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param customerid :客户编号 设置值. <br/>
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    /**
     * 描述：获取属性值-实际到账金额.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return Integer .<br/>
     */
    public Integer getIssusingAmount() {
        return issusingAmount;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param issusingAmount :实际到账金额 设置值. <br/>
     */
    public void setIssusingAmount(Integer issusingAmount) {
        this.issusingAmount = issusingAmount;
    }

    /**
     * 描述：获取属性值-订单日期.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param orderDate :订单日期 设置值. <br/>
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 描述：获取属性值-订单时间.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getOrderTime() {
        return orderTime;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param orderTime :订单时间 设置值. <br/>
     */
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 描述：获取属性值-合作方的回调地址.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getUrl() {
        return url;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param url :合作方的回调地址 设置值. <br/>
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 描述：获取属性值-订单金额.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return Integer .<br/>
     */
    public Integer getOrderAmount() {
        return orderAmount;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param orderAmount :订单金额 设置值. <br/>
     */
    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * 描述：获取属性值-手续费.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return Integer .<br/>
     */
    public Integer getFee() {
        return fee;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param fee :手续费 设置值. <br/>
     */
    public void setFee(Integer fee) {
        this.fee = fee;
    }

    /**
     * 描述：获取属性值-外部订单号.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getOutsideOrderId() {
        return outsideOrderId;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param outsideOrderId :外部订单号 设置值. <br/>
     */
    public void setOutsideOrderId(String outsideOrderId) {
        this.outsideOrderId = outsideOrderId;
    }

    /**
     * 描述：获取属性值-订单号.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param orderId :订单号 设置值. <br/>
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}