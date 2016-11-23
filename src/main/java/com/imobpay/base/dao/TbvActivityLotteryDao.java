/**
 *  <pre>	
 *  Project Name:PayUserServer .</br>
 *  File: TbvActivityLottery.java .</br>
 *  Package Name:com.imobpay.base.entity .</br>
 *  Date      Author       Changes .</br>
 *  20160923 040929 <br/>   Lance.Wu     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.dao;

import com.imobpay.base.entity.TbvActivityLottery;
import com.imobpay.base.iface.BaseDao;

/**
 * <pre>
 * ClassName: TbvActivityLottery <br/> 
 * date: 20160923 040929 <br/> 
 * @param <T> 对象
 * @author Lance.Wu . <br/> 
 * @version 1.0. <br/> 
 * @since JDK 1.6 PayUserServer 1.0 . <br/>
 * </pre>
 */
public interface TbvActivityLotteryDao<T> extends BaseDao<T> {
    
    /**
     * 
     * 【方法名】 : (获取用户抽奖记录). <br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2016年10月13日 下午3:50:13 .<br/>
     * 【参数】： .<br/>
     * 
     * @param tbvActivityLottery 参数
     * @return .<br/>
     *         <p>
     *         修改记录.<br/>
     *         修改人: madman 修改描述：创建新新件 .<br/>
     *         <p/>
     */
    public TbvActivityLottery selectActiveLottery(TbvActivityLottery tbvActivityLottery);

    /**
     * 
     * 【方法名】 : (这更新TA的状态和时间). <br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2016年9月23日 下午4:25:52 .<br/>
     * 【参数】： .<br/>
     * 
     * @param activityLottery
     *            参数
     * @return .<br/>
     *         <p>
     *         修改记录.<br/>
     *         修改人: madman 修改描述：创建新新件 .<br/>
     *         <p/>
     */
    public int updateCodeLottery(TbvActivityLottery activityLottery);

    /**
     * 
     * 【方法名】 : (修改TA卡的状态). <br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2016年9月23日 下午4:37:25 .<br/>
     * 【参数】： .<br/>
     * 
     * @return .<br/>
     *         <p>
     *         修改记录.<br/>
     *         修改人: madman 修改描述：创建新新件 .<br/>
     *         <p/>
     */
    public int updateCodeStatus();

}