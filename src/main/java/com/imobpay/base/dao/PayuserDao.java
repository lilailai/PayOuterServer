/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: Payuser.java .</br>
 *  Package Name:com.imobpay.base.entity .</br>
 *  Date      Author       Changes .</br>
 *  20160907 100927 <br/>   Lance.Wu     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.dao;

import com.imobpay.base.entity.Payuser;
import com.imobpay.base.iface.BaseDao;

/**
 * <pre>
 * ClassName: Payuser <br/> 
 * date: 20160907 100927 <br/> 
 * @param <T> 对象
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public interface PayuserDao<T> extends BaseDao<T> {
    
    /**
     * 
     * selectByCusMob:(根据客户编号查询客户信息). <br/>
     * 
     * @author madman
     * @param payuser
     *            客户编号
     * @return
     * @return 返回结果：Payuser
     * @since JDK 1.6 PayTACard 1.0
     */
    public Payuser selectByCusMob(Payuser payuser);

}