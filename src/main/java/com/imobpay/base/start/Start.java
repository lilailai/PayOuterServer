/**
 *  <pre>   
 *  Project Name:PayServicesUser .</br>
 *  File: start.java .</br>
 *  Package Name:com.pay.base.main .</br>
 *  Date      Author       Changes .</br>
 *  2016年5月19日   Lance.Wu     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>   
 */
package com.imobpay.base.start;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.imobpay.base.log.LogPay;

/**
 * <pre>
 * ClassName: start <br/> 
 * Function: 启动类. <br/> 
 * date: 2016年5月19日 下午1:10:34 <br/> 
 * 
 * @author Lance.Wu . <br/> 
 * @version   . <br/> 
 * @since JDK 1.6 PayServicesUser 1.0 . <br/>
 * </pre>
 */
public class Start {
    /** Spring 文件加载 */
    static FileSystemXmlApplicationContext context = null;
    static {
        String osType = System.getProperty("os.name").toUpperCase();
        boolean falg = osType.indexOf("WINDOW") < 0;

        /** 加载系统lib包 b */
        long l = System.currentTimeMillis();
        String workdir = System.getProperty("user.dir");
        System.setProperty("loghome", workdir);
        if (workdir.endsWith("lib") && falg) {
            workdir = workdir.substring(0, workdir.length() - 4);
            System.setProperty("loghome", workdir);
        }
        StringBuffer libPath = new StringBuffer(workdir);
        libPath.append(File.separator).append("lib");

        File fs = new File(libPath.toString());

        if (!fs.exists() && falg) {
            System.out.println(getLogNow("当前无指定主目录....."));
            System.exit(0);
        }
        String[] libs = fs.list();

        if ((libs == null || libs.length < 1) && falg) {
            System.out.println(getLogNow("没有可加载Jar文件......."));
            System.exit(0);
        }

        if (libs != null && falg) {
            /** 加载Jar包 */
            for (String libFile : libs) {
                if (libFile.endsWith("jar")) {
                    ClassLoaderUtil.addClassPath(libFile);
                }
            }
        }

        /** 加载Spring配置文件 */
        context = new FileSystemXmlApplicationContext(new String[] { "File:" + workdir + "/config/spring-context.xml" });
        long l2 = System.currentTimeMillis();
        System.setProperty("workdir", workdir);
        LogPay.info("加载文件Jar使用时间[" + (l2 - l) + "]");

    }

    /**
     * 
     * main:(启动方法). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param args
     * <br/>
     * @since JDK 1.6 PayServicesUser 1.0 <br/>
     */
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        context.start();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            long l2 = System.currentTimeMillis();
            LogPay.info("[" + (l2 - l) + "]Dubbo Server start up");
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * getLogNow:(显示日志信息). <br/>
     * 
     * @author Lance.Wu <br/>
     * @param logStr
     *            日志文件
     * @return 返回结果：String <br/>
     * @since JDK 1.6 PayServicesUser 1.0 <br/>
     */
    private static String getLogNow(String logStr) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd hhmmssSSS");
        StringBuffer sb = new StringBuffer(df.format(new Date()));
        sb.append(" [");
        sb.append(Thread.currentThread().getName());
        sb.append("][com.pay.base.main.Start] ");
        sb.append(logStr);
        return sb.toString();
    }

}
