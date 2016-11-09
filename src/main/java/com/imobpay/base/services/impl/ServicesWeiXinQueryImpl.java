package com.imobpay.base.services.impl;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.dao.TbvSysParamDao;
import com.imobpay.base.entity.TbvSysParam;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.iface.BusinessInterface;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.services.util.EmptyChecker;
import com.imobpay.base.services.util.PubWeiXin;

/**
 * ClassName: ServicesWeiXinQueryImpl <br/>
 * Function: 微信查询订单接口. <br/>
 * date: 2016年10月24日 下午2:56:35 <br/>
 * 
 * @author CAOWENJUN
 * @version
 * @since JDK 1.6
 */
@Service
public class ServicesWeiXinQueryImpl implements BusinessInterface {
    /** 微信参数表 */
    @Resource
    TbvSysParamDao<TbvSysParam> tbvSysParamDao;
    /** 上下文对象 */
    @Resource
    private ApplicationContext  applicationContext;

    @Override
    public String execute(String reqParame) throws QTException {
        /** 参数处理 */
        JSONObject reqJson = JSONObject.parseObject(reqParame);
        EmptyChecker.checkEmptyJson(reqJson, Console_Column.ORI_REQMSGID, Console_Column.MSGTYPE, Console_Column.REQDATE);

        /** 接收参数 */
        String oriReqMsgId = reqJson.getString(Console_Column.ORI_REQMSGID);
        String msgType = reqJson.getString(Console_Column.MSGTYPE);
        String reqDate = reqJson.getString(Console_Column.REQDATE);

        /** 查询参数表TBV_SYS_PARAM-报文版本号 */
        TbvSysParam tbvSysParamVersion = new TbvSysParam();
        tbvSysParamVersion.setParamname("RYXVERSION");
        tbvSysParamVersion = tbvSysParamDao.selectById(tbvSysParamVersion);
        if (EmptyChecker.isEmpty(tbvSysParamVersion)) {
            LogPay.error("数据配置异常：未配置参数RYXVERSION");
            throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "未知系统异常");
        }
        String versionNo = tbvSysParamVersion.getParamvalue();

        /** 发送数据 */
        JSONObject json = new JSONObject(true);
        json.put("version", versionNo);
        json.put("msgType", msgType);
        json.put("reqDate", reqDate);

        JSONObject reqDatas = new JSONObject(true);
        reqDatas.put("oriReqMsgId", oriReqMsgId);
        LogPay.info("SMZF100002发送报文：" + reqDatas.toString());
        json.put("data", reqDatas);

        /** 瑞银信的公钥 */
        TbvSysParam tbvSysParam = new TbvSysParam();
        tbvSysParam.setParamname("PUBLICKKEY");
        tbvSysParam = tbvSysParamDao.selectById(tbvSysParam);
        if (EmptyChecker.isEmpty(tbvSysParam)) {
            LogPay.error("数据配置异常：未配置参数PUBLICKKEY");
            throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "未知系统异常");
        }
        String publickKey = tbvSysParam.getParamvalue();

        /** 私钥 */
        tbvSysParam.setParamname("PRIVATEKEY");
        tbvSysParam = tbvSysParamDao.selectById(tbvSysParam);
        if (EmptyChecker.isEmpty(tbvSysParam)) {
            LogPay.error("数据配置异常：未配置参数PRIVATEKEY");
            throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "未知系统异常");
        }
        String privateKey = tbvSysParam.getParamvalue();

        /** 查询参数表TBV_SYS_PARAM-获取URL */
        TbvSysParam tbvSysParamUrl = new TbvSysParam();
        tbvSysParamUrl.setParamname("WXZFURL");
        tbvSysParamUrl = tbvSysParamDao.selectById(tbvSysParamUrl);
        if (EmptyChecker.isEmpty(tbvSysParamUrl)) {
            LogPay.error("数据配置异常：未配置参数WXZFURL");
            throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "未知系统异常");
        }
        String wxzfUrl = tbvSysParamUrl.getParamvalue();

        /** 查询参数表TBV_SYS_PARAM-请求参数 */
        TbvSysParam tbvSysParamReqPara = new TbvSysParam();
        tbvSysParamReqPara.setParamname("REQPARAM");
        tbvSysParamReqPara = tbvSysParamDao.selectById(tbvSysParamReqPara);
        if (EmptyChecker.isEmpty(tbvSysParamReqPara)) {
            LogPay.error("数据配置异常：未配置参数REQPARAM");
            throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "未知系统异常");
        }
        String callBackUrl = tbvSysParamReqPara.getParamvalue();
        /** 获取公私钥对象 */
        Map<String, Object> keyMaps = new HashMap<String, Object>();
        keyMaps.put("publickKey", publickKey);
        keyMaps.put("privateKey", privateKey);
        Map<String, Object> keys = PubWeiXin.getKeys(keyMaps);
        PrivateKey hzfPriKey = (PrivateKey) keys.get("hzfPriKey");
        PublicKey yhPubKey = (PublicKey) keys.get("yhPubKey");

        /** 需要加密的数据集 */
        Map<String, Object> datas = new HashMap<String, Object>();
        /** 加密数据 */
        datas.put("reqData", json);
        datas.put("reqMsgId", oriReqMsgId);
        datas.put("tranCode", "SMZF006");
        datas.put("hzfPriKey", hzfPriKey);
        datas.put("yhPubKey", yhPubKey);
        datas.put("wxzfUrl", wxzfUrl);
        datas.put("callBackUrl", callBackUrl);
        /** 加密并发送数据 */
        String retData = PubWeiXin.postData(datas);
        /** 解密数据 */
        String analyData = PubWeiXin.analyData(retData, hzfPriKey);

        /** 处理返回结果 */
        JSONObject resultData = JSONObject.parseObject(analyData);
        LogPay.info("SMZF100002返回结果：" + resultData.toString());
        String resultCode = resultData.getString("respCode");
        String resultDesc = resultData.getString("respMsg");
        JSONObject retJson = new JSONObject();
        retJson.put("resultCode", resultCode);
        if ("000000".equals(resultCode)) {
            JSONObject data = (JSONObject) resultData.get("data");
            String oriRespType = data.getString("oriRespType");
            String totalAmount = data.getString("totalAmount");
            retJson.put("oriRespType", oriRespType);
            retJson.put("totalAmount", totalAmount);
            retJson.put(Console_Column.P_MSG_CODE, "0000");
            retJson.put(Console_Column.P_MSG_TEXT, "成功");
        } else {
            retJson.put(Console_Column.P_MSG_CODE, resultCode);
            retJson.put(Console_Column.P_MSG_TEXT, resultDesc);
        }
        return retJson.toString();
    }
}
