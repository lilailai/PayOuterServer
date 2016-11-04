package com.imobpay.base.config;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Arrays;

import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.util.Hex;
import com.imobpay.base.util.Tools;

/**
 * ClassName: SendEncrypt <br/>
 * Function: 发送加密机数据 <br/>
 * Reason: ADD REASON(可选). <br/>
 * date: 2015年9月18日 下午12:30:11 <br/>
 * 
 * @author Lance.Wu
 * @version
 * @since JDK 1.6 UserServer 1.0
 */
public class SendEncrypt {

    /**
     * @方法名称:send
     * @方法作用：用户密码加密
     * @方法条件：
     * @方法流程：
     * @作者:Lance.Wu
     * @param mobile
     *            手机号
     * @param content
     *            密码
     * @param sec
     *            配置对像
     * @return 返回结果
     * @throws QTException
     *             异常信息
     */
    public static String send(String mobile, String content, PropertiesConfig sec) throws QTException {

        Socket server = null;
        InputStream input = null;
        OutputStream output = null;
        try {

            server = new Socket(sec.getUrl(), sec.getPort());
            server.setSoTimeout(sec.getTimeOut());
            input = server.getInputStream();
            output = server.getOutputStream();
            /** 拼接报文内容 */
            String sendContent = AssembleData(mobile, content, sec);
            LogPay.info("发送加密机之前内容：>>" + sendContent);
            output.write(sendContent.getBytes(sec.getEncode()));
            Arrays.toString(sendContent.getBytes());
            output.flush();
            byte[] byLen = new byte[sec.getRecLen()];
            input.read(byLen);
            String dataLen = Hex.toString(byLen);
            byte[] data = new byte[Integer.parseInt(dataLen, sec.getLenType())];
            input.read(data);
            String recData = new String(data, sec.getEncode());
            LogPay.info("加密之后内容：" + recData);
            String recResult = recData.substring(2, 4);
            LogPay.info("加加密机返回内容：" + recData);
            if (!sec.getResult().equals(recResult)) {
                LogPay.error("接收加密机数据异常");
                throw new QTException(Console_ErrCode.BUSS_EMPTY, Console_ErrCode.SYSNOSERVEDESC);
            }
            return recData.substring(4).toUpperCase();
        } catch (UnsupportedEncodingException e) {
            LogPay.error("发送机密机数据组装异常：" + e.getMessage(), e);
            throw new QTException(Console_ErrCode.BUSS_EMPTY, Console_ErrCode.SYSNOSERVEDESC);
        } catch (Exception e) {
            LogPay.error("发送机密机数据异常：" + e.getMessage(), e);
            throw new QTException(Console_ErrCode.BUSS_EMPTY, Console_ErrCode.SYSNOSERVEDESC);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (Exception e2) {
                LogPay.error("调用加密机异常后关闭流异常" + e2.getMessage(), e2);
            }
        }

    }

    /**
     * 
     * AssembleData:(组装数据). <br/>
     * 
     * @author Lance.Wu
     * @param mobile
     *            电话
     * @param content
     *            内容
     * @param sec
     *            发送对像
     * @throws UnsupportedEncodingException
     *             异常信息
     * @return 返回结果：String
     * @since JDK 1.6 UserServer 1.0
     */
    private static String AssembleData(String mobile, String content, PropertiesConfig sec) throws UnsupportedEncodingException {
        /** 拼接报文内容 */
        StringBuffer expressSb = new StringBuffer();

        expressSb.append(sec.getAddHeadData()).append(mobile.length()).append(mobile);
        expressSb.append(content.length() / 2).append(content).append(sec.getAddTrailData());
        /** 获取发送内容的十六制字符串 */
        String readydata = Hex.toString(expressSb.toString().getBytes(sec.getEncode()));
        String dataLen = Integer.toHexString(readydata.length() / 2 + sec.getAddLen());
        dataLen = Tools.leftFill(dataLen, sec.getSendlen(), sec.getSendlenFill());
        StringBuffer sendDataSb = new StringBuffer(dataLen);
        sendDataSb.append(readydata).append(sec.getAddDataTail());
        String data = new String(Hex.String2Hexbyte(sendDataSb.toString()));
        return data;
    }
}
