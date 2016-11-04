/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: Snippet.java .</br>
 *  Package Name:com.imobpay.base.config .</br>
 *  Date      Author       Changes .</br>
 *  2016年6月13日   Lance.Wu     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * ClassName: Snippet <br/> 
 * Function: 加密机参数. <br/> Oa
 * date: 2016年6月13日 下午10:06:57 <br/> 
 * 
 * @author Lance.Wu . <br/> 
 * @version   . <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
@Component
public class PropertiesConfig implements java.io.Serializable {

    /** 序列号 */
    private static final long serialVersionUID = 3364144429764524182L;

    /** 发送url */
    @Value("#{configProperties['Encrypt.url']}")
    private String            url              = "192.168.1.7";
    /** 端口 */
    @Value("#{configProperties['Encrypt.port']}")
    private Integer           port             = 60000;
    /** 超时时间 */
    @Value("#{configProperties['Encrypt.timeOut']}")
    private Integer           timeOut          = 30000;
    /** 编码 */
    @Value("#{configProperties['Encrypt.encode']}")
    private String            encode           = "utf-8";
    /** 接收长度 */
    @Value("#{configProperties['Encrypt.recLen']}")
    private Integer           recLen           = 2;
    /** 数据类型 */
    @Value("#{configProperties['Encrypt.lenType']}")
    private Integer           lenType          = 16;
    /** 正确结果 */
    @Value("#{configProperties['Encrypt.result']}")
    private String            result           = "00";
    /** 发送长度 */
    @Value("#{configProperties['Encrypt.sendlen']}")
    private Integer           sendlen          = 4;
    /** 发送填充 */
    @Value("#{configProperties['Encrypt.sendlenFill']}")
    private String            sendlenFill      = "0";
    /** 发送头信息 */
    @Value("#{configProperties['Encrypt.addHeadData']}")
    private String            addHeadData      = "A100001";
    /** 发送信息 */
    @Value("#{configProperties['Encrypt.addTrailData']}")
    private String            addTrailData     = "00003";
    /** 添加长度 */
    @Value("#{configProperties['Encrypt.addLen']}")
    private Integer           addLen           = 1;
    /** 添加信息 */
    @Value("#{configProperties['Encrypt.addDataTail']}")
    private String            addDataTail      = "00";
    /** 添加信息 */
    @Value("#{configProperties['login.qtservicesUrl']}")
    private String            qtservicesUrl    = "http://192.168.1.21:8018/qtservices/opservicebyte";

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return url .<br/>
     */
    public String getUrl() {
        return url;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 下午10:15:58 <br/>
     * 参数: @param url 设置值. <br/>
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return port .<br/>
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 下午10:15:58 <br/>
     * 参数: @param port 设置值. <br/>
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return timeOut .<br/>
     */
    public Integer getTimeOut() {
        return timeOut;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 下午10:15:58 <br/>
     * 参数: @param timeOut 设置值. <br/>
     */
    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return encode .<br/>
     */
    public String getEncode() {
        return encode;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 下午10:15:58 <br/>
     * 参数: @param encode 设置值. <br/>
     */
    public void setEncode(String encode) {
        this.encode = encode;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return recLen .<br/>
     */
    public Integer getRecLen() {
        return recLen;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 下午10:15:58 <br/>
     * 参数: @param recLen 设置值. <br/>
     */
    public void setRecLen(Integer recLen) {
        this.recLen = recLen;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return lenType .<br/>
     */
    public Integer getLenType() {
        return lenType;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 下午10:15:58 <br/>
     * 参数: @param lenType 设置值. <br/>
     */
    public void setLenType(Integer lenType) {
        this.lenType = lenType;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return result .<br/>
     */
    public String getResult() {
        return result;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 下午10:15:58 <br/>
     * 参数: @param result 设置值. <br/>
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return sendlen .<br/>
     */
    public Integer getSendlen() {
        return sendlen;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 下午10:15:58 <br/>
     * 参数: @param sendlen 设置值. <br/>
     */
    public void setSendlen(Integer sendlen) {
        this.sendlen = sendlen;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return sendlenFill .<br/>
     */
    public String getSendlenFill() {
        return sendlenFill;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 下午10:15:58 <br/>
     * 参数: @param sendlenFill 设置值. <br/>
     */
    public void setSendlenFill(String sendlenFill) {
        this.sendlenFill = sendlenFill;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return addHeadData .<br/>
     */
    public String getAddHeadData() {
        return addHeadData;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 下午10:15:58 <br/>
     * 参数: @param addHeadData 设置值. <br/>
     */
    public void setAddHeadData(String addHeadData) {
        this.addHeadData = addHeadData;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return addTrailData .<br/>
     */
    public String getAddTrailData() {
        return addTrailData;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 下午10:15:58 <br/>
     * 参数: @param addTrailData 设置值. <br/>
     */
    public void setAddTrailData(String addTrailData) {
        this.addTrailData = addTrailData;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return addLen .<br/>
     */
    public Integer getAddLen() {
        return addLen;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 下午10:15:58 <br/>
     * 参数: @param addLen 设置值. <br/>
     */
    public void setAddLen(Integer addLen) {
        this.addLen = addLen;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return addDataTail .<br/>
     */
    public String getAddDataTail() {
        return addDataTail;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年6月13日 下午10:15:58 <br/>
     * 参数: @param addDataTail 设置值. <br/>
     */
    public void setAddDataTail(String addDataTail) {
        this.addDataTail = addDataTail;
    }

    /**
     * 方法名： getQtservicesUrl.<br/>
     *
     * 返回值：@return 返回值
     *
     * 创建者：张朝辉.<br/>
     * 创建日期：2016年8月11日.<br/>
     * 创建时间：下午1:36:57.<br/>
     * 其它内容： JDK 1.6 PayUserServer 1.0.<br/>
     */
    public String getQtservicesUrl() {
        return qtservicesUrl;
    }

    /**
     * 方法名： setQtservicesUrl.<br/>
     *
     * 参数： @param qtservicesUrl 设置值
     *
     * 创建者：张朝辉.<br/>
     * 创建日期：2016年8月11日.<br/>
     * 创建时间：下午1:36:57.<br/>
     * 其它内容： JDK 1.6 PayUserServer 1.0.<br/>
     */
    public void setQtservicesUrl(String qtservicesUrl) {
        this.qtservicesUrl = qtservicesUrl;
    }

}
