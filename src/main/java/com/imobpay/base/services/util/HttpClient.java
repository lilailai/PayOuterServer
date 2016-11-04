package com.imobpay.base.services.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.imobpay.base.log.LogPay;
import com.imobpay.base.util.EmptyChecker;

/**
 * 添加时间: 2016年9月1日 下午5:22:34 <br/> 
 * 版本： 
 * @since JDK 1.6 QtServer 1.0
 */
public class HttpClient {
    /**
     * author：曹文军.<br/>
     * 创建日期：2016年9月1日.<br/>
     * 创建时间：下午5:22:46.<br/>
     * 参数或异常：@param url
     * 参数或异常：@param nvps
     * 参数或异常：@return .<br/>
     * 返回结果：String.<br/>
     * 其它内容： JDK 1.6 QtServer 1.0.<br/>
     */
    public static String post(String url, List<NameValuePair> nvps) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String body = null;
        HttpPost post = null;
        try {
            post = postForm(url, nvps);
            body = invoke(httpclient, post);
        } catch (Exception e) {
            LogPay.error("请求RYX异常:" + e.getMessage());
        } finally {
            if (EmptyChecker.isNotEmpty(httpclient)) {
                post.releaseConnection();
            }
            if (EmptyChecker.isNotEmpty(httpclient)) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    LogPay.error("关闭httpclient异常：" + e.getMessage());
                }
            }
        }
        return body;
    }

    /**
     * author：曹文军.<br/>
     * 创建日期：2016年9月1日.<br/>
     * 创建时间：下午5:23:34.<br/>
     * 参数或异常：@param httpclient
     * 参数或异常：@param httpost
     * 参数或异常：@return .<br/>
     * 返回结果：String.<br/>
     * 其它内容： JDK 1.6 QtServer 1.0.<br/>
     */
    private static String invoke(CloseableHttpClient httpclient, HttpUriRequest httpost) {
        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);
        return body;
    }

    /**
     * 
     * 方法名： paseResponse.<br/>
     * author：曹文军.<br/>
     * 创建日期：2016年9月1日.<br/>
     * 创建时间：下午5:24:19.<br/>
     * 参数或异常：@param response
     * 参数或异常：@return .<br/>
     * 返回结果：String.<br/>
     * 其它内容： JDK 1.6 QtServer 1.0.<br/>
     */
    private static String paseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(entity);
        } catch (ParseException e) {
            LogPay.error(e.getMessage());
        } catch (IOException e) {
            LogPay.error(e.getMessage());
        } catch (Exception e) {
            LogPay.error(e.getMessage());
        }
        return body;
    }

    /**
     * sendRequest:(这里用一句话描述这个方法的作用). <br/> 
     * @author CAOWENJUN 
     * @param httpclient httpclient对象
     * @param httpost 请求
     * @return HttpResponse
     * @since JDK 1.6
     */
    private static HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost) {
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            LogPay.error(e.getMessage());
        } catch (IOException e) {
            LogPay.error(e.getMessage());
        } catch (Exception e) {
            LogPay.error(e.getMessage());
        }
        return response;
    }

    /**
     * 
     * postForm:(这里用一句话描述这个方法的作用). <br/> 
     * @author Administrator 
     * @param url 请求地址
     * @param nvps 请求参数对象
     * @return HttpPost
     * @since JDK 1.6
     */
    private static HttpPost postForm(String url, List<NameValuePair> nvps) {
        HttpPost httpost = new HttpPost(url);
        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            /**设置请求和传输超时时间*/
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
            httpost.setConfig(requestConfig);
        } catch (UnsupportedEncodingException e) {
            LogPay.error(e.getMessage());
        } catch (Exception e) {
            LogPay.error(e.getMessage());
        }
        return httpost;
    }

}