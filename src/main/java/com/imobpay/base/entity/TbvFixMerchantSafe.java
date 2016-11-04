package com.imobpay.base.entity;

/**
 * <pre>
 * ClassName: TbvFixMerchantSafe <br/> 
 * date: 20161031 041045 <br/> 
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public class TbvFixMerchantSafe extends BaseEntity {

    /** 序号 */
    private static final long serialVersionUID = 1L;

    /** 属性描述：通讯秘钥 */
    private String            extdes3key;
    /** 属性描述：商户号 */
    private String            merchantid;
    /** 属性描述：验签秘钥 */
    private String            exttoken;
    /** 属性描述：内部机构号 */
    private String            agencyId;
    /** 属性描述：解密盐 */
    private String            byteiv;
    /** 属性描述：状态1有效0无效 */
    private String            status;
    /** 属性描述：创建时间 */
    private String            createdate;
    /** 属性描述： */
    private String            id;

    /**
     * 描述：获取属性值-通讯秘钥.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getExtdes3key() {
        return extdes3key;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param extdes3key :通讯秘钥 设置值. <br/>
     */
    public void setExtdes3key(String extdes3key) {
        this.extdes3key = extdes3key;
    }

    /**
     * 描述：获取属性值-商户号.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getMerchantid() {
        return merchantid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param merchantid :商户号 设置值. <br/>
     */
    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    /**
     * 描述：获取属性值-验签秘钥.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getExttoken() {
        return exttoken;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param exttoken :验签秘钥 设置值. <br/>
     */
    public void setExttoken(String exttoken) {
        this.exttoken = exttoken;
    }

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
     * 描述：获取属性值-解密盐.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getByteiv() {
        return byteiv;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param byteiv :解密盐 设置值. <br/>
     */
    public void setByteiv(String byteiv) {
        this.byteiv = byteiv;
    }

    /**
     * 描述：获取属性值-状态1有效0无效.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getStatus() {
        return status;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param status :状态1有效0无效 设置值. <br/>
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 描述：获取属性值-创建时间.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getCreatedate() {
        return createdate;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param createdate :创建时间 设置值. <br/>
     */
    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    /**
     * 描述：获取属性值-.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getId() {
        return id;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param id : 设置值. <br/>
     */
    public void setId(String id) {
        this.id = id;
    }

}