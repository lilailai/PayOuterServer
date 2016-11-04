/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: ServicesLogin.java .</br>
 *  Package Name:com.imobpay.base.services.impl .</br>
 *  Date      Author       Changes .</br>
 *  2016年6月13日   Lance.Wu     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.services.impl;

import java.net.URLEncoder;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.dao.TbvBranchParamDao;
import com.imobpay.base.dao.TbvCustomerDao;
import com.imobpay.base.dao.TbvPayjnlsDao;
import com.imobpay.base.dao.TbvSysParamDao;
import com.imobpay.base.entity.TbvBranchParam;
import com.imobpay.base.entity.TbvCustomer;
import com.imobpay.base.entity.TbvPayjnls;
import com.imobpay.base.entity.TbvSysParam;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.iface.BusinessInterface;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.services.util.PubWeiXin;
import com.imobpay.base.util.DateUtil;
import com.imobpay.base.util.EmptyChecker;
import com.imobpay.base.util.HttpHelper;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class ServicesWeiXinMsgPush implements BusinessInterface {
    /** 微信参数表 */
    @Resource
    TbvPayjnlsDao<TbvPayjnls>         tbvPayjnlsDao;
    /** 客户信息表 */
    @Resource
    TbvCustomerDao<TbvCustomer>       tbvCustomerDao;
    /** 机构信息表 */
    @Resource
    TbvBranchParamDao<TbvBranchParam> tbvBranchParamDao;
    /** 微信参数表 */
    @Resource
    TbvSysParamDao<TbvSysParam>       tbvSysParamDao;

    @Override
    public String execute(String reqParame) throws QTException {
        /** 接收请求报文  */
        JSONObject reqJson = JSONObject.parseObject(reqParame);
        EmptyChecker.checkEmptyJson(reqJson, Console_Column.REQMSGID, Console_Column.WX_MSG_TRADE_TYPE, Console_Column.WX_MSG_TEM_CONTENT_COUNT);

        /** 接收参数  */
        String reqMsgId = reqJson.getString(Console_Column.REQMSGID);
        String tradeType = reqJson.getString(Console_Column.WX_MSG_TRADE_TYPE);
        String contentCnt = reqJson.getString(Console_Column.WX_MSG_TEM_CONTENT_COUNT);

        try {
            /** 查流水   */
            TbvPayjnls tbvPayjnls = new TbvPayjnls();
            tbvPayjnls.setOrderId(reqMsgId);
            tbvPayjnls = tbvPayjnlsDao.selectById(tbvPayjnls);
            if (EmptyChecker.isEmpty(tbvPayjnls)) {
                throw new QTException(Console_ErrCode.TRANS_ERROR, "无此交易，请联系客服");
            }
            String fee = tbvPayjnls.getFee().toString();
            String amount = tbvPayjnls.getAmount().toString();
            String taaccount = tbvPayjnls.getTaaccount();
            String localdate = tbvPayjnls.getLocaldate();
            String localtime = tbvPayjnls.getLocaltime();
            String issuingAmount = tbvPayjnls.getIssuingAmount().toString();
            String tid = tbvPayjnls.getTid();
            String accountno = tbvPayjnls.getAccountno();
            String paytype = tbvPayjnls.getPaytype();
            String branchId = tbvPayjnls.getBranchid();
            String tradeTimes = localdate + localtime;
            String times = DateUtil.formatDate(tradeTimes, DateUtil.YYYYMMDDHHMMSS, "yyyy年MM月dd日 HH:mm:ss");
            amount = PubWeiXin.fromFenToYuan(amount);
            fee = PubWeiXin.fromFenToYuan(fee);
            issuingAmount = PubWeiXin.fromFenToYuan(issuingAmount);

            TbvCustomer tbvCustomer = new TbvCustomer();
            tbvCustomer.setTid(tid);
            tbvCustomer = tbvCustomerDao.selectById(tbvCustomer);
            if (EmptyChecker.isEmpty(tbvCustomer)) {
                throw new QTException(Console_ErrCode.TRANS_ERROR, "无此交易，请联系客服");
            }
            String openid = tbvCustomer.getOpenid();

            /** 查询公众号描述 */
            TbvBranchParam tbvBranchParam = new TbvBranchParam();
            tbvBranchParam = tbvBranchParamDao.selectById(tbvBranchParam);
            if (EmptyChecker.isEmpty(tbvBranchParam)) {
                throw new QTException(Console_ErrCode.TRANS_ERROR, "交易异常，请联系客服");
            }
            String taDesc = tbvBranchParam.getTadesc();
            /** 收款  */
            JSONObject firstValJson = new JSONObject();
            if ("WxMsgTrade".equals(tradeType)) {
                firstValJson.put("firstVal", "您的" + taDesc + taaccount + "收到一笔消费\n");
                firstValJson.put("keyword1Val", taDesc + "消费");
                firstValJson.put("keyword2Val", times);
                firstValJson.put("keyword3Val", amount + "元 ");
                firstValJson.put("wxMsgTradeType", tradeType);
            } else if ("WxMsgAccount".equals(tradeType)) {
                String userName = tbvCustomer.getUsername();
                int len = accountno.length();
                accountno = accountno.substring(len - 4, len);
                firstValJson.put("firstVal", "尊敬的" + userName + "先生/女士:\n您尾号" + accountno + "的银行卡最新交易信息\n");
                firstValJson.put("keyword1Val", times);
                firstValJson.put("keyword2Val", "转存");
                firstValJson.put("keyword3Val", issuingAmount + "元 ");
                firstValJson.put("wxMsgTradeType", tradeType);
            }
            firstValJson.put("remarkVal", "\n交易成功，感谢您使用" + taDesc + "！\n");
            firstValJson.put("branchId", branchId);
            firstValJson.put("JYM", "TAWeiXinMsgPush");
            firstValJson.put("openId", openid);
            firstValJson.put("wxMsgTemContentCount", contentCnt);

            /** 查询参数表TBV_SYS_PARAM-PUSHMSGURL */
            TbvSysParam tbvSysParam = new TbvSysParam();
            tbvSysParam.setParamname("PUSHMSGURL");
            tbvSysParam = tbvSysParamDao.selectById(tbvSysParam);
            if (EmptyChecker.isEmpty(tbvSysParam)) {
                LogPay.error("数据配置异常：未配置参数PUSHMSGURL");
                throw new QTException(Console_ErrCode.SYSERROR, Console_ErrCode.NO_DBPARAM);
            }
            String pushUrlValue = tbvSysParam.getParamvalue();
            if (EmptyChecker.isEmpty(pushUrlValue)) {
                LogPay.error("数据配置异常：未配置参数PUSHMSGURL");
                throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "系统异常");
            }
            pushUrlValue = pushUrlValue.replace("tradeTimeVal", tradeTimes);
            pushUrlValue = pushUrlValue.replace("orderIdVal", reqMsgId);
            pushUrlValue = pushUrlValue.replace("amtVal", amount);
            pushUrlValue = pushUrlValue.replace("feeVal", fee);
            pushUrlValue = pushUrlValue.replace("taaccountVal", taaccount);
            String desc = "";
            if ("21".equals(paytype)) {
                desc = "支付宝支付";
            } else {
                desc = "微信支付";
            }
            pushUrlValue = pushUrlValue.replace("paytypeVal", desc);
            pushUrlValue = pushUrlValue.replace("statusVal", "成功");
            int index = pushUrlValue.indexOf("?");
            String params = URLEncoder.encode(pushUrlValue.substring(index + 1), "UTF-8");
            String url = pushUrlValue.substring(0, index);
            firstValJson.put("pushUrl", url + "?" + params);

            /** 查询参数表TBV_SYS_PARAM-PAYTACARDURL */
            tbvSysParam.setParamname("PAYTACARDURL");
            tbvSysParam = tbvSysParamDao.selectById(tbvSysParam);
            if (EmptyChecker.isEmpty(tbvSysParam)) {
                LogPay.error("数据配置异常：未配置参数PAYTACARDURL");
                throw new QTException(Console_ErrCode.SYSERROR, Console_ErrCode.NO_DBPARAM);
            }
            String payServerUrl = tbvSysParam.getParamvalue();
            String method = HttpHelper.post(payServerUrl, firstValJson.toString(), "UTF-8", "UTF-8");
            LogPay.info("微信消息下推返回报文:" + method);
        } catch (Exception e) {
            LogPay.error(e.getMessage(), e);
            throw new QTException(Console_ErrCode.RESP_CODE_99_ERR_UNKNOW, "系统异常,请稍后重试!");
        }
        return "";

    }

}
