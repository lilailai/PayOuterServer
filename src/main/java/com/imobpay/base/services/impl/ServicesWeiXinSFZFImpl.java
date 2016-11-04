package com.imobpay.base.services.impl;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.dao.TbvSysParamDao;
import com.imobpay.base.entity.TbvSysParam;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.iface.BusinessInterface;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.services.util.PubWeiXin;
import com.imobpay.base.util.EmptyChecker;

/** 
 * ClassName: ServicesWeiXinSFZFImpl <br/> 
 * Function: 微信扫码支付接口. <br/> 
 * date: 2016年10月24日 下午2:56:35 <br/> 
 * @author CAOWENJUN
 * @version  
 * @since JDK 1.6 
 */
@Service
public class ServicesWeiXinSFZFImpl implements BusinessInterface {
    /** 微信参数表 */
    @Resource
    TbvSysParamDao<TbvSysParam> tbvSysParamDao;

    @Override
    public String execute(String reqParame) throws QTException {
        JSONObject reqJson = JSONObject.parseObject(reqParame);
        EmptyChecker.checkEmptyJson(reqJson, Console_Column.SCENE, Console_Column.TOTALAMOUNT, Console_Column.REQMSGID, Console_Column.MERCHANTCODE, Console_Column.SUBJECT, Console_Column.MSGTYPE,
                Console_Column.REQDATE, Console_Column.PAYWAY, Console_Column.SHOPNAME, Console_Column.TOTALFEE);

        /** 获取参数 */
        String scene = reqJson.getString(Console_Column.SCENE);
        String totalAmount = reqJson.getString(Console_Column.TOTALAMOUNT);
        String reqMsgId = reqJson.getString(Console_Column.REQMSGID);
        String merchantCode = reqJson.getString(Console_Column.MERCHANTCODE);
        String subject = reqJson.getString(Console_Column.SUBJECT);
        String msgType = reqJson.getString(Console_Column.MSGTYPE);
        String reqDate = reqJson.getString(Console_Column.REQDATE);
        String payWay = reqJson.getString(Console_Column.PAYWAY);
        String shopname = reqJson.getString(Console_Column.SHOPNAME);
        String totalFee = reqJson.getString(Console_Column.TOTALFEE);

        /** 查询参数表TBV_SYS_PARAM-报文版本号 */
        TbvSysParam tbvSysParamVersion = new TbvSysParam();
        tbvSysParamVersion.setParamname("RYXVERSION");
        tbvSysParamVersion = tbvSysParamDao.selectById(tbvSysParamVersion);
        if (EmptyChecker.isEmpty(tbvSysParamVersion)) {
            LogPay.error("数据配置异常：未配置参数RYXVERSION");
            throw new QTException(Console_ErrCode.SYSERROR, Console_ErrCode.NO_DBPARAM);
        }
        String versionNo = tbvSysParamVersion.getParamvalue();

        /** 发送数据 */
        JSONObject json = new JSONObject(true);
        json.put("version", versionNo);
        json.put("msgType", msgType);
        json.put("reqDate", reqDate);
        JSONObject reqDatas = new JSONObject(true);
        reqDatas.put("merchantCode", merchantCode);
        reqDatas.put("payWay", payWay);
        reqDatas.put("scene", scene);
        reqDatas.put("desc", shopname);
        reqDatas.put("totalFee", PubWeiXin.fromFenToYuan(totalFee));
        reqDatas.put("totalAmount", PubWeiXin.fromFenToYuan(totalAmount));
        reqDatas.put("subject", subject);
        json.put("data", reqDatas);
        LogPay.info("SMZF100001请求数据：" + json.toString());

        /** 查询参数表TBV_SYS_PARAM-获取URL */
        TbvSysParam tbvSysParamUrl = new TbvSysParam();
        tbvSysParamUrl.setParamname("WXZFURL");
        tbvSysParamUrl = tbvSysParamDao.selectById(tbvSysParamUrl);
        if (EmptyChecker.isEmpty(tbvSysParamUrl)) {
            LogPay.error("数据配置异常：未配置参数WXZFURL");
            throw new QTException(Console_ErrCode.SYSERROR, Console_ErrCode.NO_DBPARAM);
        }
        String wxzfUrl = tbvSysParamUrl.getParamvalue();

        /** 查询参数表TBV_SYS_PARAM-请求参数 */
        TbvSysParam tbvSysParamReqPara = new TbvSysParam();
        tbvSysParamReqPara.setParamname("REQPARAM");
        tbvSysParamReqPara = tbvSysParamDao.selectById(tbvSysParamReqPara);
        if (EmptyChecker.isEmpty(tbvSysParamReqPara)) {
            LogPay.error("数据配置异常：未配置参数REQPARAM");
            throw new QTException(Console_ErrCode.SYSERROR, Console_ErrCode.NO_DBPARAM);
        }
        String callBackUrl = tbvSysParamReqPara.getParamvalue();

        /**瑞银信的公钥*/
        TbvSysParam tbvSysParam = new TbvSysParam();
        tbvSysParam.setParamname("PUBLICKKEY");
        tbvSysParam = tbvSysParamDao.selectById(tbvSysParam);
        if (EmptyChecker.isEmpty(tbvSysParam)) {
            LogPay.error("数据配置异常：未配置参数PUBLICKKEY");
            throw new QTException(Console_ErrCode.SYSERROR, Console_ErrCode.NO_DBPARAM);
        }
        String publickKey = tbvSysParam.getParamvalue();

        /** 私钥 */
        tbvSysParam.setParamname("PRIVATEKEY");
        tbvSysParam = tbvSysParamDao.selectById(tbvSysParam);
        if (EmptyChecker.isEmpty(tbvSysParam)) {
            LogPay.error("数据配置异常：未配置参数PRIVATEKEY");
            throw new QTException(Console_ErrCode.SYSERROR, Console_ErrCode.NO_DBPARAM);
        }
        String privateKey = tbvSysParam.getParamvalue();

        /** 获取公私钥对象*/
        Map<String, Object> keyMaps = new HashMap<String, Object>();
        keyMaps.put("publickKey", publickKey);
        keyMaps.put("privateKey", privateKey);
        Map<String, Object> keys = PubWeiXin.getKeys(keyMaps);
        PrivateKey hzfPriKey = (PrivateKey) keys.get("hzfPriKey");
        PublicKey yhPubKey = (PublicKey) keys.get("yhPubKey");

        /** 需要加密的数据集*/
        Map<String, Object> datas = new HashMap<String, Object>();
        /** 加密数据 */
        datas.put("reqData", json);
        datas.put("reqMsgId", reqMsgId);
        datas.put("tranCode", "SMZF002");
        datas.put("hzfPriKey", hzfPriKey);
        datas.put("yhPubKey", yhPubKey);
        datas.put("wxzfUrl", wxzfUrl);
        datas.put("callBackUrl", callBackUrl);

        /** 加密并发送数据*/
        String retData = PubWeiXin.postData(datas);
        /** 解密数据*/
        String analyData = PubWeiXin.analyData(retData, hzfPriKey);

        /** 处理返回结果 */
        JSONObject resultData = JSONObject.parseObject(analyData);
        LogPay.info("SMZF100001返回结果：" + resultData.toString());
        String resultCode = resultData.getString("respCode");
        JSONObject retJson = new JSONObject();
        if ("000000".equals(resultCode)) {
            String data = resultData.getString("data");
            JSONObject dataJson = JSONObject.parseObject(data);
            retJson.put("qrCode", dataJson.getString("qrCode"));
            retJson.put(Console_Column.P_MSG_CODE, "0000");
            retJson.put(Console_Column.P_MSG_TEXT, "成功");
        } else {
            retJson.put(Console_Column.P_MSG_CODE, "9999");
            retJson.put(Console_Column.P_MSG_TEXT, "交易异常");
        }
        retJson.put("REQMSGID", reqMsgId);
        return retJson.toString();
    }

}
