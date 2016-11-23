package com.imobpay.base.servlet;

import javax.annotation.Resource;
import javax.servlet.Servlet;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import com.imobpay.base.log.LogPay;

/**
 * 【类型】: BaseBeanPostProcessor <br/> 
 * 【作用】: spring前置处理器 <br/>  
 * 【时间】：2016年10月17日 下午2:04:29 <br/> 
 * 【作者】：madman <br/>
 */
public class BaseBeanPostProcessor implements BeanPostProcessor {
    /** payServlet类 */
    private String     payServlet;
    /** payCallBackServlet */
    private String     payCallBackServlet;
    /** payRedirectServlet */
    private String     payRedirectServlet;
    /** 项目的根目录 */
    private String     contextPath;
    /** 上下文对象 */
    @Resource
    ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @SuppressWarnings("all")
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ServletContextHandler) {
            ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
            servletHandler.setAttribute("applicationContext", applicationContext);
            servletHandler.setContextPath(contextPath);
            try {
                servletHandler.addServlet((Class<? extends Servlet>) Class.forName(payServlet), "/SmzfServer.do");
                servletHandler.addServlet((Class<? extends Servlet>) Class.forName(payCallBackServlet), "/CallBack.do");
                servletHandler.addServlet((Class<? extends Servlet>) Class.forName(payRedirectServlet), "/Redirect.do");
            } catch (ClassNotFoundException e) {
                LogPay.error(e.getMessage());
            }
            return servletHandler;
        }
        return bean;
    }

    
    /**
     * 描述：获取属性值.<br/>
     * 创建人：HuaiYu.Wen <br/>
     * 返回类型：@return payRedirectServlet .<br/>
     */
    public String getPayRedirectServlet() {
        return payRedirectServlet;
    }

    /**
    * 创建人：HuaiYu.Wen <br/>
    * 创建时间：2016年11月23日 下午4:40:23 <br/>
    * 参数: @param  payRedirectServlet 设置值.  <br/>
    */
    public void setPayRedirectServlet(String payRedirectServlet) {
        this.payRedirectServlet = payRedirectServlet;
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：LanceWu <br/>
     * 返回类型：@return contextPath .<br/>
     */
    public String getContextPath() {
        return contextPath;
    }

    /**
     * 创建人：LanceWu <br/>
     * 创建时间：2016年10月17日 上午11:15:38 <br/>
     * 参数: @param contextPath 设置值. <br/>
     */
    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    /** 
     * 方法名： getPayServlet.<br/>
     * 适用条件:TODO(简单描述).<br/> 
     * 执行流程:TODO(简单描述).<br/> 
     * 注意事项:TODO(简单描述).<br/>
     * 方法作用:TODO(简单描述).<br/>
     *
     * 返回值：@return 返回值 
     *
     * 创建者：Lance.Wu.<br/>
     * 创建日期：2016年11月4日.<br/>
     * 创建时间：上午9:43:08.<br/>
     * 其它内容： JDK 1.6 PayWeiXinServer 1.0.<br/>
     */

    public String getPayServlet() {
        return payServlet;
    }

    /** 
    * 方法名： setPayServlet.<br/>
    * 适用条件:TODO(简单描述).<br/> 
    * 执行流程:TODO(简单描述).<br/> 
    * 注意事项:TODO(简单描述).<br/>
    * 方法作用:TODO(简单描述).<br/>
    *
    * 参数： @param payServlet 设置值
    *
    * 创建者：Lance.Wu.<br/>
    * 创建日期：2016年11月4日.<br/>
    * 创建时间：上午9:43:08.<br/>
    * 其它内容： JDK 1.6 PayWeiXinServer 1.0.<br/>
    */

    public void setPayServlet(String payServlet) {
        this.payServlet = payServlet;
    }

    /** 
     * 方法名： getPayCallBackServlet.<br/>
     * 返回值：@return 返回值 
     * 创建者：Lance.Wu.<br/>
     * 创建日期：2016年11月4日.<br/>
     * 创建时间：上午9:43:08.<br/>
     * 其它内容： JDK 1.6 PayWeiXinServer 1.0.<br/>
     */

    public String getPayCallBackServlet() {
        return payCallBackServlet;
    }

    /** 
    * 方法名： setPayCallBackServlet.<br/>
    * 参数： @param payCallBackServlet 设置值
    * 创建者：Lance.Wu.<br/>
    * 创建日期：2016年11月4日.<br/>
    * 创建时间：上午9:43:08.<br/>
    * 其它内容： JDK 1.6 PayWeiXinServer 1.0.<br/>
    */

    public void setPayCallBackServlet(String payCallBackServlet) {
        this.payCallBackServlet = payCallBackServlet;
    }

}
