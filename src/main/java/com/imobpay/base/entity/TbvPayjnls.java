/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: TbvPayjnls.java .</br>
 *  Package Name:com.imobpay.base.entity .</br>
 *  Date          			Author      Changes .</br>
 *  20161021 031006    Lance.Wu     Create  .</br>
 *  Description: 当前类是通过工具生成的.</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.entity;

import java.io.Serializable;

/**
 * <pre>
 * ClassName: TbvPayjnls <br/> 
 * date: 20161021 031006 <br/> 
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public class TbvPayjnls  implements Serializable {

    /** 序号 */
    private static final long serialVersionUID = 1L;

    /** 属性描述： */
    private String            feeid;
    /** 属性描述：经度 */
    private String            longitude;
    /** 属性描述： */
    private String            msgcode;
    /** 属性描述： */
    private String            msgtext;
    /** 属性描述：机构号 */
    private String            branchid;
    /** 属性描述：商户名称 */
    private String            merchantname;
    /** 属性描述： */
    private String            merchantid;
    /** 属性描述：纬度 */
    private String            latitude;
    /** 属性描述：IP */
    private String            ip;
    /** 属性描述：日期 */
    private String            localdate;
    /** 属性描述：时间 */
    private String            localtime;
    /** 属性描述：流水号 */
    private String            locallogno;
    /** 属性描述：TID */
    private String            tid;
    /** 属性描述：TA卡号 */
    private String            taaccount;
    /** 属性描述：手机号 */
    private String            mobile;
    /** 属性描述：提现卡号 */
    private String            accountno;
    /** 属性描述：支付方式 01：ta卡微信 11：瑞玛微信 21：ta卡支付宝 31:瑞玛支付宝 */
    private String            paytype;
    /** 属性描述：金额 */
    private Integer           amount;
    /** 属性描述：手续费 */
    private Integer           fee;
    /** 属性描述：代发金额 */
    private Integer           issuingAmount;
    /** 属性描述：订单号 */
    private String            orderId;
    /** 属性描述：服务编码 */
    private String            servcode;
    /** ipenid */
    private String            openid;
    /** fdfee */
    private String            fdfee;

    /** 
     * 方法名： getOpenid.<br/>
     * 适用条件:TODO(简单描述).<br/> 
     * 执行流程:TODO(简单描述).<br/> 
     * 注意事项:TODO(简单描述).<br/>
     * 方法作用:TODO(简单描述).<br/>
     *
     * 返回值：@return 返回值 
     *
     * 创建者：Lance.Wu.<br/>
     * 创建日期：2016年11月3日.<br/>
     * 创建时间：下午2:08:55.<br/>
     * 其它内容： JDK 1.6 PayWeiXinServer 1.0.<br/>
     */

    public String getOpenid() {
        return openid;
    }

    /** 
    * 方法名： setOpenid.<br/>
    * 适用条件:TODO(简单描述).<br/> 
    * 执行流程:TODO(简单描述).<br/> 
    * 注意事项:TODO(简单描述).<br/>
    * 方法作用:TODO(简单描述).<br/>
    *
    * 参数： @param openid 设置值
    *
    * 创建者：Lance.Wu.<br/>
    * 创建日期：2016年11月3日.<br/>
    * 创建时间：下午2:08:55.<br/>
    * 其它内容： JDK 1.6 PayWeiXinServer 1.0.<br/>
    */

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /** 
     * 方法名： getFdfee.<br/>
     * 适用条件:TODO(简单描述).<br/> 
     * 执行流程:TODO(简单描述).<br/> 
     * 注意事项:TODO(简单描述).<br/>
     * 方法作用:TODO(简单描述).<br/>
     *
     * 返回值：@return 返回值 
     *
     * 创建者：Lance.Wu.<br/>
     * 创建日期：2016年11月3日.<br/>
     * 创建时间：下午2:08:55.<br/>
     * 其它内容： JDK 1.6 PayWeiXinServer 1.0.<br/>
     */

    public String getFdfee() {
        return fdfee;
    }

    /** 
    * 方法名： setFdfee.<br/>
    * 适用条件:TODO(简单描述).<br/> 
    * 执行流程:TODO(简单描述).<br/> 
    * 注意事项:TODO(简单描述).<br/>
    * 方法作用:TODO(简单描述).<br/>
    *
    * 参数： @param fdfee 设置值
    *
    * 创建者：Lance.Wu.<br/>
    * 创建日期：2016年11月3日.<br/>
    * 创建时间：下午2:08:55.<br/>
    * 其它内容： JDK 1.6 PayWeiXinServer 1.0.<br/>
    */

    public void setFdfee(String fdfee) {
        this.fdfee = fdfee;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getFeeid() {
        return feeid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param feeid : 设置值. <br/>
     */
    public void setFeeid(String feeid) {
        this.feeid = feeid;
    }

    /**
     * 描述：获取属性值-经度.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param longitude :经度 设置值. <br/>
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getMsgcode() {
        return msgcode;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param msgcode : 设置值. <br/>
     */
    public void setMsgcode(String msgcode) {
        this.msgcode = msgcode;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getMsgtext() {
        return msgtext;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param msgtext : 设置值. <br/>
     */
    public void setMsgtext(String msgtext) {
        this.msgtext = msgtext;
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
     * 描述：获取属性值-商户名称.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getMerchantname() {
        return merchantname;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param merchantname :商户名称 设置值. <br/>
     */
    public void setMerchantname(String merchantname) {
        this.merchantname = merchantname;
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
     * 描述：获取属性值-纬度.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param latitude :纬度 设置值. <br/>
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 描述：获取属性值-IP.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getIp() {
        return ip;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param ip :IP 设置值. <br/>
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 描述：获取属性值-日期.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getLocaldate() {
        return localdate;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param localdate :日期 设置值. <br/>
     */
    public void setLocaldate(String localdate) {
        this.localdate = localdate;
    }

    /**
     * 描述：获取属性值-时间.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getLocaltime() {
        return localtime;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param localtime :时间 设置值. <br/>
     */
    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    /**
     * 描述：获取属性值-流水号.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getLocallogno() {
        return locallogno;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param locallogno :流水号 设置值. <br/>
     */
    public void setLocallogno(String locallogno) {
        this.locallogno = locallogno;
    }

    /**
     * 描述：获取属性值-TID.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getTid() {
        return tid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param tid :TID 设置值. <br/>
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

    /**
     * 描述：获取属性值-TA卡号.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getTaaccount() {
        return taaccount;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param taaccount :TA卡号 设置值. <br/>
     */
    public void setTaaccount(String taaccount) {
        this.taaccount = taaccount;
    }

    /**
     * 描述：获取属性值-手机号.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param mobile :手机号 设置值. <br/>
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 描述：获取属性值-提现卡号.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getAccountno() {
        return accountno;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param accountno :提现卡号 设置值. <br/>
     */
    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    /**
     * 描述：获取属性值-支付方式 01：ta卡微信 11：瑞玛微信 21：ta卡支付宝 31:瑞玛支付宝.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getPaytype() {
        return paytype;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param paytype :支付方式 01：ta卡微信 11：瑞玛微信 21：ta卡支付宝 31:瑞玛支付宝 设置值. <br/>
     */
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    /**
     * 描述：获取属性值-金额.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return Integer .<br/>
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param amount :金额 设置值. <br/>
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
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
     * 描述：获取属性值-代发金额.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return Integer .<br/>
     */
    public Integer getIssuingAmount() {
        return issuingAmount;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param issuingAmount :代发金额 设置值. <br/>
     */
    public void setIssuingAmount(Integer issuingAmount) {
        this.issuingAmount = issuingAmount;
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

    /**
     * 描述：获取属性值-服务编码.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getServcode() {
        return servcode;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param servcode :服务编码 设置值. <br/>
     */
    public void setServcode(String servcode) {
        this.servcode = servcode;
    }

}