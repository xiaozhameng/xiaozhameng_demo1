package com.xiaozhameng.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemDetails {
	private static Logger logger = LoggerFactory.getLogger(SystemDetails.class);
    /**
     * 输出系统基本信息
     */
    public static void outputDetails() {
        timeZone();
        currentTime();
        os();
    }

    /**
     * 输出系统时区
     */
    private static void timeZone() {
        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        logger.info("系统时区:" + timeZone.getDisplayName());
    }

    /**
     * 输出系统时间
     */
    private static void currentTime() {
        String fromFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(fromFormat);
        Date myDate = new Date();
        logger.info("系统时间:" + format.format(myDate));
    }

    /**
     * 输出系统基本配置
     */
    private static void os() {
        String osName = System.getProperty("os.name"); //操作系统名称
        logger.info("当前系统:" + osName);
        String osArch = System.getProperty("os.arch"); //操作系统构架
        logger.info("当前系统架构" + osArch);
        String osVersion = System.getProperty("os.version"); //操作系统版本
        logger.info("当前系统版本:" + osVersion);
    }
}
