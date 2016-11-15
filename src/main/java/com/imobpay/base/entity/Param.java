/** 
 * 包名: package com.imobpay.codeImg.entity; <br/> 
 * 添加时间: 2016年8月26日 下午3:12:03 <br/> 
 */
package com.imobpay.base.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 类名: Param <br/>
 * 作用：参数对像<br/>
 * 方法：参数对像<br/>
 * 创建者: Administrator. <br/>
 * 添加时间: 2016年8月26日 下午3:12:03 <br/>
 * 版本： JDK 1.6 codeImg 1.0
 */
public class Param implements Serializable {

    /**
     * 序列
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 3908831518401557832L;

    /**
     * ftpurl
     */
    private String            ftpUrl;
    /**
     * ftp端口
     */
    private String            ftpPort;
    /**
     * ftp用户名称
     */
    private String            ftpName;
    /**
     *ftp密码
     */
    private String            ftpPass;

    /**
     * 文件5
     */
    private String            fileFri;
    /**
     * 文件2
     */
    private String            fileTwo;

    /** 生成图片机构 */
    private String            createBranch;

    /**
     * 文件大小
     */
    private int               defaultDirCount;
    /**
     * 文件大小
     */
    private String            taStart;
    /**
     * 手刷用户二维码目录
     */
    private String            cusPayPicDir;
    /**
     * 手刷用户二维码目录访问地址
     */
    private String            picFilePre;
    /**
     * 瑞码的费率ID
     */
    private String            ruiMaFeeid;

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return ruiMaFeeid .<br/>
     */
    public String getRuiMaFeeid() {
        return ruiMaFeeid;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年9月23日 下午6:21:57 <br/>
     * 参数: @param ruiMaFeeid 设置值. <br/>
     */
    public void setRuiMaFeeid(String ruiMaFeeid) {
        this.ruiMaFeeid = ruiMaFeeid;
    }

    /**
     * @return the picFilePre
     */
    public String getPicFilePre() {
        return picFilePre;
    }

    /**
     * picFilePre.
     * 
     * @param picFilePre
     *            the picFilePre to set
     * @since JDK 1.6 PayTACard
     */
    public void setPicFilePre(String picFilePre) {
        this.picFilePre = picFilePre;
    }

    /**
     * @return the cusPayPicDir
     */
    public String getCusPayPicDir() {
        return cusPayPicDir;
    }

    /**
     * cusPayPicDir.
     * 
     * @param cusPayPicDir
     *            the cusPayPicDir to set
     * @since JDK 1.6 PayTACard
     */
    public void setCusPayPicDir(String cusPayPicDir) {
        this.cusPayPicDir = cusPayPicDir;
    }

    /**
     * @return the taStart
     */
    public String getTaStart() {
        return taStart;
    }

    /**
     * taStart.
     * 
     * @param taStart
     *            the taStart to set
     * @since JDK 1.6 PayCodeImg
     */
    public void setTaStart(String taStart) {
        this.taStart = taStart;
    }

    /**
     * @return the ftpUrl
     */
    public String getFtpUrl() {
        return ftpUrl;
    }

    /**
     * ftpUrl.
     * 
     * @param ftpUrl
     *            the ftpUrl to set
     * @since JDK 1.6 PayTACard
     */
    public void setFtpUrl(String ftpUrl) {
        this.ftpUrl = ftpUrl;
    }

    /**
     * @return the ftpPort
     */
    public String getFtpPort() {
        return ftpPort;
    }

    /**
     * ftpPort.
     * 
     * @param ftpPort
     *            the ftpPort to set
     * @since JDK 1.6 PayTACard
     */
    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort;
    }

    /**
     * @return the ftpName
     */
    public String getFtpName() {
        return ftpName;
    }

    /**
     * ftpName.
     * 
     * @param ftpName
     *            the ftpName to set
     * @since JDK 1.6 PayTACard
     */
    public void setFtpName(String ftpName) {
        this.ftpName = ftpName;
    }

    /**
     * @return the ftpPass
     */
    public String getFtpPass() {
        return ftpPass;
    }

    /**
     * ftpPass.
     * 
     * @param ftpPass
     *            the ftpPass to set
     * @since JDK 1.6 PayTACard
     */
    public void setFtpPass(String ftpPass) {
        this.ftpPass = ftpPass;
    }

    /**
     * 开始编号
     */
    private int    numStart;
    /**
     * 结束编号
     */
    private int    numEnd;
    /**
     * 文件目录
     */
    private String fileDir;
    /**
     * 二维码中HTTP路径
     */
    private String httpURL;
    /**
     * 图片URL
     */
    private String picURL;
    /**
     * 3DES密钥
     */
    private String desKey;
    /**
     * 3DES盐
     */
    private String desYan;

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return fileTwo .<br/>
     */
    public String getFileTwo() {
        return fileTwo;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年8月30日 上午10:35:11 <br/>
     * 参数: @param fileTwo 设置值. <br/>
     */
    public void setFileTwo(String fileTwo) {
        this.fileTwo = fileTwo;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return numStart .<br/>
     */
    public int getNumStart() {
        return numStart;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年8月30日 上午10:35:11 <br/>
     * 参数: @param numStart 设置值. <br/>
     */
    public void setNumStart(int numStart) {
        this.numStart = numStart;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return numEnd .<br/>
     */
    public int getNumEnd() {
        return numEnd;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年8月30日 上午10:35:11 <br/>
     * 参数: @param numEnd 设置值. <br/>
     */
    public void setNumEnd(int numEnd) {
        this.numEnd = numEnd;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return fileDir .<br/>
     */
    public String getFileDir() {
        return fileDir;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年8月30日 上午10:35:11 <br/>
     * 参数: @param fileDir 设置值. <br/>
     */
    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return httpURL .<br/>
     */
    public String getHttpURL() {
        return httpURL;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年8月30日 上午10:35:11 <br/>
     * 参数: @param httpURL 设置值. <br/>
     */
    public void setHttpURL(String httpURL) {
        this.httpURL = httpURL;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return picURL .<br/>
     */
    public String getPicURL() {
        return picURL;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年8月30日 上午10:35:11 <br/>
     * 参数: @param picURL 设置值. <br/>
     */
    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return desKey .<br/>
     */
    public String getDesKey() {
        return desKey;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年8月30日 上午10:35:11 <br/>
     * 参数: @param desKey 设置值. <br/>
     */
    public void setDesKey(String desKey) {
        this.desKey = desKey;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return desYan .<br/>
     */
    public String getDesYan() {
        return desYan;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年8月30日 上午10:35:11 <br/>
     * 参数: @param desYan 设置值. <br/>
     */
    public void setDesYan(String desYan) {
        this.desYan = desYan;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return fileFri .<br/>
     */
    public String getFileFri() {
        return fileFri;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年8月30日 上午10:35:25 <br/>
     * 参数: @param fileFri 设置值. <br/>
     */
    public void setFileFri(String fileFri) {
        this.fileFri = fileFri;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return createBranch .<br/>
     */
    public String getCreateBranch() {
        return createBranch;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年8月30日 下午5:34:14 <br/>
     * 参数: @param createBranch 设置值. <br/>
     */
    public void setCreateBranch(String createBranch) {
        this.createBranch = createBranch;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return defaultDirCount .<br/>
     */
    public int getDefaultDirCount() {
        return this.defaultDirCount;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年8月30日 下午5:36:28 <br/>
     * 参数: @param defaultDirCount 设置值. <br/>
     */
    public void setDefaultDirCount(int defaultDirCount) {
        this.defaultDirCount = defaultDirCount;
    }

    /**
     * 
     * Creates a new instance of Param.
     * 
     * @param list
     *            f
     */
    public Param(List<TbvSysParam> list) {
        for (TbvSysParam tbvSysParam : list) {
            String key = tbvSysParam.getParamname();
            String value = tbvSysParam.getParamvalue();
            if ("httpURL".equals(key)) {
                this.httpURL = value;
            }
            if ("picURL".equals(key)) {
                this.picURL = value;
            }
            if ("3DES_KEY".equals(key)) {
                this.desKey = value;
            }
            if ("3DES_YAN".equals(key)) {
                this.desYan = value;
            }
            if ("CreateBranch".equals(key)) {
                this.createBranch = value;
            }
            if ("DefaultDirCount".equals(key)) {
                this.defaultDirCount = Integer.valueOf(value);
            }
            if ("TaStart".equals(key)) {
                this.taStart = value;
            }
            if ("TaStart".equals(key)) {
                this.taStart = value;
            }
            if ("ftpUrl".equals(key)) {
                this.ftpUrl = value;
            }
            if ("ftpPort".equals(key)) {
                this.ftpPort = value;
            }
            if ("ftpName".equals(key)) {
                this.ftpName = value;
            }
            if ("ftpPass".equals(key)) {
                this.ftpPass = value;
            }
            if ("cusPayPicDir".equals(key)) {
                this.cusPayPicDir = value;
            }
            if ("picFilePre".equals(key)) {
                this.picFilePre = value;
            }
            if ("ruiMaFeeid".equals(key)) {
                this.ruiMaFeeid = value;
            }

        }

    }
}
