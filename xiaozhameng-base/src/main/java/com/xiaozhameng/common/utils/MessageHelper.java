package com.xiaozhameng.common.utils;

import java.text.MessageFormat;


public class MessageHelper {
	private final static String PATH_MATCHING = "classpath*:message/*.properties";
	private static PropertiesLoader propertiesLoader = new PropertiesLoader(PATH_MATCHING);

	private MessageHelper() {
	}

	private static MessageHelper instance = new MessageHelper();

	public static MessageHelper getInstance() {
		return instance;
	}

	public String getMessageByCode(String code) {
		return propertiesLoader.getProperty(code);
	}

	public String getMessageByCode(String code, Object... params) {
		return MessageFormat.format(propertiesLoader.getProperty(code), params);
	}
	
	public static boolean isErrorCode(String msg) {
		if(msg.length() == 6) {
			return msg.matches("[A-Z][0-9]{5}");
		}
		return false;
	}
}
