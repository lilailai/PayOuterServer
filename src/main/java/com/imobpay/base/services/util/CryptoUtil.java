package com.imobpay.base.services.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

import com.imobpay.base.log.LogPay;
import com.longtop.intelliplatform.base.exception.ServiceException;

/**
 * ClassName: CryptoUtil <br/> 
 * date: 2016年9月23日 下午1:59:13 <br/> 
 * @version  
 * @since JDK 1.6
 */
public abstract class CryptoUtil {

    /**
     * 数字签名函数入口
     * 
     * @param plainBytes
     *            待签名明文字节数组
     * @param privateKey
     *            签名使用私钥
     * @param signAlgorithm
     *            签名算法
     * @return 签名后的字节数组
     * @throws ServiceException 异常
     */
    public static byte[] digitalSign(byte[] plainBytes, PrivateKey privateKey, String signAlgorithm) throws ServiceException {
        byte[] signBytes = null;
        try {
            Signature signature = Signature.getInstance(signAlgorithm);
            signature.initSign(privateKey);
            signature.update(plainBytes);
            signBytes = signature.sign();
        } catch (NoSuchAlgorithmException e) {
            LogPay.error(String.format("数字签名时没有[%s]此类算法", signAlgorithm));
        } catch (InvalidKeyException e) {
            LogPay.error("数字签名时私钥无效");
        } catch (SignatureException e) {
            LogPay.error("数字签名时出现异常");
        }
        return signBytes;
    }

    /**
     * RSA加密
     * 
     * @param plainBytes
     *            明文字节数组
     * @param publicKey
     *            公钥
     * @param keyLength
     *            密钥bit长度
     * @param reserveSize
     *            padding填充字节数，预留11字节
     * @param cipherAlgorithm
     *            加解密算法，一般为RSA/ECB/PKCS1Padding
     * @return 加密后字节数组，不经base64编码
     * @throws ServiceException 000
     */
    public static byte[] RSAEncrypt(byte[] plainBytes, PublicKey publicKey, int keyLength, int reserveSize, String cipherAlgorithm) throws ServiceException {
        int keyByteSize = keyLength / 8;
        int encryptBlockSize = keyByteSize - reserveSize;
        int nBlock = plainBytes.length / encryptBlockSize;
        if ((plainBytes.length % encryptBlockSize) != 0) {
            nBlock += 1;
        }
        ByteArrayOutputStream outbuf = null;
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            outbuf = new ByteArrayOutputStream(nBlock * keyByteSize);
            for (int offset = 0; offset < plainBytes.length; offset += encryptBlockSize) {
                int inputLen = plainBytes.length - offset;
                if (inputLen > encryptBlockSize) {
                    inputLen = encryptBlockSize;
                }
                byte[] encryptedBlock = cipher.doFinal(plainBytes, offset, inputLen);
                outbuf.write(encryptedBlock);
            }
            outbuf.flush();
            outbuf.close();
            return outbuf.toByteArray();
        } catch (NoSuchAlgorithmException e) {
            LogPay.error(String.format("数字签名时没有[%s]此类算法", cipherAlgorithm));
        } catch (NoSuchPaddingException e) {
            LogPay.error(String.format("没有[%s]此类填充模式", cipherAlgorithm));
        } catch (InvalidKeyException e) {
            LogPay.error("无效密钥");
        } catch (IllegalBlockSizeException e) {
            LogPay.error("加密块大小不合法");
        } catch (BadPaddingException e) {
            LogPay.error("错误填充模式");
        } catch (IOException e) {
            LogPay.error("字节输出流异常");
        }
        return null;
    }

    /**
     * RSA解密
     * 
     * @param encryptedBytes
     *            加密后字节数组
     * @param privateKey
     *            私钥
     * @param keyLength
     *            密钥bit长度
     * @param reserveSize
     *            padding填充字节数，预留11字节
     * @param cipherAlgorithm
     *            加解密算法，一般为RSA/ECB/PKCS1Padding
     * @return 解密后字节数组，不经base64编码
     * @throws ServiceException 000
     */
    public static byte[] RSADecrypt(byte[] encryptedBytes, PrivateKey privateKey, int keyLength, int reserveSize, String cipherAlgorithm) throws ServiceException {
        int keyByteSize = keyLength / 8;
        int decryptBlockSize = keyByteSize - reserveSize;
        int nBlock = encryptedBytes.length / keyByteSize;
        ByteArrayOutputStream outbuf = null;
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            outbuf = new ByteArrayOutputStream(nBlock * decryptBlockSize);
            for (int offset = 0; offset < encryptedBytes.length; offset += keyByteSize) {
                int inputLen = encryptedBytes.length - offset;
                if (inputLen > keyByteSize) {
                    inputLen = keyByteSize;
                }

                byte[] decryptedBlock = cipher.doFinal(encryptedBytes, offset, inputLen);
                outbuf.write(decryptedBlock);
            }

            outbuf.flush();
            outbuf.close();
            return outbuf.toByteArray();
        } catch (NoSuchAlgorithmException e) {
            LogPay.error(String.format("数字签名时没有[%s]此类算法", cipherAlgorithm));
        } catch (NoSuchPaddingException e) {
            LogPay.error(String.format("数字签名时没有[%s]此类算法", cipherAlgorithm));
        } catch (InvalidKeyException e) {
            LogPay.error("无效密钥");
        } catch (IllegalBlockSizeException e) {
            LogPay.error("加密块大小不合法");
        } catch (BadPaddingException e) {
            LogPay.error("错误填充模式");
        } catch (IOException e) {
            LogPay.error("字节输出流异常");
        }
        return null;
    }

    /**
     * AES加密
     * 
     * @param plainBytes
     *            明文字节数组
     * @param keyBytes
     *            密钥字节数组
     * @param keyAlgorithm
     *            密钥算法
     * @param cipherAlgorithm
     *            加解密算法
     * @param iv
     *            随机向量
     * @return 加密后字节数组，不经base64编码
     * @throws ServiceException 异常处理
     */
    public static byte[] AESEncrypt(byte[] plainBytes, byte[] keyBytes, String keyAlgorithm, String cipherAlgorithm, String iv) throws ServiceException {
        byte[] encryptedBytes = null;
        try {
            if (keyBytes.length % 8 != 0 || keyBytes.length < 16 || keyBytes.length > 32) {
                LogPay.error("AES密钥长度不合法");
            }
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            SecretKey secretKey = new SecretKeySpec(keyBytes, keyAlgorithm);
            if (StringUtils.trimToNull(iv) != null) {
                IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            }
            encryptedBytes = cipher.doFinal(plainBytes);
        } catch (NoSuchAlgorithmException e) {
            LogPay.error(String.format("没有[%s]此类加密算法", cipherAlgorithm));
        } catch (NoSuchPaddingException e) {
            LogPay.error(String.format("没有[%s]此类填充模式", cipherAlgorithm));
        } catch (InvalidKeyException e) {
            LogPay.error("无效密钥");
        } catch (InvalidAlgorithmParameterException e) {
            LogPay.error("无效密钥参数");
        } catch (BadPaddingException e) {
            LogPay.error("错误填充模式");
        } catch (IllegalBlockSizeException e) {
            LogPay.error("加密块大小不合法");
        }

        return encryptedBytes;
    }

    /**
     * AES解密
     * 
     * @param encryptedBytes
     *            密文字节数组，不经base64编码
     * @param keyBytes
     *            密钥字节数组
     * @param keyAlgorithm
     *            密钥算法
     * @param cipherAlgorithm
     *            加解密算法
     * @param iv
     *            随机向量
     * @return 解密后字节数组
     * @throws ServiceException 异常
     */
    public static byte[] AESDecrypt(byte[] encryptedBytes, byte[] keyBytes, String keyAlgorithm, String cipherAlgorithm, String iv) throws ServiceException {
        try {
            // AES密钥长度为128bit、192bit、256bit，默认为128bit
            if (keyBytes.length % 8 != 0 || keyBytes.length < 16 || keyBytes.length > 32) {
                throw new ServiceException("AES密钥长度不合法");
            }

            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            SecretKey secretKey = new SecretKeySpec(keyBytes, keyAlgorithm);
            if (iv != null && StringUtils.trimToNull(iv) != null) {
                IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
                cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
            }

            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            return decryptedBytes;
        } catch (NoSuchAlgorithmException e) {
            LogPay.error(String.format("没有[%s]此类加密算法", cipherAlgorithm));
        } catch (NoSuchPaddingException e) {
            LogPay.error(String.format("没有[%s]此类填充模式", cipherAlgorithm));
        } catch (InvalidKeyException e) {
            LogPay.error("无效密钥");
        } catch (InvalidAlgorithmParameterException e) {
            LogPay.error("无效密钥参数");
        } catch (BadPaddingException e) {
            LogPay.error("错误填充模式");
        } catch (IllegalBlockSizeException e) {
            LogPay.error("加密块大小不合法");
        }
        return null;
    }

}
