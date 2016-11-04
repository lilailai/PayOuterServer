/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: TbvSysParam.java .</br>
 *  Package Name:com.imobpay.base.entity .</br>
 *  Date          			Author      Changes .</br>
 *  20161020 031050    Lance.Wu     Create  .</br>
 *  Description: 当前类是通过工具生成的.</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.entity;

/**
 * <pre>
 * ClassName: TbvSysParam <br/> 
 * date: 20161020 031050 <br/> 
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public class TbvSysParam extends BaseEntity {

    /** 序号 */
    private static final long serialVersionUID = 1L;

    /** 属性描述：参数值 */
    private String            paramvalue;
    /** 属性描述：参数描述 */
    private String            paramdesc;
    /** 属性描述：参数名 */
    private String            paramname;

    /**
     * 描述：获取属性值-参数值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getParamvalue() {
        return paramvalue;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param paramvalue :参数值 设置值. <br/>
     */
    public void setParamvalue(String paramvalue) {
        this.paramvalue = paramvalue;
    }

    /**
     * 描述：获取属性值-参数描述.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getParamdesc() {
        return paramdesc;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param paramdesc :参数描述 设置值. <br/>
     */
    public void setParamdesc(String paramdesc) {
        this.paramdesc = paramdesc;
    }

    /**
     * 描述：获取属性值-参数名.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return String .<br/>
     */
    public String getParamname() {
        return paramname;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param paramname :参数名 设置值. <br/>
     */
    public void setParamname(String paramname) {
        this.paramname = paramname;
    }

}