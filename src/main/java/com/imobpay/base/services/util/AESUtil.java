package com.imobpay.base.services.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by Administrator on 7/25 0025.
 */
@SuppressWarnings("restriction")
public class AESUtil {

    /** 字符集 */
    public static final String  CHARSET        = "UTF-8";
    /** 加密类型 */
    public static final String  ALGORITHM_TYPE = "AES";
    /**加密法则*/
    public static final String  AES_ALGORITHM  = "AES/ECB/PKCS5Padding";
    /**生成密钥的字符集合(只取char中的数字和字母)*/
    private static final char[] KEY_CHAR_ARRAY = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
        't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    /**
     * 
     * Encrypt:加密数据. <br/> 
     * @author CAOWENJUN 
     * @param sSrc 源字符串
     * @param sKey 密钥
     * @return string
     * @throws Exception 
     * @since JDK 1.6
     */
    public static String Encrypt(String sSrc, String sKey) throws Exception {

        // 判断Key是否为16位
        if (sKey == null) {
            throw new RuntimeException("AES加密密钥为空或格式不对！");
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            throw new RuntimeException("AES加密密钥为空或格式不对！");
        }
        byte[] raw = sKey.getBytes(CHARSET);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM_TYPE);
        /**"算法/模式/补码方式"*/
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(CHARSET));
        /** 此处使用BASE64做转码功能，同时能起到2次加密的作用。*/
        return new BASE64Encoder().encode(encrypted);
    }

    /**
     * 
     * Decrypt:解密数据. <br/> 
     * @author CAOWENJUN 
     * @param sSrc 源字符串
     * @param sKey aes密钥
     * @return String
     * @throws Exception 异常
     * @since JDK 1.6
     */
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            /**判断Key是否正确*/
            if (sKey == null) {
                throw new RuntimeException("AES解密密钥为空或格式不对！");
            }
            /**判断Key是否正确*/
            if (sKey.length() != 16) {
                throw new RuntimeException("AES解密密钥为空或格式不对！");
            }
            byte[] raw = sKey.getBytes(CHARSET);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM_TYPE);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            // 先用base64解密
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, CHARSET);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * 
     * getAESKey:获取AES密钥，16位的字母数字组合. <br/> 
     * @author CAOWENJUN 
     * @return AES
     * @since JDK 1.6
     */
    public static String getAESKey() {

        StringBuffer keyBuffer = new StringBuffer("");
        for (int i = 0; i < 16; i++) {
            keyBuffer.append(KEY_CHAR_ARRAY[(int) Math.floor(Math.random() * KEY_CHAR_ARRAY.length)]);
        }
        return keyBuffer.toString();
    }

}
