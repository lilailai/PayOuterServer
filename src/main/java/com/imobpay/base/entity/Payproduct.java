/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: Payproduct.java .</br>
 *  Package Name:com.imobpay.base.entity .</br>
 *  Date          			Author      Changes .</br>
 *  20161102 031129    Lance.Wu     Create  .</br>
 *  Description: 当前类是通过工具生成的.</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.entity;

/**
 * <pre>
 * ClassName: Payproduct <br/> 
 * date: 20161102 031129 <br/> 
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public class Payproduct extends BaseEntity {

    /** 序号 */
    private static final long serialVersionUID = 1L;

    /** 属性描述： */
    private String            producttype;
    /** 属性描述： */
    private String            productdescPm;
    /** 属性描述：APP展示产品归类编码 */
    private String            appBusinessType;
    /** 属性描述： */
    private String            timestamp;
    /** 属性描述： */
    private String            merchantid;
    /** 属性描述： */
    private String            productid;
    /** 属性描述： */
    private String            productdesc;
    /** 属性描述： */
    private Integer           status;
    /** 属性描述： */
    private String            curmsgcode;
    /** 属性描述： */
    private String            startdatetime;
    /** 属性描述： */
    private String            enddatetime;
    /** 属性描述： */
    private String            denybranchid;
    /** 属性描述： */
    private String            denyweekday;
    /** 属性描述： */
    private String            denydate;
    /** 属性描述： */
    private String            saleBusinessType;

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getProducttype() {
        return producttype;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param producttype : 设置值. <br/>
     */
    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getProductdescPm() {
        return productdescPm;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param productdescPm : 设置值. <br/>
     */
    public void setProductdescPm(String productdescPm) {
        this.productdescPm = productdescPm;
    }

    /**
     * 描述：获取属性值-APP展示产品归类编码.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getAppBusinessType() {
        return appBusinessType;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param appBusinessType :APP展示产品归类编码 设置值. <br/>
     */
    public void setAppBusinessType(String appBusinessType) {
        this.appBusinessType = appBusinessType;
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
    public String getMerchantid() {
        return merchantid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param merchantid : 设置值. <br/>
     */
    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getProductid() {
        return productid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param productid : 设置值. <br/>
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getProductdesc() {
        return productdesc;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param productdesc : 设置值. <br/>
     */
    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return Integer .<br/>
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param status : 设置值. <br/>
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getCurmsgcode() {
        return curmsgcode;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param curmsgcode : 设置值. <br/>
     */
    public void setCurmsgcode(String curmsgcode) {
        this.curmsgcode = curmsgcode;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getStartdatetime() {
        return startdatetime;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param startdatetime : 设置值. <br/>
     */
    public void setStartdatetime(String startdatetime) {
        this.startdatetime = startdatetime;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getEnddatetime() {
        return enddatetime;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param enddatetime : 设置值. <br/>
     */
    public void setEnddatetime(String enddatetime) {
        this.enddatetime = enddatetime;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getDenybranchid() {
        return denybranchid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param denybranchid : 设置值. <br/>
     */
    public void setDenybranchid(String denybranchid) {
        this.denybranchid = denybranchid;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getDenyweekday() {
        return denyweekday;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param denyweekday : 设置值. <br/>
     */
    public void setDenyweekday(String denyweekday) {
        this.denyweekday = denyweekday;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getDenydate() {
        return denydate;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param denydate : 设置值. <br/>
     */
    public void setDenydate(String denydate) {
        this.denydate = denydate;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getSaleBusinessType() {
        return saleBusinessType;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param saleBusinessType : 设置值. <br/>
     */
    public void setSaleBusinessType(String saleBusinessType) {
        this.saleBusinessType = saleBusinessType;
    }

}