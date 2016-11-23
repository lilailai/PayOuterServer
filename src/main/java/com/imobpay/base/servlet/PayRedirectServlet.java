package com.imobpay.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_Column;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.console.OuterConsoleColumn;
import com.imobpay.base.dao.PayuserDao;
import com.imobpay.base.dao.TbvBranchParamDao;
import com.imobpay.base.dao.TbvCardDao;
import com.imobpay.base.dao.TbvCustomerDao;
import com.imobpay.base.dao.TbvSysParamDao;
import com.imobpay.base.entity.Payuser;
import com.imobpay.base.entity.TbvBranchParam;
import com.imobpay.base.entity.TbvCard;
import com.imobpay.base.entity.TbvCustomer;
import com.imobpay.base.entity.TbvSysParam;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.log.LogPay;
import com.imobpay.base.pojo.ResultPojo;
import com.imobpay.base.services.util.EmptyChecker;

/**
 * 
 * <pre>
 * 【类型】: PayRedirectServlet <br/> 
 * 【作用】: TA支付转发servlet. <br/>  
 * 【时间】：2016年11月23日 下午3:31:47 <br/> 
 * 【作者】：HuaiYu.Wen <br/> 
 * </pre>
 */
@Service
public class PayRedirectServlet extends HttpServlet {

    /** 应用环境 */
    private ApplicationContext                        applicationContext;

    /** 客户信息类 */
    private TbvCustomerDao<TbvCustomer>               tbvCusDao;
    
    /** 用户信息类 */
    private PayuserDao<Payuser>                       payUserDao;
    
    /** TACARD表 */
    private TbvCardDao<TbvCard>                       tbvCardDao;
    
    /** 参数表 */
    private TbvSysParamDao<TbvSysParam>               tbvSysParame;
    
    /** 机构公众号对应表 */
    private TbvBranchParamDao<TbvBranchParam>         tbvBranchParmDao;

    @SuppressWarnings("unchecked")
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        /** 获取应用环境 */
        applicationContext = (ApplicationContext) (this.getServletContext().getAttribute("applicationContext"));

        /** 填充数据库操作对象 */
        tbvCusDao = (TbvCustomerDao<TbvCustomer>) applicationContext.getBean("tbvCustomerDao");
        payUserDao = (PayuserDao<Payuser>) applicationContext.getBean("payuserDao");
        tbvCardDao = (TbvCardDao<TbvCard>) applicationContext.getBean("tbvCardDao");
        tbvSysParame = (TbvSysParamDao<TbvSysParam>) applicationContext.getBean("tbvSysParamDao");
        tbvBranchParmDao = (TbvBranchParamDao<TbvBranchParam>) applicationContext.getBean("tbvBranchParamDao");
    }

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Thread.currentThread().setName(System.currentTimeMillis() + "");
        LogPay.info("请求URL:" + request.getRequestURL());
        long l = System.currentTimeMillis();
        ResultPojo resultPojo = new ResultPojo();
        String msg = "";
        PrintWriter out = null;
        try {
            String taid = request.getParameter(OuterConsoleColumn.TAID);

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            LogPay.info("TA卡号:" + taid);
            TbvCustomer tbvCustomer = new TbvCustomer();
            tbvCustomer.setTaaccountmac(taid);
            String respUrl = Console_Column.EMPTY;
            /** 主业务处理开始*/
            respUrl = getSendRedirectUrl(tbvCustomer);

            /** 主业务处理结束*/
            if (EmptyChecker.isNotEmpty(respUrl)) {
                LogPay.info("跳转URL:" + respUrl);
                response.sendRedirect(respUrl);
            } else {
                throw new QTException(Console_ErrCode.URL_NOEXITISCODE, Console_ErrCode.URL_NOEXITISDESC);
            }
        } catch (QTException e) {
            LogPay.error(e.getMessage(), e);
            resultPojo.setMsgcode(e.getRespCode());
            resultPojo.setMsgtext(e.getRespMsg());
            msg = JSONObject.toJSON(resultPojo).toString();
        } catch (Exception e) {
            LogPay.error(e.getMessage(), e);
            resultPojo.setMsgcode(Console_ErrCode.NOTE_SYSISBUSYCODE);
            resultPojo.setMsgtext(Console_ErrCode.NOTE_SYSISBUSYDESC);
            msg = JSONObject.toJSON(resultPojo).toString();
        } catch (Throwable e) {
            LogPay.error(e.getMessage(), e);
            resultPojo.setMsgcode(Console_ErrCode.NOTE_SYSISBUSYCODE);
            resultPojo.setMsgtext(Console_ErrCode.NOTE_SYSISBUSYDESC);
            msg = JSONObject.toJSON(resultPojo).toString();
        } finally {
            LogPay.info("返回报文:" + msg);
            out.write(msg);
            if (EmptyChecker.isNotEmpty(out)) {
                out.flush();
                out.close();
                LogPay.info("out流已关闭");
            }
            LogPay.info("交易耗时:" + (System.currentTimeMillis() - l));
        }
    }

    /**
     * 
     * 【方法名】    : getSendRedirectUrl. <br/> 
     * 【作用】: 获取转发地址.<br/> 
     * 【作者】: HuaiYu.Wen .<br/>
     * 【时间】： 2016年11月23日 下午4:29:54 .<br/>
     * 【参数】： .<br/>
     * @param tbvCustomer TA客户对象
     * @return 跳转地址
     * @throws QTException 
     * @throws Exception .<br/>
     */
    public String getSendRedirectUrl(TbvCustomer tbvCustomer) throws QTException, Exception {
        // 优先判断TA卡号是否存在
        TbvCard tCard = new TbvCard();
        tCard.setTaaccountmac(tbvCustomer.getTaaccountmac());
        TbvCard taCard = tbvCardDao.selectById(tCard);
        if (EmptyChecker.isEmpty(taCard)) {
            throw new QTException(Console_ErrCode.NOTE_TACARDERRCODE, Console_ErrCode.NOTE_TACARDERRDESC);
        }
        TbvSysParam tbvParam = new TbvSysParam();
        // 在此处判断是APP请求转发还是微信网页版转发 如果是A开头的则是APP的瑞玛扫描
        if (Console_Column.VALUE_1.equalsIgnoreCase(taCard.getType())) {
            tbvParam.setParamname(OuterConsoleColumn.PARAME_PAY_APP);
            TbvSysParam selectParam = tbvSysParame.selectById(tbvParam);
            if (EmptyChecker.isEmpty(selectParam)) {
                throw new QTException(Console_ErrCode.NOTE_NOPARAMCONFIGCODE, Console_ErrCode.NOTE_NOPARAMCONFIGDESC);
            }

            // 根据客户编号查询用户信息
            Payuser pu = new Payuser();
            pu.setCustomerid(taCard.getTaaccount());
            /**
             *修改人:文怀宇
             *修改描述:判断Taaccount是否包含AppUser
             *修改时间: 2016年10月24日19:42:44
             */
            Map<String, String> map = retAppUserParam();

            /** 判断是否非空 */
            if (EmptyChecker.isNotEmpty(map)) {
                /** 获得客户号 */
                String customerId = taCard.getTaaccount();
                for (String str : map.keySet()) {

                    /** 判断客户号是否包含appuser */
                    if (customerId.indexOf(str) > -1) {
                        pu.setCustomerid(customerId.substring(0, customerId.indexOf(str)));
                        break;
                    }
                }
            }

            Payuser selectByCusMob = payUserDao.selectByCusMob(pu);
            if (EmptyChecker.isEmpty(selectByCusMob)) {
                throw new QTException(Console_ErrCode.NOTE_NOREGISTERCODE, Console_ErrCode.NOTE_NOREGISTERDESC);
            }
            // 判断用户是否审核通过
            if (!"3".equals(selectByCusMob.getCustomertag())) {
                throw new QTException(Console_ErrCode.USERNOREALNAMECODE, Console_ErrCode.USERNOREALNAMEDESC);
            }

            String mobVal = selectByCusMob.getUserid();
            String userName = selectByCusMob.getUsername() == null ? "" : selectByCusMob.getUsername();
            String mugshoturl = selectByCusMob.getMugshoturl() == null ? "" : selectByCusMob.getMugshoturl();
            LogPay.info("mobVal:" + mobVal + ",userNameVal:" + userName + ",taMacVal:" + tbvCustomer.getTaaccountmac() + ",iconVal" + mugshoturl);
            String parValue = selectParam.getParamvalue();
            parValue = parValue.replaceAll("mobVal", URLEncoder.encode(mobVal, "UTF-8"));
            parValue = parValue.replaceAll("taMacVal", URLEncoder.encode(tbvCustomer.getTaaccountmac(), "UTF-8"));
            parValue = parValue.replaceAll("userNameVal", URLEncoder.encode(userName, "UTF-8"));
            parValue = parValue.replaceAll("iconVal", URLEncoder.encode(mugshoturl, "UTF-8"));
            /**
             * 修改人:文怀宇
             * 修改时间:2016年10月21日16:18:16
             * 修改描述 :跳转页面url参数追加TAACCOUNT明文参数
             */
            parValue = parValue.replaceAll("taAccountVal", taCard.getTaaccount());
            return parValue;
        }

        tbvCustomer.setTaaccount(taCard.getTaaccount());
        String branchName = taCard.getBranchname() == null ? "欣象信息" : taCard.getBranchname();
        TbvCustomer selectById = tbvCusDao.selectById(tbvCustomer);

        /** 获取机构与微信的绑定关系 **/
        TbvBranchParam tbracnch = new TbvBranchParam();
        tbracnch.setBranchid(taCard.getBranchid());
        TbvBranchParam selBranch = tbvBranchParmDao.selectById(tbracnch);
        if (EmptyChecker.isEmpty(selBranch)) {
            throw new QTException(Console_ErrCode.NOTE_NOBRANCHIDCODE, Console_ErrCode.NOTE_NOBRANCHIDDESC);
        }
        String pubAccount = selBranch.getPubaccount();
        String taDesc = selBranch.getTadesc();
        String taUrl = selBranch.getTaurl();
        LogPay.info("pubAccount:" + pubAccount);
        LogPay.info("taDesc:" + taDesc);
        LogPay.info("taUrl:" + taUrl);
        if (EmptyChecker.isEmpty(selectById)) {
            // 无数据 表示没有注册 跳转注册页面
            LogPay.info("跳往注册页面");
            tbvParam.setParamname(OuterConsoleColumn.PARAME_REG);
            TbvSysParam selectParam = tbvSysParame.selectById(tbvParam);
            if (EmptyChecker.isEmpty(selectParam)) {
                throw new QTException(Console_ErrCode.NOTE_NOPARAMCONFIGCODE, Console_ErrCode.NOTE_NOPARAMCONFIGDESC);
            }
            LogPay.info("taVal:" + tbvCustomer.getTaaccountmac() + ",bidVal:" + taCard.getBranchid() + ",bidNameVal:" + branchName);
            String parValue = selectParam.getParamvalue();

            LogPay.info("taVal:" + taCard.getTaaccount());
            LogPay.info("taMacVal:" + tbvCustomer.getTaaccountmac());
            LogPay.info("bidVal:" + taCard.getBranchid());
            LogPay.info("bidNameVal:" + branchName);
            parValue = parValue.replaceAll("taVal", URLEncoder.encode(taCard.getTaaccount(), "UTF-8"));
            parValue = parValue.replaceAll("taMacVal", URLEncoder.encode(tbvCustomer.getTaaccountmac(), "UTF-8"));
            parValue = parValue.replaceAll("bidVal", URLEncoder.encode(taCard.getBranchid(), "UTF-8"));
            parValue = parValue.replaceAll("bidNameVal", URLEncoder.encode(branchName, "UTF-8"));
            parValue = parValue.replaceAll("pubAccountVal", URLEncoder.encode(pubAccount, "UTF-8"));
            parValue = parValue.replaceAll("taDescVal", URLEncoder.encode(taDesc, "UTF-8"));
            parValue = parValue.replaceAll("taUrlVal", URLEncoder.encode(taUrl, "UTF-8"));

            return parValue;
        }
        if (OuterConsoleColumn.TA_STATUS_1.equals(selectById.getStatus())) {
            // 跳转收款页面
            LogPay.info("跳往收款页面");
            String shopName = selectById.getShopname();
            String mob = selectById.getMobile();
            tbvParam.setParamname(OuterConsoleColumn.PARAME_PAY);
            TbvSysParam selectParam = tbvSysParame.selectById(tbvParam);
            if (EmptyChecker.isEmpty(selectParam)) {
                throw new QTException(Console_ErrCode.NOTE_NOPARAMCONFIGCODE, Console_ErrCode.NOTE_NOPARAMCONFIGDESC);
            }
            LogPay.info("taVal:" + tbvCustomer.getTaaccountmac() + ",shopNameVal:" + shopName + ",mobVal:" + mob);
            String parValue = selectParam.getParamvalue();

            LogPay.info("taVal:" + selectById.getTaaccount());
            LogPay.info("taMacVal:" + tbvCustomer.getTaaccountmac());
            LogPay.info("bidVal:" + taCard.getBranchid());
            LogPay.info("bidNameVal:" + branchName);
            LogPay.info("shopNameVal:" + shopName);
            parValue = parValue.replaceAll("taVal", URLEncoder.encode(selectById.getTaaccount(), "UTF-8"));
            parValue = parValue.replaceAll("taMacVal", URLEncoder.encode(tbvCustomer.getTaaccountmac(), "UTF-8"));
            parValue = parValue.replaceAll("bidVal", URLEncoder.encode(taCard.getBranchid(), "UTF-8"));
            parValue = parValue.replaceAll("bidNameVal", URLEncoder.encode(branchName, "UTF-8"));
            parValue = parValue.replaceAll("shopNameVal", URLEncoder.encode(shopName, "UTF-8"));
            parValue = parValue.replaceAll("pubAccountVal", URLEncoder.encode(pubAccount, "UTF-8"));
            parValue = parValue.replaceAll("taDescVal", URLEncoder.encode(taDesc, "UTF-8"));
            parValue = parValue.replaceAll("taUrlVal", URLEncoder.encode(taUrl, "UTF-8"));
            return parValue;
        } else {
            // 跳转冻结页面
            LogPay.info("跳往冻结页面");
            tbvParam.setParamname(OuterConsoleColumn.PARAME_DJ);
            TbvSysParam selectParam = tbvSysParame.selectById(tbvParam);
            if (EmptyChecker.isEmpty(selectParam)) {
                throw new QTException(Console_ErrCode.NOTE_NOPARAMCONFIGCODE, Console_ErrCode.NOTE_NOPARAMCONFIGDESC);
            }
            String parValue = selectParam.getParamvalue();
            return parValue;
        }

    }

    /**
     * 
     * 【方法名】    :retAppUserParam. <br/> 
     * 【作用】: 初始化配置的appUser信息，.<br/> 
     * 【作者】: HuaiYu.Wen .<br/>
     * 【时间】： 2016年10月21日 下午5:07:40 .<br/>
     * 【参数】： .<br/>
     * @return Map<String, String> 应用类型map集合
     * @throws QTException .<br/>
     */
    public Map<String, String> retAppUserParam() throws QTException {
        /** 返回map对象 */
        Map<String, String> map = new HashMap<String, String>();

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

            /** 添加 */
            map.put(tempAppUser, tempFeeId);
        }
        return map;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：HuaiYu.Wen <br/>
     * 返回类型：@return applicationContext .<br/>
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
    * 创建人：HuaiYu.Wen <br/>
    * 创建时间：2016年11月23日 下午3:49:54 <br/>
    * 参数: @param  applicationContext 设置值.  <br/>
    */
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：HuaiYu.Wen <br/>
     * 返回类型：@return tbvCusDao .<br/>
     */
    public TbvCustomerDao<TbvCustomer> getTbvCusDao() {
        return tbvCusDao;
    }

    /**
    * 创建人：HuaiYu.Wen <br/>
    * 创建时间：2016年11月23日 下午3:49:54 <br/>
    * 参数: @param  tbvCusDao 设置值.  <br/>
    */
    public void setTbvCusDao(TbvCustomerDao<TbvCustomer> tbvCusDao) {
        this.tbvCusDao = tbvCusDao;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：HuaiYu.Wen <br/>
     * 返回类型：@return payUserDao .<br/>
     */
    public PayuserDao<Payuser> getPayUserDao() {
        return payUserDao;
    }

    /**
    * 创建人：HuaiYu.Wen <br/>
    * 创建时间：2016年11月23日 下午3:49:54 <br/>
    * 参数: @param  payUserDao 设置值.  <br/>
    */
    public void setPayUserDao(PayuserDao<Payuser> payUserDao) {
        this.payUserDao = payUserDao;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：HuaiYu.Wen <br/>
     * 返回类型：@return tbvCardDao .<br/>
     */
    public TbvCardDao<TbvCard> getTbvCardDao() {
        return tbvCardDao;
    }

    /**
    * 创建人：HuaiYu.Wen <br/>
    * 创建时间：2016年11月23日 下午3:49:54 <br/>
    * 参数: @param  tbvCardDao 设置值.  <br/>
    */
    public void setTbvCardDao(TbvCardDao<TbvCard> tbvCardDao) {
        this.tbvCardDao = tbvCardDao;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：HuaiYu.Wen <br/>
     * 返回类型：@return tbvSysParame .<br/>
     */
    public TbvSysParamDao<TbvSysParam> getTbvSysParame() {
        return tbvSysParame;
    }

    /**
    * 创建人：HuaiYu.Wen <br/>
    * 创建时间：2016年11月23日 下午3:49:54 <br/>
    * 参数: @param  tbvSysParame 设置值.  <br/>
    */
    public void setTbvSysParame(TbvSysParamDao<TbvSysParam> tbvSysParame) {
        this.tbvSysParame = tbvSysParame;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：HuaiYu.Wen <br/>
     * 返回类型：@return tbvBranchParmDao .<br/>
     */
    public TbvBranchParamDao<TbvBranchParam> getTbvBranchParmDao() {
        return tbvBranchParmDao;
    }

    /**
    * 创建人：HuaiYu.Wen <br/>
    * 创建时间：2016年11月23日 下午3:49:54 <br/>
    * 参数: @param  tbvBranchParmDao 设置值.  <br/>
    */
    public void setTbvBranchParmDao(TbvBranchParamDao<TbvBranchParam> tbvBranchParmDao) {
        this.tbvBranchParmDao = tbvBranchParmDao;
    }

}
