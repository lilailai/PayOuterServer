package com.imobpay.base.services.util;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.log.LogPay;

/**
 * <dl>
 * 类功能概要：<br/>
 * <dd>提供基本对象、集合、数组的空值、非空值检查。<br/>
 * </dl>
 * 
 * @version <pre>
 * 【Update History】
 * Version  Date        Company      Author         Case-Name
 * -------  ----------  -----------  -------------  ------------------------------------------------
 * 
 * </pre>
 */
public class EmptyChecker {

    /**
     * isEmpty:(对于Collection、Dictionary、Map，不深入迭代，判断有没有子元素。). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param obj
     *            判断对像
     * @return <br/>
     * @since JDK 1.6 PayCommomPlatform 1.0 <br/>
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null || "null".equals(obj)) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Dictionary) {
            return ((Dictionary<?, ?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        return false;
    }

    /**
     * 
     * isEmpty:(数组里任何一个元素非空，返回false。). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param array
     *            对像数组
     * @return <br/>
     * @since JDK 1.6 PayCommomPlatform 1.0 <br/>
     */
    public static boolean isEmpty(Object... array) {
        if (array == null || array.length == 0) {
            return true;
        }

        for (Object o : array) {
            if (isNotEmpty(o)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 
     * isEmpty:(数组里任何一个元素非空，返回false。). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param array
     *            对像数组
     * @return <br/>
     * @since JDK 1.6 PayCommomPlatform 1.0 <br/>
     */
    public static boolean isEmpty(String... array) {
        if (array == null || array.length == 0) {
            return true;
        }

        for (String o : array) {
            if (isNotEmpty(o)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 
     * isEmpty:(单个对像任何一个元素为空，返回false。). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param obj
     *            对像
     * @return <br/>
     * @since JDK 1.6 PayCommomPlatform 1.0 <br/>
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 
     * isNotEmpty:(数组里任何一个元素为空，返回false。). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param array
     *            一组对像
     * @return <br/>
     * @since JDK 1.6 PayCommomPlatform 1.0 <br/>
     */
    public static boolean isNotEmpty(Object... array) {
        if (array == null || array.length == 0) {
            return false;
        }

        for (Object o : array) {
            if (isEmpty(o)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 
     * isNotEmpty:(数组里任何一个元素为空，返回false。). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param array
     *            一组对像
     * @return <br/>
     * @since JDK 1.6 PayCommomPlatform 1.0 <br/>
     */
    public static boolean isNotEmpty(String... array) {
        if (array == null || array.length == 0) {
            return false;
        }
        for (String o : array) {
            if (isEmpty(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * checkEmpty:(判断是否为空的内容). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param items
     *            判断对像集合 <br/>
     * @param strings
     *            判断的字段集合 <br/>
     * @throws QTException
     * <br/>
     * <br/>
     * @since JDK 1.6 PayIFramework 1.0 <br/>
     */
    public static void checkEmpty(Map<String, Object> items, String... strings) throws QTException {
        for (String paraKey : strings) {
            if (isEmpty(items.get(paraKey))) {
                LogPay.error("请求参数异常:[" + paraKey + "]");
                throw new QTException(Console_ErrCode.RESP_CODE_03_ERR_PRARM, "参数错误：缺少必须参数、或必须参数不合法");
            }
        }
    }

    /**
     * 
     * checkEmpty:(判断是否为空的内容). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param items
     *            判断对像集合 <br/>
     * @param strings
     *            判断的字段集合 <br/>
     * @throws QTException
     * <br/>
     * <br/>
     * @since JDK 1.6 PayIFramework 1.0 <br/>
     */
    public static void checkEmptyJson(JSONObject items, String... strings) throws QTException {
        for (String paraKey : strings) {
            if (isEmpty(items.get(paraKey))) {
                LogPay.error("请求参数异常:[" + paraKey + "]");
                throw new QTException(Console_ErrCode.RESP_CODE_03_ERR_PRARM, "参数"+"["+ paraKey + "]"+"错误:" +"缺少必须参数、或必须参数不合法");
            }
        }
    }

}
