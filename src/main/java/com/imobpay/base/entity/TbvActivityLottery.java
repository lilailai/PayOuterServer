/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: TbvActivityLottery.java .</br>
 *  Package Name:com.imobpay.base.entity .</br>
 *  Date          			Author      Changes .</br>
 *  20160923 040929    Lance.Wu     Create  .</br>
 *  Description: 当前类是通过工具生成的.</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.entity;

/**
 * <pre>
 * ClassName: TbvActivityLottery <br/> 
 * date: 20160923 040929 <br/> 
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public class TbvActivityLottery extends BaseEntity {

    /** 序号 */
    private static final long serialVersionUID = 1L;

    /** 属性描述：描述 W微信 A APP */
    private String            remark;
    /** 属性描述：用户OPID */
    private String            openid;
    /** 属性描述：卡类型 1 TA卡 */
    private String            cardty;
    /** 属性描述：有效时间 */
    private String            checktime;
    /** 属性描述：状态 0初始状态 1 中奖状态 */
    private String            status;
    /** 属性描述：二维码内容 */
    private String            cardurl;
    /** 属性描述：TA卡号 */
    private String            id;
    /** 公众号 */
    private String            pubAccount;

    /**
     * 
     * 【方法名】 : (这里用一句话描述这个方法的作用). <br/>
     * 【注意】: (这里描述这个方法的注意事项 – 可选).<br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2016年10月13日 下午3:46:43 .<br/>
     * 【参数】： .<br/>
     * 
     * @return .<br/>
     *         <p>
     *         修改记录.<br/>
     *         修改人: madman 修改描述：创建新新件 .<br/>
     *         <p/>
     */
    public String getPubAccount() {
        return pubAccount;
    }

    /**
     * 
     * 【方法名】 : (这里用一句话描述这个方法的作用). <br/>
     * 【注意】: (这里描述这个方法的注意事项 – 可选).<br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2016年10月13日 下午3:46:48 .<br/>
     * 【参数】： .<br/>
     * 
     * @param pubAccount
     *            .<br/>
     *            <p>
     *            修改记录.<br/>
     *            修改人: madman 修改描述：创建新新件 .<br/>
     *            <p/>
     */
    public void setPubAccount(String pubAccount) {
        this.pubAccount = pubAccount;
    }

    /**
     * 描述：获取属性值-描述 W微信 A APP.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param remark :描述 W微信 A APP 设置值. <br/>
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 描述：获取属性值-用户OPID.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param openid :用户OPID 设置值. <br/>
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 描述：获取属性值-卡类型 1 TA卡.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getCardty() {
        return cardty;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param cardty :卡类型 1 TA卡 设置值. <br/>
     */
    public void setCardty(String cardty) {
        this.cardty = cardty;
    }

    /**
     * 描述：获取属性值-有效时间.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getChecktime() {
        return checktime;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param checktime :有效时间 设置值. <br/>
     */
    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    /**
     * 描述：获取属性值-状态 0初始状态 1 中奖状态.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getStatus() {
        return status;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param status :状态 0初始状态 1 中奖状态 设置值. <br/>
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 描述：获取属性值-二维码内容.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getCardurl() {
        return cardurl;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param cardurl :二维码内容 设置值. <br/>
     */
    public void setCardurl(String cardurl) {
        this.cardurl = cardurl;
    }

    /**
     * 描述：获取属性值-TA卡号.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getId() {
        return id;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param id :TA卡号 设置值. <br/>
     */
    public void setId(String id) {
        this.id = id;
    }

}