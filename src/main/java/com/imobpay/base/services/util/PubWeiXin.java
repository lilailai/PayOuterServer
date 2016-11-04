package com.imobpay.base.services.util;

import java.math.BigDecimal;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.util.EmptyChecker;

/**
 * 类名: PubWeiXin <br/>
 * 作用：微信支付公用方法<br/>
 * 创建者: CAOWENJUN. <br/>
 * 添加时间: 2016年8月29日 下午1:24:57 <br/>
 * 版本：
 * 
 * @since JDK 1.6 QtServer 1.0
 */
public class PubWeiXin {
    /**
     * 
     * 方法名： postData.<br/>
     * author：曹文军.<br/>
     * 创建日期：2016年8月29日.<br/>
     * 创建时间：下午1:29:21.<br/>
     * 参数或异常：@param para 参数
     * 参数或异常：@return 
     * 参数或异常：@throws QTException .<br/>
     * 返回结果：HashMap<String,String>.<br/>
     * 其它内容： JDK 1.6 QtServer 1.0.<br/>
     */
    public static String postData(Map<String, Object> para) throws QTException {
        
        /** 获取配置的URL */
        String url = (String) para.get("wxzfUrl");
        /** 回调URL */
        String reqParaStr = (String) para.get("callBackUrl");

        /** 获取数据 */
        String reqData = para.get("reqData").toString();
        String reqMsgId = (String) para.get("reqMsgId");
        String tranCode = (String) para.get("tranCode");
        PublicKey yhPubKey = (PublicKey) para.get("yhPubKey");
        PrivateKey hzfPriKey = (PrivateKey) para.get("hzfPriKey");

        /** 生成AES密钥key */
        byte[] reqDataByte;
        List<NameValuePair> nvps = new LinkedList<NameValuePair>();
        try {
            reqDataByte = reqData.getBytes("UTF-8");
            String keyStr = AESUtil.getAESKey();
            byte[] keyBytes = keyStr.getBytes("UTF-8");
            String encryptData = new String(Base64.encodeBase64(CryptoUtil.AESEncrypt(reqDataByte, keyBytes, "AES", "AES/ECB/PKCS5Padding", null)), "UTF-8");
            String signData = new String(Base64.encodeBase64(CryptoUtil.digitalSign(reqDataByte, hzfPriKey, "SHA1WithRSA")), "UTF-8");
            String encrtptKey = new String(Base64.encodeBase64(CryptoUtil.RSAEncrypt(keyBytes, yhPubKey, 2048, 11, "RSA/ECB/PKCS1Padding")), "UTF-8");

            String[] paras = reqParaStr.split("#");
            LogPay.info("encryptData:" + encryptData);
            LogPay.info("encryptKey:" + encrtptKey);
            LogPay.info("cooperator:" + paras[1]);
            LogPay.info("signData:" + signData);
            LogPay.info("tranCode:" + tranCode);
            LogPay.info("callBack:" + paras[0]);
            LogPay.info("reqMsgId:" + reqMsgId);

            nvps.add(new BasicNameValuePair("encryptData", encryptData));
            nvps.add(new BasicNameValuePair("encryptKey", encrtptKey));
            nvps.add(new BasicNameValuePair("cooperator", paras[1]));
            nvps.add(new BasicNameValuePair("signData", signData));
            nvps.add(new BasicNameValuePair("tranCode", tranCode));
            nvps.add(new BasicNameValuePair("callBack", paras[0]));
            nvps.add(new BasicNameValuePair("reqMsgId", reqMsgId));
        } catch (Exception e) {
            LogPay.error("加密数据异常:" + e.getMessage());
            throw new QTException(Console_ErrCode.SYSERROR, "加密数据");
        }

        String respStr = HttpClient.post(url, nvps);

        LogPay.info("发送瑞银信返回报文[微信支付]：" + respStr);
        if (EmptyChecker.isEmpty(respStr)) {
            LogPay.error("发送瑞银信未返回结果");
            throw new QTException(Console_ErrCode.SYSERROR, Console_ErrCode.SYSNOERRORDESC);
        }

        JSONObject jsonObject = JSONObject.parseObject(respStr);
        boolean boo = jsonObject.containsKey("respCode");
        if (boo) {
            String txt = jsonObject.getString("respMsg");
            throw new QTException(Console_ErrCode.RESP_CODE_88_ERR_TXN, txt);
        }
        return respStr;
    }

    /**
     * getKeys:(这里用一句话描述这个方法的作用). <br/>
     * 方法名： getKeys.<br/>
     * author：曹文军.<br/>
     * 创建日期：2016年9月1日.<br/>
     * 创建时间：下午2:09:56.<br/>
     * 参数或异常：@param para 参数
     * 参数或异常：@throws QTException .<br/>
     * @return
     * 其它内容： JDK 1.6 QtServer 1.0.<br/>
     */
    public static Map<String, Object> getKeys(Map<String, Object> para) throws QTException {
        /** 瑞银信的公钥 */
        String cooperPublickey = (String) para.get("publickKey");
        PublicKey pubKey = null;
        PrivateKey priKey = null;
        try {
            /** 私钥 */
            String privateKey = (String) para.get("privateKey");
            X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decodeBase64(cooperPublickey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            pubKey = keyFactory.generatePublic(pubX509);

            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
            KeyFactory priKeyFcty = KeyFactory.getInstance("RSA");
            priKey = priKeyFcty.generatePrivate(priPKCS8);
        } catch (Exception e) {
            LogPay.error("生成密钥对象异常:" + e.getMessage());
            throw new QTException(Console_ErrCode.SYSERROR, "生成密钥对象异常");
        }

        Map<String, Object> keys = new HashMap<String, Object>();
        keys.put("hzfPriKey", priKey);
        keys.put("yhPubKey", pubKey);
        return keys;
    }

    /**
     * 创建日期：2016年9月1日.<br/>
     * 创建时间：下午3:04:54.<br/>
     * 参数或异常：@param fen
     * 参数或异常：@return .<br/>
     * 返回结果：String.<br/>
     * 其它内容： JDK 1.6 QtServer 1.0.<br/>
     */
    public static String fromFenToYuan(String fen) {
        String yuan = "";
        // 左去0
        int multiplier = 100;
        Pattern pattern = Pattern.compile("^[1-9][0-9]*{1}");
        Matcher matcher = pattern.matcher(fen);
        if (matcher.matches()) {
            yuan = new BigDecimal(fen).divide(new BigDecimal(multiplier)).setScale(2).toString();
        }
        return yuan;
    }

    /**
     * author：曹文军.<br/>
     * 创建日期：2016年9月1日.<br/>
     * 创建时间：下午3:52:28.<br/>
     * 参数或异常：@param dataStr
     * 参数或异常：@param hzfPriKey
     * 参数或异常：@return
     * 参数或异常：@throws QTException .<br/>
     * 返回结果：String.<br/>
     * 其它内容： JDK 1.6 QtServer 1.0.<br/>
     */
    public static String analyData(String dataStr, PrivateKey hzfPriKey) throws QTException {
        String resXml = "";
        try {
            JSONObject jsonObject = JSONObject.parseObject(dataStr);
            String resEncryptData = jsonObject.getString("encryptData");
            String resEncryptKey = jsonObject.getString("encryptKey");
            byte[] decodeBase64KeyBytes = Base64.decodeBase64(resEncryptKey.getBytes("UTF-8"));
            // 解密encryptKey得到merchantAESKey
            byte[] merchantAESKeyBytes;
            merchantAESKeyBytes = CryptoUtil.RSADecrypt(decodeBase64KeyBytes, hzfPriKey, 2048, 11, "RSA/ECB/PKCS1Padding");

            // 使用base64解码商户请求报文
            byte[] decodeBase64DataBytes = Base64.decodeBase64(resEncryptData.getBytes("UTF-8"));
            // 用解密得到的merchantAESKey解密encryptData
            byte[] merchantXmlDataBytes = CryptoUtil.AESDecrypt(decodeBase64DataBytes, merchantAESKeyBytes, "AES", "AES/ECB/PKCS5Padding", null);
            resXml = new String(merchantXmlDataBytes, "UTF-8");
            LogPay.info("发送瑞银信返回报文[微信支付〉明文]：" + resXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resXml;
    }

}
