package com.xiaozhameng.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

/**
 * 获取 spring 管理的 bean
 * 
 * @author liuqc
 * 
 */
public class ApplicationContextTool extends ApplicationObjectSupport {

	private static ApplicationContext applicationContext = null;
	@Override
	protected void initApplicationContext(ApplicationContext context)
			throws BeansException {
		super.initApplicationContext(context);
		if (ApplicationContextTool.applicationContext == null) {
			ApplicationContextTool.applicationContext = context;
		}
	}

	public static ApplicationContext getAppContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return getAppContext().getBean(name);
	}
	public static <T> T getBean(Class<T> clazz) {
		return getAppContext().getBean(clazz);
	}
	public static <T> T getBean(String name,Class<T> clazz) {
		return getAppContext().getBean(name,clazz);
	}
}
