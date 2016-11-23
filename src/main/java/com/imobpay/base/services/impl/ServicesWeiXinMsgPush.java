package com.imobpay.base.services.impl;

import java.text.MessageFormat;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.console.OuterConsoleColumn;
import com.imobpay.base.dao.TbvBranchParamDao;
import com.imobpay.base.dao.TbvCustomerDao;
import com.imobpay.base.dao.TbvSysParamDao;
import com.imobpay.base.dao.TbvTemplateNewsDao;
import com.imobpay.base.entity.TbvBranchParam;
import com.imobpay.base.entity.TbvCustomer;
import com.imobpay.base.entity.TbvSysParam;
import com.imobpay.base.entity.TbvTemplateNews;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.iface.BusinessInterface;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.util.EmptyChecker;
import com.imobpay.base.util.HttpsUtil;

/**
 * 
 * <pre>
 * 【类型】: ServicesWeiXinMsgPush <br/> 
 * 【作用】: 微信消息推送. <br/>  
 * 【时间】：2016年11月22日 上午11:19:00 <br/> 
 * 【作者】：HuaiYu.Wen <br/> 
 * </pre>
 */
@Service
public class ServicesWeiXinMsgPush implements BusinessInterface {

    /** 公众号管理表 */
    @Resource
    TbvBranchParamDao<TbvBranchParam>   tbvBranchParamDao;

    /**
     * 客户表
     */
    @Resource
    TbvCustomerDao<TbvCustomer>         tbvCusDao;
    /**
     * 用户参数表
     */
    @Resource
    TbvSysParamDao<TbvSysParam>         tbvParmDao;

    /** 公众号配置表 */
    @Resource
    TbvTemplateNewsDao<TbvTemplateNews> tbvTemDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String execute(String json) throws QTException{
        JSONObject reqJson = JSONObject.parseObject(json);
        EmptyChecker.checkEmpty(reqJson, OuterConsoleColumn.BRANCHID, OuterConsoleColumn.OPENID_X, OuterConsoleColumn.FIRSTVAL, OuterConsoleColumn.REMARKVAL, OuterConsoleColumn.WX_MSG_TEM_CONTENT_COUNT,
                OuterConsoleColumn.WX_MSG_TRADE_TYPE, OuterConsoleColumn.PUSH_URL);
        int count = reqJson.getIntValue(OuterConsoleColumn.WX_MSG_TEM_CONTENT_COUNT);
        for (int i = 1; i <= count; i++) {
            EmptyChecker.checkEmpty(reqJson, MessageFormat.format(OuterConsoleColumn.KEYWORDVAL, String.valueOf(i)));
        }
        String branchId = reqJson.getString(OuterConsoleColumn.BRANCHID);
        String openId = reqJson.getString(OuterConsoleColumn.OPENID_X);
        String firstVal = reqJson.getString(OuterConsoleColumn.FIRSTVAL);
        String remarkVal = reqJson.getString(OuterConsoleColumn.REMARKVAL);
        String wxMsgTemContentCount = reqJson.getString(OuterConsoleColumn.WX_MSG_TEM_CONTENT_COUNT);
        String wxMsgTradeType = reqJson.getString(OuterConsoleColumn.WX_MSG_TRADE_TYPE);
        String pushUrl = reqJson.getString(OuterConsoleColumn.PUSH_URL);
        /** 获取消息模板和ID和内容替换 */
        TbvTemplateNews tbvTem = new TbvTemplateNews();
        tbvTem.setBranchid(branchId);
        tbvTem.setDealtype(wxMsgTradeType);
        tbvTem.setNums(Integer.valueOf(wxMsgTemContentCount));
        TbvTemplateNews selectTem = tbvTemDao.selectById(tbvTem);
        if (EmptyChecker.isEmpty(selectTem)) {
            throw new QTException(Console_ErrCode.NOTE_NOTEMPLATECODE, Console_ErrCode.NOTE_NOTEMPLATEDESC);
        }
        String temId = selectTem.getTemplateid();
        String temContent = selectTem.getTemplate();
        temContent = temContent.replaceAll("firstVal", firstVal);
        temContent = temContent.replaceAll("remarkVal", remarkVal);
        for (int i = 1; i <= count; i++) {
            String keyword =  MessageFormat.format(OuterConsoleColumn.KEYWORDVAL, String.valueOf(i));
            temContent = temContent.replaceAll(keyword, reqJson.getString(keyword));
        }
        JSONObject j = new JSONObject();
        j.put(OuterConsoleColumn.TOUSER, openId);
        j.put(OuterConsoleColumn.TEMPLATE_ID, temId);
        j.put(OuterConsoleColumn.URL, pushUrl);
        j.put(OuterConsoleColumn.DATA, JSONObject.parse(temContent));
        /**** 获取推送的URL ****/
        TbvSysParam tbvParam = new TbvSysParam();
        tbvParam.setParamname("WxMsgSendUrl");
        TbvSysParam selParam = tbvParmDao.selectById(tbvParam);
        if (EmptyChecker.isEmpty(selParam)) {
            throw new QTException(Console_ErrCode.NOTE_NOPARAMCONFIGCODE, Console_ErrCode.NOTE_NOPARAMCONFIGDESC);
        }
        String sendUrl = selParam.getParamvalue();
        /** 获取公众号的token号 */
        TbvBranchParam tbranchParam = new TbvBranchParam();
        tbranchParam.setBranchid(branchId);
        TbvBranchParam selBranchParam = tbvBranchParamDao.selectById(tbranchParam);
        if (EmptyChecker.isEmpty(selBranchParam)) {
            throw new QTException(Console_ErrCode.NOTE_NOPARAMCONFIGCODE, Console_ErrCode.NOTE_NOPARAMCONFIGDESC);
        }
        String token = selBranchParam.getToken();
        sendUrl = sendUrl.replaceAll("tokenVal", token);
        LogPay.info("微信消息下推URl:" + sendUrl);
        LogPay.info("微信消息下推请求报文:" + j.toString());
        String method =Console_Column.EMPTY; 
        try {
            method = HttpsUtil.getMethod(sendUrl, j.toString());
        } catch (Exception e) {
            LogPay.error(e.getMessage(), e);
        }
        LogPay.info("微信消息下推返回报文:" + method);
        
        /** 组装成功信息返回 */
        JSONObject respJson = new JSONObject();
        respJson.put(Console_Column.MSG_CODE, Console_ErrCode.SUCCESS);
        respJson.put(Console_Column.MSG_TEXT, Console_ErrCode.SUCCESSDESC);
        return respJson.toString();
    }
}
