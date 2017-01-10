package com.xiaozhameng.base.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactoryUtil {
    private static ApplicationContext applicationContext = null;

    public static void init(String contextPath) {
        if (applicationContext == null) {
        	synchronized (BeanFactoryUtil.class) {
        		if(applicationContext == null){
        			applicationContext = new ClassPathXmlApplicationContext(contextPath);
        		} 
			}
        }
    }
    /**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) applicationContext.getBean(name);
	}
	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}
}
