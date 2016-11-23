/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: TbvCustomer.java .</br>
 *  Package Name:com.imobpay.base.entity .</br>
 *  Date          			Author      Changes .</br>
 *  20161103 021120    Lance.Wu     Create  .</br>
 *  Description: 当前类是通过工具生成的.</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.entity;

/**
 * <pre>
 * ClassName: TbvCustomer <br/> 
 * date: 20161103 021120 <br/> 
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public class TbvCustomer extends BaseEntity {

    /** 序号 */
    private static final long serialVersionUID = 1L;

    /** 属性描述：经营地区 */
    private String            region;
    /** 属性描述：状态：1:正常2:冻结 */
    private String            status;
    /** 属性描述：微信的唯一标识,用于推送用户消息 */
    private String            openid;
    /** 属性描述：机构号 */
    private String            branchid;
    /** 属性描述：纬度 */
    private String            latitude;
    /** 属性描述：店铺名称 */
    private String            shopname;
    /** 属性描述：主营业务 */
    private String            mainBus;
    /** 属性描述：经营省份 */
    private String            province;
    /** 属性描述：经营城市 */
    private String            city;
    /** 属性描述：注册日期 */
    private String            customerregdate;
    /** 属性描述：经营详细地址 */
    private String            address;
    /** 属性描述： */
    private String            ip;
    /** 属性描述：经度 */
    private String            longitude;
    /** 属性描述：TA卡号 */
    private String            taaccount;
    /** 属性描述：身份证 */
    private String            customerpid;
    /** 属性描述：姓名 */
    private String            username;
    /** 属性描述：手机 */
    private String            mobile;
    /** 属性描述： */
    private String            tid;
    /** 属性描述：TA卡号mac */
    private String            taaccountmac;
    
    /**
     * 描述：获取属性值.<br/>
     * 创建人：HuaiYu.Wen <br/>
     * 返回类型：@return taaccountmac .<br/>
     */
    public String getTaaccountmac() {
        return taaccountmac;
    }

    /**
    * 创建人：HuaiYu.Wen <br/>
    * 创建时间：2016年11月23日 下午3:47:41 <br/>
    * 参数: @param  taaccountmac 设置值.  <br/>
    */
    public void setTaaccountmac(String taaccountmac) {
        this.taaccountmac = taaccountmac;
    }

    /**
     * 描述：获取属性值-经营地区.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getRegion() {
        return region;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param region :经营地区 设置值. <br/>
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 描述：获取属性值-状态：1:正常2:冻结.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getStatus() {
        return status;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param status :状态：1:正常2:冻结 设置值. <br/>
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 描述：获取属性值-微信的唯一标识,用于推送用户消息.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param openid :微信的唯一标识,用于推送用户消息 设置值. <br/>
     */
    public void setOpenid(String openid) {
        this.openid = openid;
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
     * 描述：获取属性值-店铺名称.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getShopname() {
        return shopname;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param shopname :店铺名称 设置值. <br/>
     */
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    /**
     * 描述：获取属性值-主营业务.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getMainBus() {
        return mainBus;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param mainBus :主营业务 设置值. <br/>
     */
    public void setMainBus(String mainBus) {
        this.mainBus = mainBus;
    }

    /**
     * 描述：获取属性值-经营省份.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getProvince() {
        return province;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param province :经营省份 设置值. <br/>
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 描述：获取属性值-经营城市.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getCity() {
        return city;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param city :经营城市 设置值. <br/>
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 描述：获取属性值-注册日期.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getCustomerregdate() {
        return customerregdate;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param customerregdate :注册日期 设置值. <br/>
     */
    public void setCustomerregdate(String customerregdate) {
        this.customerregdate = customerregdate;
    }

    /**
     * 描述：获取属性值-经营详细地址.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getAddress() {
        return address;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param address :经营详细地址 设置值. <br/>
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getIp() {
        return ip;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param ip : 设置值. <br/>
     */
    public void setIp(String ip) {
        this.ip = ip;
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
     * 描述：获取属性值-身份证.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getCustomerpid() {
        return customerpid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param customerpid :身份证 设置值. <br/>
     */
    public void setCustomerpid(String customerpid) {
        this.customerpid = customerpid;
    }

    /**
     * 描述：获取属性值-姓名.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getUsername() {
        return username;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param username :姓名 设置值. <br/>
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 描述：获取属性值-手机.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param mobile :手机 设置值. <br/>
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getTid() {
        return tid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param tid : 设置值. <br/>
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

}