/**
 *  Project Name:PayUrlShort
 *  File: ShortUrlServerImpl.java
 *  Package Name:com.imobpay.base.services.impl
 *  Date      Author       Changes
 *  2016年7月27日   madman     Create
 *  Description:
 *  Copyright 2014-2015 QIANTUO FINANCE Services Co.,Ltd. All rights reserved.
 */
package com.imobpay.base.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.console.OuterConsoleColumn;
import com.imobpay.base.dao.PrepClientVersionDao;
import com.imobpay.base.dao.TbvCardDao;
import com.imobpay.base.dao.TbvSysParamDao;
import com.imobpay.base.entity.Param;
import com.imobpay.base.entity.PrepClientVersion;
import com.imobpay.base.entity.TbvCard;
import com.imobpay.base.entity.TbvSysParam;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.iface.BusinessInterface;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.util.DateUtil;
import com.imobpay.base.util.Des3Util;
import com.imobpay.base.util.EmptyChecker;
import com.imobpay.base.util.EncoderQRCoder;
import com.imobpay.base.util.Format;
import com.imobpay.base.util.FtpUtil;
import com.imobpay.base.util.MD5;

/**
 * 
 * <pre>
 * 【类型】: ServicesGetPayPicUrl <br/> 
 * 【作用】: 获取用户的瑞玛. <br/>  
 * 【时间】：2016年11月14日 下午3:00:42 <br/> 
 * 【作者】：HuaiYu.Wen <br/> 
 * </pre>
 */
@Service
public class ServicesGetPayPicUrl implements BusinessInterface {
    /**
     * TACARD表
     * */
    @Resource
    TbvCardDao<TbvCard>                         tbvCardDao;
    /**
     * 参数表
     * */
    @Resource
    TbvSysParamDao<TbvSysParam>                 tbvSysParame;
    /**
     * 客户端版本表
     */
    @Resource
    PrepClientVersionDao<PrepClientVersion>     clientDao;

    @Override
    public String execute(String json) throws QTException {
        JSONObject reqJson = JSONObject.parseObject(json);
        EmptyChecker.checkEmpty(reqJson, Console_Column.CUSTOMERID, Console_Column.APPUSER);
        String customeId = reqJson.getString(Console_Column.CUSTOMERID);
        String appuser = reqJson.getString(Console_Column.APPUSER);

        /**
         * 修改人:文怀宇
         * 修改时间:2016年10月21日17:20:02
         * 修改描述:生产二维码图片时判断在数据库中是否配置该appuser，且生成二维码规则变更为 customeId + appuser
         */
        Map<String, String> map = retAppUserParam(appuser);

        /** 判断appuser是否在map中 */
        if (map.containsKey(appuser)) {
            customeId = customeId + appuser;
        }

        // 验证短信验证码
        PrepClientVersion pcv = new PrepClientVersion();
        pcv.setAppuser(appuser);
        PrepClientVersion selectPcv = clientDao.selectById(pcv);
        if (EmptyChecker.isEmpty(selectPcv)) {
            throw new QTException(Console_ErrCode.NOTE_NOBRANCHIDCODE, Console_ErrCode.NOTE_NOBRANCHIDDESC);
        }
        JSONObject respJson = new JSONObject();
        // 优先判断TA卡号是否存在
        TbvCard t = new TbvCard();
        t.setTaaccount(customeId);
        TbvCard taCard = tbvCardDao.selectById(t);
        if (EmptyChecker.isEmpty(taCard)) {
            TbvSysParam tp = new TbvSysParam();
            List<TbvSysParam> list = tbvSysParame.list(tp);
            Param param = new Param(list);
            String httpUrl = param.getHttpURL();
            String ftpUrl = param.getFtpUrl();
            String ftpName = param.getFtpName();
            String ftpPass = param.getFtpPass();
            String cusPayFileDir = param.getCusPayPicDir();
            String picFillePre = param.getPicFilePre();
            String ruiMaFeeId = param.getRuiMaFeeid();

            /**
             * 修改人:文怀宇
             * 修改时间:2016年10月21日17:33:45
             * 修改描述:若该appuser为配置的appuser，则获取该appuser对应的FeeId
             */
            /** 判断appuser是否在map中 */
            if (map.containsKey(appuser)) {
                ruiMaFeeId = map.get(appuser);
            }

            int ftpPort = Integer.parseInt(param.getFtpPort());
            /** 获取图片url */
            String pricUrl = param.getPicURL();
            LogPay.info("瑞码的费率ID:" + ruiMaFeeId);
            LogPay.info("参数httpUrl:" + httpUrl);
            LogPay.info("参数ftpUrl:" + ftpUrl);
            LogPay.info("参数ftPort:" + ftpPort);
            LogPay.info("参数ftpName:" + ftpName);
            LogPay.info("参数ftpPass:" + ftpPass);
            LogPay.info("客户编号customeId:" + customeId);
            LogPay.info("手刷二维码目录cusPayFileDir:" + cusPayFileDir);
            LogPay.info("写文件前缀号cusPayFileDirUrl:" + picFillePre);
            LogPay.info("客户编号pricUrl:" + pricUrl);
            if (EmptyChecker.isEmpty(httpUrl, ftpUrl, ftpPort, ftpName, ftpPass, customeId, cusPayFileDir, pricUrl, ruiMaFeeId)) {
                throw new QTException(Console_ErrCode.NOTE_NOPARAMCONFIGCODE, Console_ErrCode.NOTE_NOPARAMCONFIGDESC);
            }
            cusPayFileDir = cusPayFileDir + MD5.md5(DateUtil.getCurrDate() + param.getDesKey()).toUpperCase() + "/";
            LogPay.info("手刷二维码目录cusPayFileDir:" + cusPayFileDir);
            String accounMac=Console_Column.EMPTY;
            try {
                accounMac = Des3Util.Encrypt2(customeId, MD5.md5(param.getDesKey()), MD5.md5(param.getDesYan()).substring(0, 8).getBytes());
            } catch (Exception e) {
                LogPay.error("加密失败");
                throw new QTException(Console_ErrCode.NOTE_CREATETWOCODE, Console_ErrCode.NOTE_CREATETWODESC);
            }
            LogPay.info("客户编号加密数据accounMac:" + accounMac);
            String picContext = httpUrl + accounMac;
            LogPay.info("图片内容地址:" + picContext);
            /** 生成二维码图片 */
            byte[] encoderQRCoder = EncoderQRCoder.encoderQRCoder(picContext);
            String fileName = MD5.md5(accounMac).toUpperCase() + ".png";
            LogPay.info("文件名:" + fileName);
            boolean ftpPic = FtpUtil.FtpPic(ftpUrl, ftpPort, ftpName, ftpPass, picFillePre + cusPayFileDir, Format.encoderBASE64(encoderQRCoder), fileName);
            if (!ftpPic) {
                throw new QTException(Console_ErrCode.NOTE_CREATETWOCODE, Console_ErrCode.NOTE_CREATETWODESC);
            }
            String branchid = selectPcv.getBranchid() == null ? param.getCreateBranch() : selectPcv.getBranchid();
            String ftpurl = pricUrl + cusPayFileDir + fileName;
            TbvCard tcard = new TbvCard();
            tcard.setTaaccount(customeId);
            tcard.setUrl(ftpurl);
            tcard.setBranchid(branchid);
            tcard.setCreatedate(DateUtil.getCurrDate(new Date(), DateUtil.YYYYMMDDHHMMSS));
            tcard.setTaaccountmac(accounMac);
            tcard.setStatus("1");
            /** 0未绑定 1 已绑定 手刷用户默认是绑定的*/
            tcard.setBindflag("1");
            /** 0 微信端 1手刷端*/
            tcard.setType("1");
            /** 100002 微信TA卡默认费率*/
            /** 100003 App扫码默认费率*/
            tcard.setFeeid(ruiMaFeeId);
            tbvCardDao.insert(tcard);
            respJson.put("payPicUrl", ftpurl);
        } else {
            respJson.put("payPicUrl", taCard.getUrl());
        }
        
        /** 组装成功信息返回 */
        JSONObject retJson = new JSONObject();
        retJson.put(Console_Column.RESULTBEAN, respJson.toString());
        retJson.put(Console_Column.MSG_CODE, Console_ErrCode.SUCCESS);
        retJson.put(Console_Column.MSG_TEXT, Console_ErrCode.SUCCESSDESC);
        return retJson.toString();
        
    }

    /**
     * 
     * 【方法名】    :retAppUserParam. <br/> 
     * 【作用】: 初始化配置的appUser信息，.<br/> 
     * 【作者】: HuaiYu.Wen .<br/>
     * 【时间】： 2016年10月21日 下午5:07:40 .<br/>
     * 【参数】： .<br/>
     * @param appUser               应用类型 .<br/>
     * @return Map<String, String>  应用类型map集合 .<br/>
     * @throws QTException .<br/>
     */
    public Map<String, String> retAppUserParam(String appUser) throws QTException {
        /** 返回map对象 */
        Map<String, String> map = new HashMap<String, String>();

        /** 判断传入的appUser是否为空 */
        if (EmptyChecker.isEmpty(appUser)) {
            return map;
        }

        /** 查询数据库配置信息 */
        TbvSysParam bean = new TbvSysParam();
        bean.setParamname(OuterConsoleColumn.VIPAPPUSER);
        TbvSysParam param = tbvSysParame.selectById(bean);

        /** 判断是否非空 */
        if (EmptyChecker.isEmpty(param)) {
            throw new QTException(Console_ErrCode.NOTE_NOPARAMCONFIGCODE, Console_ErrCode.NOTE_NOPARAMCONFIGDESC);
        }

        /** 获取配置值 */
        String paramVal = param.getParamvalue();

        /** 以逗号分隔 */
        String[] appUsers = paramVal.split(",");

        /** 遍历所有配置的appUser */
        for (String str : appUsers) {
            /** 以竖线分隔字符串 */
            String[] vals = str.split("\\|");
            String tempAppUser = vals[0];
            String tempFeeId = vals[1];

            /** 如果传入的appUser在数据库中配置了 */
            if (appUser.equals(tempAppUser)) {
                map.put(tempAppUser, tempFeeId);
                break;
            }
        }
        return map;
    }
}
