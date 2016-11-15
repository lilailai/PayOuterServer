/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: TbvCard.java .</br>
 *  Package Name:com.imobpay.base.entity .</br>
 *  Date          			Author      Changes .</br>
 *  20160827 100810    Lance.Wu     Create  .</br>
 *  Description: 当前类是通过工具生成的.</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.entity;

/**
 * <pre>
 * ClassName: TbvCard <br/> 
 * date: 20160827 100810 <br/> 
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public class TbvCard extends BaseEntity {

    /** 序号 */
    private static final long serialVersionUID = 1L;

    /** 属性描述：机构号 */
    private String            branchid;
    /** 属性描述：URL */
    private String            url;
    /** 属性描述：入库时间 */
    private String            createdate;
    /** 属性描述：状态 */
    private String            status;
    /** 属性描述：TA卡号 */
    private String            taaccount;
    /** 机构名称 */
    private String            branchname;
    /** ta卡号串 */
    private String            taaccountmac;
    /*** 绑定类型 0未绑定 1已绑定 */
    private String            bindflag;
    /** TA卡类型 0 微信端 1手刷端 */
    private String            type;
    /** feeid 费率标识 */
    private String            feeid;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * type.
     * 
     * @param type
     *            the type to set
     * @since JDK 1.6 PayTACard
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the feeid
     */
    public String getFeeid() {
        return feeid;
    }

    /**
     * feeid.
     * 
     * @param feeid
     *            the feeid to set
     * @since JDK 1.6 PayTACard
     */
    public void setFeeid(String feeid) {
        this.feeid = feeid;
    }

    /**
     * @return the bindflag
     */
    public String getBindflag() {
        return bindflag;
    }

    /**
     * bindflag.
     * 
     * @param bindflag
     *            the bindflag to set
     * @since JDK 1.6 PayTACard
     */
    public void setBindflag(String bindflag) {
        this.bindflag = bindflag;
    }

    /**
     * @return the taaccountmac
     */
    public String getTaaccountmac() {
        return taaccountmac;
    }

    /**
     * taaccountmac.
     * 
     * @param taaccountmac
     *            the taaccountmac to set
     * @since JDK 1.6 PayTACard
     */
    public void setTaaccountmac(String taaccountmac) {
        this.taaccountmac = taaccountmac;
    }

    /**
     * @return the branchname
     */
    public String getBranchname() {
        return branchname;
    }

    /**
     * branchname.
     * 
     * @param branchname
     *            the branchname to set
     * @since JDK 1.6 PayTACard
     */
    public void setBranchname(String branchname) {
        this.branchname = branchname;
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
     * 描述：获取属性值-URL.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getUrl() {
        return url;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param url :URL 设置值. <br/>
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 描述：获取属性值-入库时间.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getCreatedate() {
        return createdate;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param createdate :入库时间 设置值. <br/>
     */
    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    /**
     * 描述：获取属性值-状态.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getStatus() {
        return status;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param status :状态 设置值. <br/>
     */
    public void setStatus(String status) {
        this.status = status;
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

}