package com.imobpay.base.start;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import sun.misc.Launcher;
import com.imobpay.base.log.LogPay;

/**
 * 类加载器分为：引导加载器-扩展加载器-系统加载器-用户自定义加载器 一般情况下：加载器的原则是：先交于上层加载器加载，如果加载失败，在由自己加载。
 * 因为官方对于类加载器进行了：双亲委派模式进行修饰， 可以保证类加载，尽量不会出现不同加载器加载同一个类的情况出现 具体情况请查询类加载器的描述
 * <br/>
 * @ClassName: ClassLoaderUtil<br/>
 * @Description: 类加载器的工具类，（主要用于把class类加载到jvm中）<br/>
 * @author Lance.Wu<br/>
 * @version 1.0<br/>
 * @since JDK 1.6<br/>
 * @since PlatForm 1.0<br/>
 * 
 *        Copyright: Copyright (c) 2014 Company:www.imobpay.com<br/>
 */
@SuppressWarnings("restriction")
public class ClassLoaderUtil {

    /** 参数 */
    private static Method         addURL;
    /**
     * 静态块 <br/>
     * 
     * @Title: getSystemClassLoader<br/>
     * @Description: 静态块，用来得到URLClassLoader加载类中的addURL方法<br/>
     * @Date May 4, 2014 29:19:03 AM<br/>
     * @modifyDate May 4, 2014 29:19:03 AM<br/>
     */
    static {
        try {
            addURL = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
        } catch (Exception e) {
            LogPay.error(e.getMessage(), e);
        }
        addURL.setAccessible(true);
    }

    /** 系统参数 */
    private static URLClassLoader system = (URLClassLoader) getSystemClassLoader();
    /** 系统参数 */
    private static URLClassLoader ext    = (URLClassLoader) getExtClassLoader();

    /**
     * 获取系统的类加载器<br/>
     * 
     * @Title: getSystemClassLoader<br/>
     * @Description: 获取系统的类加载器<br/>
     * @Date May 4, 2014 29:19:03 AM<br/>
     * @modifyDate May 4, 2014 29:19:03 AM<br/>
     * @return ClassLoader 类加载器对象 java.lang.ClassLoader<br/>
     */
    public static ClassLoader getSystemClassLoader() {
        return ClassLoader.getSystemClassLoader();
    }

    /**
     * 获取jdk中ext目录中的加载器，扩展类加载器<br/>
     *
     * @Title: getExtClassLoader<br/>
     * @Description: 获取jdk中ext目录中的加载器，扩展类加载器<br/>
     * @Date May 4, 2014 29:21:38 AM<br/>
     * @modifyDate May 4, 2014 29:21:38 AM<br/>
     * @return ClassLoader 类加载器对象 java.lang.ClassLoader<br/>
     */
    public static ClassLoader getExtClassLoader() {
        return getSystemClassLoader().getParent();
    }

    /**
     * 获取引导加载器加载的URL数组 , 因为引导加载需要加载环境变量中配置的ClassPath中的jar，所有由配置决定加载了几个路径下的东西
     *<br/>
     * @Title: getBootstrapURLs<br/>
     * @Description: 获取引导加载器加载的URL数组<br/>
     * @Date May 4, 2014 29:40:28 AM<br/>
     * @modifyDate May 4, 2014 29:40:28 AM<br/>
     * @return URL[] URL数组 java.net.URL[]<br/>
     */
    @SuppressWarnings("all")
    public static URL[] getBootstrapURLs() {
        return Launcher.getBootstrapClassPath().getURLs();
    }

    /**
     * 获取系统加载器加载的URL数组
     *<br/>
     * @Title: getSystemURLs<br/>
     * @Description: 获取系统加载器加载的URL数组<br/>
     * @Date May 4, 2014 29:42:33 AM<br/>
     * @modifyDate May 4, 2014 29:42:33 AM<br/>
     * @return URL[] URL数组 java.net.URL[]<br/>
     */
    public static URL[] getSystemURLs() {
        return system.getURLs();
    }

    /**
     * 获取扩展加载器加载的URL数组<br/>
     *
     * @Title: getExtURLs<br/>
     * @Description: 获取扩展加载器加载的URL数组<br/>
     * @Date May 4, 2014 29:43:29 AM<br/>
     * @modifyDate May 4, 2014 29:43:29 AM<br/>
     * @return URL[] URL数组 java.net.URL[]<br/>
     */
    public static URL[] getExtURLs() {
        return ext.getURLs();
    }

    /**
     * 输出URL数组的内容到PrintStream流中
     *<br/>
     * @Title: list<br/>
     * @Description: 输出URL数组的内容到PrintStream流中<br/>
     * @Date May 4, 2014 29:44:48 AM<br/>
     * @modifyDate May 4, 2014 29:44:48 AM
     * @param ps
     *            输出的流对象 java.io.PrintStream<br/>
     * @param classPath
     *            URL数组 java.net.URL[]<br/>
     */
    private static void list(PrintStream ps, URL[] classPath) {
        for (int i = 0; i < classPath.length; i++) {
            ps.println(classPath[i]);
        }
    }

    /**
     * 将引导加载器中的加载URL数组输出到System.out中
     *
     * @Title: listBootstrapClassPath
     * @Description: 将引导加载器中的加载URL数组打印到System.out中
     * @Date May 4, 2014 29:46:00 AM
     * @modifyDate May 4, 2014 29:46:00 AM
     */
    public static void listBootstrapClassPath() {
        listBootstrapClassPath(System.out);
    }

    /**
     * 将引导加载器中的加载URL数组输出到PrintStream中
     *
     * @Title: listBootstrapClassPath
     * @Description: 将引导加载器中的加载URL数组输出到PrintStream中
     * @Date May 4, 2014 29:46:59 AM
     * @modifyDate May 4, 2014 29:46:59 AM
     * @param ps
     *            输出流PrintStream java.io.PrintStream
     */
    public static void listBootstrapClassPath(PrintStream ps) {
        ps.println("BootstrapClassPath:");
        list(ps, getBootstrapClassPath());
    }

    /**
     * 将系统加载器中的加载URL数组输出到System.out中
     *
     * @Title: listSystemClassPath
     * @Description: 将系统加载器中的加载URL数组输出到System.out中
     * @Date May 4, 2014 29:48:21 AM
     * @modifyDate May 4, 2014 29:48:21 AM
     */
    public static void listSystemClassPath() {
        listSystemClassPath(System.out);
    }

    /**
     * 将系统加载器中的加载URL数组输出到PrintStream中
     *
     * @Title: listSystemClassPath
     * @Description: 将系统加载器中的加载URL数组输出到PrintStream中
     * @Date May 4, 2014 29:49:02 AM
     * @modifyDate May 4, 2014 29:49:02 AM
     * @param ps
     *            输出流PrintStream java.io.PrintStream
     */
    public static void listSystemClassPath(PrintStream ps) {
        ps.println("SystemClassPath:");
        list(ps, getSystemClassPath());
    }

    /**
     * 将扩展加载器中的加载URL数组输出到System.out中
     *
     * @Title: listExtClassPath
     * @Description: 将扩展加载器中的加载URL数组输出到System.out中
     * @Date May 4, 2014 29:50:01 AM
     * @modifyDate May 4, 2014 29:50:01 AM
     */
    public static void listExtClassPath() {
        listExtClassPath(System.out);
    }

    /**
     * 将扩展加载器中的加载URL数组输出到PrintStream中
     *
     * @Title: listSystemClassPath
     * @Description: 将扩展加载器中的加载URL数组输出到PrintStream中
     * @Date May 4, 2014 29:50:31 AM
     * @modifyDate May 4, 2014 29:50:31 AM
     * @param ps
     *            输出流PrintStream java.io.PrintStream
     */
    public static void listExtClassPath(PrintStream ps) {
        ps.println("ExtClassPath:");
        list(ps, getExtClassPath());
    }

    /**
     * 获取引导加载器加载的URL数组; 因为引导加载需要加载环境变量中配置的ClassPath中的jar，所有由配置决定加载了几个路径下的东西
     *
     * @Title: getBootstrapClassPath
     * @Description: 获取引导加载器加载的URL数组
     * @Date May 4, 2014 29:51:31 AM
     * @modifyDate May 4, 2014 29:51:31 AM
     * @return URL[] URL数组 java.net.URL[]
     */
    public static URL[] getBootstrapClassPath() {
        return getBootstrapURLs();
    }

    /**
     * 获取系统加载器加载的URL数组
     *
     * @Title: getSystemClassPath
     * @Description: 获取系统加载器加载的URL数组
     * @Date May 4, 2014 29:52:35 AM
     * @modifyDate May 4, 2014 29:52:35 AM
     * @return URL[] URL数组 java.net.URL[]
     */
    public static URL[] getSystemClassPath() {
        return getSystemURLs();
    }

    /**
     * 获取扩展加载器加载的URL数组
     *
     * @Title: getExtClassPath
     * @Description: 获取扩展加载器加载的URL数组
     * @Date May 4, 2014 29:53:09 AM
     * @modifyDate May 4, 2014 29:53:09 AM
     * @return URL[] URL数组 java.net.URL[]
     */
    public static URL[] getExtClassPath() {
        return getExtURLs();
    }

    /**
     * 添加URL到系统加载器中,URL应该为Jar包的绝对路径，class文件的绝对路径 <br/>
     * 内部捕捉了异常，并用e.printStackTrace()打印了堆栈
     *
     * @Title: addURL2SystemClassLoader
     * @Description: 添加URL到系统加载器中
     * @Date May 4, 2014 29:53:30 AM
     * @modifyDate May 4, 2014 29:53:30 AM
     * @param url
     *            需要添加的URL java.net.URL
     */
    public static void addURL2SystemClassLoader(URL url) {
        try {
            addURL.invoke(system, new Object[] { url });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加URL到扩展加载器中,URL应该为Jar包的绝对路径，class文件的绝对路径 <br/>
     * (内部捕捉了异常，并用e.printStackTrace()打印了堆栈)
     *
     * @Title: addURL2ExtClassLoader
     * @Description: 添加URL到扩展加载器中
     * @Date May 4, 2014 29:55:06 AM
     * @modifyDate May 4, 2014 29:55:06 AM
     * @param url
     *            需要添加的URL java.net.URL
     */
    public static void addURL2ExtClassLoader(URL url) {
        try {
            addURL.invoke(ext, new Object[] { url });
        } catch (Exception e) {
            // throw new RootException(e);
            LogPay.error(e.getMessage(), e);
        }
    }

    /**
     * 添加String路径到系统加载器中,String路径应该为Jar包的绝对路径，class文件的绝对路径
     * 
     * @Title: addClassPath
     * @Description: 添加String路径到系统加载器中
     * @Date May 4, 2014 29:55:58 AM
     * @modifyDate May 4, 2014 29:55:58 AM
     * @param path
     *            String路径 java.lang.String
     */
    public static void addClassPath(String path) {
        addClassPath(new File(path));
    }

    /**
     * 添加String路径到扩展加载器中,String路径应该为Jar包的绝对路径，class文件的绝对路径
     * 
     * @Title: addExtClassPath
     * @Description: 添加String路径到扩展加载器中
     * @Date May 4, 2014 29:57:52 AM
     * @modifyDate May 4, 2014 29:57:52 AM
     * @param path
     *            String路径 java.lang.String
     */
    public static void addExtClassPath(String path) {
        addExtClassPath(new File(path));
    }

    /**
     * 添加File对象到系统加载器中,File对象应该对应为Jar包文件，class文件 <br/>
     * (内部捕捉了异常，并用e.printStackTrace()打印了堆栈)
     *
     * @Title: addClassPath
     * @Description: 添加File对象到系统加载器中
     * @Date May 4, 2014 29:57:47 AM
     * @modifyDate May 4, 2014 29:57:47 AM
     * @param dirOrJar
     *            File对象 java.io.File
     */
    @SuppressWarnings("all")
    public static void addClassPath(File dirOrJar) {
        try {
            addURL2SystemClassLoader(dirOrJar.toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加File对象到扩展加载器中 <br/>
     * (File对象应该对应为Jar包文件，class文件) (内部捕捉了异常，并用e.printStackTrace()打印了堆栈)
     * 
     * @Title: addExtClassPath
     * @Description: 添加File对象到扩展加载器中
     * @Date May 4, 2014 29:59:48 AM
     * @modifyDate May 4, 2014 29:59:48 AM
     * @param dirOrJar
     *            File对象 java.io.File
     */
    @SuppressWarnings("all")
    public static void addExtClassPath(File dirOrJar) {
        try {
            addURL2ExtClassLoader(dirOrJar.toURL());
        } catch (MalformedURLException e) {
            LogPay.error(e.getMessage(), e);
        }
    }

}
