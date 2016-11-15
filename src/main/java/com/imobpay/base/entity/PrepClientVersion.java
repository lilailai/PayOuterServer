/**
 *  <pre>	
 *  Project Name :PayUserServer .</br>
 *  File : PrepClientVersion.java .</br>
 *  Package Name :com.imobpay.base.entity .</br>
 *  Date      Author       Changes .</br>
 *  2016年6月2日   Lance.Wu     Create  .</br>
 *  Description : .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.entity;

/**
 * <pre>
 * ClassName : PrepClientVersion <br/> 
 * date : 20160614 050639 <br/> 
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public class PrepClientVersion extends BaseEntity {

    /** 序号 */
    private static final long serialVersionUID = 1L;

    /** 属性描述：版本号 */
    private String            instrVersion;
    /** 属性描述：null */
    private String            shopno;
    /** 属性描述：机构号 */
    private String            branchid;
    /** 属性描述：客户端描述 */
    private String            clientAlias;
    /** 属性描述：更新路径 */
    private String            updatePath;
    /** 属性描述：是否必须更新 */
    private Integer           isMustUpdate;
    /** 属性描述：版本描述 */
    private String            updateContent;
    /** 属性描述：null */
    private String            appid;
    /** 属性描述：app秘钥 */
    private String            key;
    /** 属性描述：当前版本状态 */
    private String            versionstatus;
    /** 属性描述：app名称 */
    private String            appuser;
    /** 属性描述：客户端类型 */
    private String            clientType;
    /** 属性描述：客户端版本 */
    private String            clientVersion;

    /**
     * 描述：获取属性值-null .<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getInstrVersion() {
        return instrVersion;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7 :27 :50 <br/>
     * 参数 : @param instrVersion :null 设置值. <br/>
     */
    public void setInstrVersion(String instrVersion) {
        this.instrVersion = instrVersion;
    }

    /**
     * 描述：获取属性值-null .<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getShopno() {
        return shopno;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7 :27 :50 <br/>
     * 参数 : @param shopno :null 设置值. <br/>
     */
    public void setShopno(String shopno) {
        this.shopno = shopno;
    }

    /**
     * 描述：获取属性值-null .<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getBranchid() {
        return branchid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7 :27 :50 <br/>
     * 参数 : @param branchid :null 设置值. <br/>
     */
    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    /**
     * 描述：获取属性值-null .<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getClientAlias() {
        return clientAlias;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7 :27 :50 <br/>
     * 参数 : @param clientAlias :null 设置值. <br/>
     */
    public void setClientAlias(String clientAlias) {
        this.clientAlias = clientAlias;
    }

    /**
     * 描述：获取属性值-null .<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getUpdatePath() {
        return updatePath;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7 :27 :50 <br/>
     * 参数 : @param updatePath :null 设置值. <br/>
     */
    public void setUpdatePath(String updatePath) {
        this.updatePath = updatePath;
    }

    /**
     * 描述：获取属性值-null .<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return Integer .<br/>
     */
    public Integer getIsMustUpdate() {
        return isMustUpdate;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7 :27 :50 <br/>
     * 参数 : @param isMustUpdate :null 设置值. <br/>
     */
    public void setIsMustUpdate(Integer isMustUpdate) {
        this.isMustUpdate = isMustUpdate;
    }

    /**
     * 描述：获取属性值-null .<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getUpdateContent() {
        return updateContent;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7 :27 :50 <br/>
     * 参数 : @param updateContent :null 设置值. <br/>
     */
    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    /**
     * 描述：获取属性值-null .<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7 :27 :50 <br/>
     * 参数 : @param appid :null 设置值. <br/>
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * 描述：获取属性值-null .<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getKey() {
        return key;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7 :27 :50 <br/>
     * 参数 : @param key :null 设置值. <br/>
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 描述：获取属性值-null .<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getVersionstatus() {
        return versionstatus;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7 :27 :50 <br/>
     * 参数 : @param versionstatus :null 设置值. <br/>
     */
    public void setVersionstatus(String versionstatus) {
        this.versionstatus = versionstatus;
    }

    /**
     * 描述：获取属性值-null .<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getAppuser() {
        return appuser;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7 :27 :50 <br/>
     * 参数 : @param appuser :null 设置值. <br/>
     */
    public void setAppuser(String appuser) {
        this.appuser = appuser;
    }

    /**
     * 描述：获取属性值-null .<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7 :27 :50 <br/>
     * 参数 : @param clientType :null 设置值. <br/>
     */
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    /**
     * 描述：获取属性值-null .<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getClientVersion() {
        return clientVersion;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7 :27 :50 <br/>
     * 参数 : @param clientVersion :null 设置值. <br/>
     */
    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

}