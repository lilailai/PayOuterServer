/**
 *  <pre>	
 *  Project Name:UserDServer .</br>
 *  File: UserDServerBuesiness.java .</br>
 *  Package Name:com.imobpay.base.services .</br>
 *  Date      Author       Changes .</br>
 *  2016年5月30日   Lance.Wu     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.config.JymFindServerConfig;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.entity.ReultErrorBean;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.iface.BusinessInterface;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.util.EmptyChecker;
import com.imobpay.base.util.Tools;

/**
 * 
 * <pre>
 * 【类型】: WeiXinServerImpl <br/> 
 * 【作用】: 微信支付实现类 <br/>  
 * 【时间】：2016年10月14日 下午8:24:01 <br/> 
 * 【作者】：madman <br/>
 * </pre>
 */
@Service
public class PayOuterServerImpl implements PayOuterServer {

    /** Spring对像 */
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 描述：功能藐视：当前方法实现方法.<br/>
     * 创建人：Lance.Wu .<br/>
     * 创建时间：2016年6月27日 下午5:35:58 .<br/>
     * 
     * @param json
     *            请求参数
     * @return 返回结果
     * @see com.imobpay.base.services.UserServer#executeJson(java.lang.String)
     */
    @Override
    public String execute(String json) {
        long l = System.currentTimeMillis();
        String resultJson = "";
        Object jym = null;
        try {
            JSONObject param = JSONObject.parseObject(json);
            jym = param.get(Console_Column.SMS_SERVERJYM);
            String logKey = param.get(Console_Column.LOGKEYALL) + "";
            if (EmptyChecker.isEmpty(logKey) || "null".equals(logKey)) {
                logKey = Tools.getOnlyPK();
            }
            Thread.currentThread().setName(logKey);
            LogPay.info("请求内容:" + json);

            /** 判断交易码 */
            if (EmptyChecker.isEmpty(jym)) {
                throw new QTException(Console_ErrCode.RESP_CODE_03_ERR_PRARM, Console_ErrCode.JYMNULLDESC);
            }

            String jymStr = JymFindServerConfig.getServerObject(jym.toString());

            if (EmptyChecker.isEmpty(jymStr)) {
                LogPay.error("未配置[JymFindServerConfig]中的交易码");
                throw new QTException(Console_ErrCode.RESP_CODE_03_ERR_PRARM, Console_ErrCode.TRANS_ERROR);
            }
            Object serverObj = applicationContext.getBean(jymStr.trim());
            if (EmptyChecker.isEmpty(serverObj)) {
                LogPay.error("[未定义" + serverObj + "]的对像或者没有注解");
                throw new QTException(Console_ErrCode.RESP_CODE_88_ERR_TXN, Console_ErrCode.TRANS_ERROR);
            }
            BusinessInterface bean = (BusinessInterface) serverObj;
            resultJson = bean.execute(json);
        } catch (QTException e) {
            LogPay.error(e.getMessage(), e);
            String reutlBeanType = JymFindServerConfig.getServerResultType(jym);
            ReultErrorBean serverObj = (ReultErrorBean) applicationContext.getBean(reutlBeanType);
            resultJson = serverObj.returnBeanJson(e.getRespCode(), e.getRespMsg());
        } catch (Exception e) {
            LogPay.error(e.getMessage(), e);
            String reutlBeanType = JymFindServerConfig.getServerResultType(jym);
            ReultErrorBean serverObj = (ReultErrorBean) applicationContext.getBean(reutlBeanType);
            resultJson = serverObj.returnBeanJson(Console_ErrCode.RESP_CODE_88_ERR_TXN, Console_ErrCode.TRANS_ERROR);
        } catch (Throwable e) {
            LogPay.error(e.getMessage(), e);
            String reutlBeanType = JymFindServerConfig.getServerResultType(jym);
            ReultErrorBean serverObj = (ReultErrorBean) applicationContext.getBean(reutlBeanType);
            resultJson = serverObj.returnBeanJson(Console_ErrCode.RESP_CODE_88_ERR_TXN, Console_ErrCode.TRANS_ERROR);
        } finally {
            LogPay.info("返回内容[" + (System.currentTimeMillis() - l) + "]:" + resultJson);
        }
        return resultJson;
    }
}
