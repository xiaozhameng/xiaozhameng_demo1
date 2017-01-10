package com.xiaozhameng.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateFormatUtils;

public class DateTool {
	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "yyyyMMddHHmmss";

	/** 完整时间 yyyy-MM-dd HH:mm:ss */
	public static final String simple = "yyyy-MM-dd HH:mm:ss";

	/** 年月日(无下划线) yyyyMMdd */
	public static final String dtShort = "yyyy-MM-dd";

	/** 年月日(无下划线) yyyy.MM.dd */
	public static final String enShort = "yyyy.MM.dd";

	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDate() {
		return getDate(new Date(), null);
	}

	/**
	 * 获取相差天数的时间
	 * @param diff 之前天数负数 如 -1，之后天数正数 如 1
	 * */
	public static Date getNextDay(Date date,int diff) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, diff);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 获取当前时间相差天数的时间
	 * @param diff 之前天数负数 如 -1，之后天数正数 如 1
	 * */
	public static Date getNextDay(int diff) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, diff);
		return calendar.getTime();
	}

	/**
	 * 获取时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		return getDate(date, null);
	}

	/**
	 * 获取时间string类型
	 * 
	 * @param d
	 *            时间
	 * @param format
	 *            格式
	 * @return
	 */
	public static String getDate(Date d, String format) {
		if (null == d) {
			return "";
		}
		if (null == format || "".equals(format)) {
			format = dtShort;
		}
		return DateFormatUtils.format(d, format);
	}

	/**
	 * 验证日期格式，yyyyMMdd
	 * 
	 * @param str
	 * @return
	 */
	public static boolean regDate(String str) {
		Pattern pattern = Pattern.compile("[0-9]{4}[0-9]{2}[0-9]{2}");
		if (StringUtil.isNullOrEmpty(str)) {
			return false;
		} else {
			Matcher matcher = pattern.matcher(str);
			return matcher.matches();
		}
	}

	/**
	 * 将指定格式的字符串转换为日期型
	 * 
	 * @param strDate
	 *            - 日期
	 * @param oracleFormat
	 *            --oracle型日期格式
	 * @return 转换得到的日期
	 */
	public static Date stringToDate(String strDate, String oracleFormat) {
		if (strDate == null)
			return null;
		Hashtable h = new Hashtable();
		String javaFormat = new String();
		String s = oracleFormat.toLowerCase();
		if (s.indexOf("yyyy") != -1)
			h.put(new Integer(s.indexOf("yyyy")), "yyyy");
		else if (s.indexOf("yy") != -1)
			h.put(new Integer(s.indexOf("yy")), "yy");
		if (s.indexOf("mm") != -1)
			h.put(new Integer(s.indexOf("mm")), "MM");

		if (s.indexOf("dd") != -1)
			h.put(new Integer(s.indexOf("dd")), "dd");
		if (s.indexOf("hh24") != -1)
			h.put(new Integer(s.indexOf("hh24")), "HH");
		if (s.indexOf("mi") != -1)
			h.put(new Integer(s.indexOf("mi")), "mm");
		if (s.indexOf("ss") != -1)
			h.put(new Integer(s.indexOf("ss")), "ss");

		int intStart = 0;
		while (s.indexOf("-", intStart) != -1) {
			intStart = s.indexOf("-", intStart);
			h.put(new Integer(intStart), "-");
			intStart++;
		}

		intStart = 0;
		while (s.indexOf("/", intStart) != -1) {
			intStart = s.indexOf("/", intStart);
			h.put(new Integer(intStart), "/");
			intStart++;
		}

		intStart = 0;
		while (s.indexOf(" ", intStart) != -1) {
			intStart = s.indexOf(" ", intStart);
			h.put(new Integer(intStart), " ");
			intStart++;
		}

		intStart = 0;
		while (s.indexOf(":", intStart) != -1) {
			intStart = s.indexOf(":", intStart);
			h.put(new Integer(intStart), ":");
			intStart++;
		}

		if (s.indexOf("年") != -1)
			h.put(new Integer(s.indexOf("年")), "年");
		if (s.indexOf("月") != -1)
			h.put(new Integer(s.indexOf("月")), "月");
		if (s.indexOf("日") != -1)
			h.put(new Integer(s.indexOf("日")), "日");
		if (s.indexOf("时") != -1)
			h.put(new Integer(s.indexOf("时")), "时");
		if (s.indexOf("分") != -1)
			h.put(new Integer(s.indexOf("分")), "分");
		if (s.indexOf("秒") != -1)
			h.put(new Integer(s.indexOf("秒")), "秒");

		int i = 0;
		while (h.size() != 0) {
			Enumeration e = h.keys();
			int n = 0;
			while (e.hasMoreElements()) {
				i = ((Integer) e.nextElement()).intValue();
				if (i >= n)
					n = i;
			}
			String temp = (String) h.get(new Integer(n));
			h.remove(new Integer(n));

			javaFormat = temp + javaFormat;
		}
		SimpleDateFormat df = new SimpleDateFormat(javaFormat);

		Date myDate = new Date();
		try {
			myDate = df.parse(strDate);
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}

		return myDate;
	}

	/**
	 * 比较两个时间先后
	 * 
	 * @param str1
	 *            传入的字符串
	 * @param str2
	 *            传入的字符串
	 * @return int negative integer, zero, or a positive integer as str1 is less
	 *         than, equal to, or greater than str2
	 * */
	public static int compareDate(String str1, String str2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date1 = null, date2 = null;
		try {
			date1 = formatter.parse(str1);
			date2 = formatter.parse(str2);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date1.compareTo(date2);
	}

	/**
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return
	 */
	public static int compareDate(String date1, String date2, int stype) {
		int n = 0;

		String[] u = { "天", "月", "年" };
		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

		date2 = date2 == null ? getCurrentYMDDate() : date2;

		java.text.DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			System.out.println("wrong occured");
		}
		// List list = new ArrayList();
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			// list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}

		n = n - 1;

		if (stype == 2) {
			n = (int) n / 365;
		}
		return n;
	}

	/**
	 * 得到当前日期
	 * 
	 * @return
	 */
	public static String getCurrentYMDDate() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		return simple.format(date);

	}

	public static int compareDate(String str1, Date date2) {
		Date date1 = getDateByString(str1);
		return date1.compareTo(date2);
	}

	public static int compareDate(String format, String str1, Date date2) {

		Date date1 = null;
		try {
			date1 = fromStringToDate(format, str1);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return date1.compareTo(date2);
	}

	/**
	 * 根据传入的日期字符串转换成相应的日期对象，如果字符串为空或不符合日期格 式，则返回当前时间。
	 * 
	 * @param strDate
	 *            String 日期字符串
	 * @return java.sql.Timestamp 日期对象
	 * */
	public static java.sql.Timestamp getDateByString(String strDate) {
		if (strDate.trim().equals("")) {
			return new java.sql.Timestamp(System.currentTimeMillis());
		}
		try {
			strDate = getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss") + ".000000000";
			return java.sql.Timestamp.valueOf(strDate);
		} catch (Exception ex) {
			return new java.sql.Timestamp(System.currentTimeMillis());
		}
	}

	public static java.sql.Timestamp getNextMonyDate(String strDate) {
		try {
			int iYear = StringUtil.getStrToInt(getFormattedDate(strDate, "yyyy"));
			int iMonth = StringUtil.getStrToInt(getFormattedDate(strDate, "MM"));
			if (iMonth == 12) {
				iYear = iYear + 1;
			} else {
				iMonth = iMonth + 1;
			}
			String strMonth = Integer.toString(iMonth);
			if (strMonth.length() == 1) {
				strMonth = "0" + strMonth;
			}
			strDate = Integer.toString(iYear) + "/" + strMonth + "/01";
			return getDateByString(strDate);
		} catch (Exception ex) {
			return getDateByString(strDate);
		}
	}

	// /**
	// * 根据参数名称，从request对象中取出该参数，并把该参数转换成GB2312编码的字符集。
	// * @param request 请求对象
	// * @param strParamName 参数名称
	// * @return java.sql.Date 转换后的参数值
	// * */
	// public static java.sql.Timestamp getDateFromReqParam(HttpServletRequest
	// request, String strParamName)
	// {
	// String strStr =
	// StringUtil.getNotNullStr(request.getParameter(strParamName));
	// return getDateByString(strStr);
	// }
	/**
	 * 得到当前日期，格式yyyy-MM-dd。
	 * 
	 * @return String 格式化的日期字符串
	 */
	public static String getCurrDate() {
		return getFormattedDate(getDateByString(""));
	}

	/**
	 * 得到当前日期，格式yyyy-MM-dd。
	 * 
	 * @return String 格式化的日期字符串
	 */
	public static String getToday() {
		Date cDate = new Date();
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return cSimpleDateFormat.format(cDate);
	}

	/**
	 * 得到当前日期，格式yyyy-MM-dd。
	 * 
	 * @return String 格式化的日期字符串
	 */
	public static String getYesterday() {
		Date cDate = new Date();
		cDate.setTime(cDate.getTime() - 24 * 3600 * 1000);
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return cSimpleDateFormat.format(cDate);
	}

	/**
	 * 得到当前日期，格式yyyy-MM-dd。
	 * 
	 * @return String 格式化的日期字符串
	 */
	public static String getTomorrow() {
		Date cDate = new Date();
		cDate.setTime(cDate.getTime() + 24 * 3600 * 1000);
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return cSimpleDateFormat.format(cDate);
	}

	/**
	 * 返回默认的功能生效的时间，1900/01/01。
	 * 
	 * @return String 默认的实效时间字符串
	 * */
	public static String getDefaultValidDate() {
		return "1900-01-01";
	}

	/**
	 * 返回默认的功能失效的时间，2099/12/31。
	 * 
	 * @return String 默认的实效时间字符串
	 * */
	public static String getDefaultExpireDate() {
		return "2099-12-31";
	}

	/**
	 * 得到当前日期时间,格式为yyyy-MM-dd hh:mm:ss.
	 * 
	 * @return String
	 */
	public static String getCurrDateTime() {
		java.sql.Timestamp date = new java.sql.Timestamp(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return formatter.format(date);
	}

	/**
	 * 得到指定的日期，如一年三个月零九天后(以yyyy/MM/dd格式显示)参数为("yyyy/MM/dd",1,3,9)
	 * 
	 * @param strFormat
	 *            strFormat
	 * @param iYear
	 *            iYear
	 * @param iMonth
	 *            iMonth
	 * @param iDate
	 *            iDate
	 * @return String
	 */
	public static String getSpecDate(String strFormat, int iYear, int iMonth, int iDate) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.set(Calendar.YEAR, rightNow.get(Calendar.YEAR) + iYear);
		rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH) + iMonth);
		rightNow.set(Calendar.DATE, rightNow.get(Calendar.DATE) + iDate);
		SimpleDateFormat df = new SimpleDateFormat(strFormat);
		return df.format(rightNow.getTime());
	}

	/**
	 * 对输入的日期字符串进行默认的格式yyyy-MM-dd HH:mm:ss转换。
	 * 
	 * @param strDate
	 *            String 需要进行格式化的日期字符串
	 * @return String 经过格式化后的字符串
	 */
	public static String getDefaultFormattedDate(String strDate) {
		return getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 对输入的日期进行默认的格式yyyy-MM-dd HH:mm:ss转换。
	 * 
	 * @param dtDate
	 *            需要进行格式化的日期
	 * @return String 经过格式化后的字符串
	 */
	public static String getDefaultFormattedDate(java.sql.Timestamp dtDate) {
		return getFormattedDate(dtDate, "yyyy-MM-dd HH:mm:ss");
	}

	public static java.sql.Timestamp getNullBirthDay() {
		return new java.sql.Timestamp(0);
	}

	/**
	 * 对输入的日期字符串按照默认的格式yyyy-MM-dd转换.
	 * 
	 * @param strDate
	 *            String 需要进行格式化的日期字符串
	 * @return String 经过格式化后的字符串
	 */
	public static String getFormattedDate(String strDate) {
		return getFormattedDate(strDate, "yyyy-MM-dd");
	}

	/**
	 * 对输入的日期字符串进行格式化,如果输入的是0000/00/00 00:00:00则返回空串.
	 * 
	 * @param strDate
	 *            String 需要进行格式化的日期字符串
	 * @param strFormatTo
	 *            String 要转换的日期格式
	 * @return String 经过格式化后的字符串
	 */
	public static String getFormattedDate(String strDate, String strFormatTo) {
		if (strDate == null || strDate.trim().equals("")) {
			return "";
		}
		strDate = strDate.replace('/', '-');
		strFormatTo = strFormatTo.replace('/', '-');
		if (strDate.equals("0000-00-00 00:00:00") || strDate.equals("1800-01-01 00:00:00")) {
			return "";
		}
		String formatStr = strFormatTo; // "yyyyMMdd";
		if (strDate == null || strDate.trim().equals("")) {
			return "";
		}
		switch (strDate.trim().length()) {
		case 6:
			if (strDate.substring(0, 1).equals("0")) {
				formatStr = "yyMMdd";
			} else {
				formatStr = "yyyyMM";
			}
			break;
		case 8:
			formatStr = "yyyyMMdd";
			break;
		case 10:
			if (strDate.indexOf("-") == -1) {
				formatStr = "yyyy/MM/dd";
			} else {
				formatStr = "yyyy-MM-dd";
			}
			break;
		case 11:
			if (strDate.getBytes().length == 14) {
				formatStr = "yyyy年MM月dd日";
			} else {
				return "";
			}
		case 14:
			formatStr = "yyyyMMddHHmmss";
			break;
		case 19:
			if (strDate.indexOf("-") == -1) {
				formatStr = "yyyy/MM/dd HH:mm:ss";
			} else {
				formatStr = "yyyy-MM-dd HH:mm:ss";
			}
			break;
		case 21:
			if (strDate.indexOf("-") == -1) {
				formatStr = "yyyy/MM/dd HH:mm:ss.S";
			} else {
				formatStr = "yyyy-MM-dd HH:mm:ss.S";
			}
			break;
		default:
			return strDate.trim();
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(formatter.parse(strDate));
			formatter = new SimpleDateFormat(strFormatTo);
			return formatter.format(calendar.getTime());
		} catch (Exception e) {
			// Common.printLog("转换日期字符串格式时出错;" + e.getMessage());
			return "";
		}
	}

	/**
	 * 对输入的日期按照默认的格式yyyy-MM-dd转换.
	 * 
	 * @param dtDate
	 *            需要进行格式化的日期字符串
	 * @return String 经过格式化后的字符串
	 */
	public static String getFormattedDate(java.sql.Timestamp dtDate) {
		return getFormattedDate(dtDate, "yyyy-MM-dd");
	}

	/**
	 * 对输入的日期进行格式化, 如果输入的日期是null则返回空串.
	 * 
	 * @param dtDate
	 *            java.sql.Timestamp 需要进行格式化的日期字符串
	 * @param strFormatTo
	 *            String 要转换的日期格式
	 * @return String 经过格式化后的字符串
	 */
	public static String getFormattedDate(java.sql.Timestamp dtDate, String strFormatTo) {
		if (dtDate == null) {
			return "";
		}
		if (dtDate.equals(new java.sql.Timestamp(0))) {
			return "";
		}
		strFormatTo = strFormatTo.replace('/', '-');
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			if (Integer.parseInt(formatter.format(dtDate)) < 1900) {
				return "";
			} else {
				formatter = new SimpleDateFormat(strFormatTo);
				return formatter.format(dtDate);
			}
		} catch (Exception e) {
			// Common.printLog("转换日期字符串格式时出错;" + e.getMessage());
			return "";
		}
	}

	/**
	 * 把秒数转换成hh:mm:ss格式
	 * 
	 * @param lSecond
	 *            long
	 * @return String
	 * */
	public static String getTimeFormat(long lSecond) {
		String szTime = new String();

		if (lSecond <= 0) {
			szTime = "00" + ":" + "00" + ":" + "00";
		} else {
			long hour = lSecond / 3600;
			long minute = (lSecond - hour * 3600) / 60;
			long second = (lSecond - hour * 3600 - minute * 60);

			if (hour <= 0) {
				szTime = "00";
			} else if (hour < 10) {
				szTime = "0" + String.valueOf(hour);
			} else {
				szTime = String.valueOf(hour);
			}
			szTime = szTime + ":";

			if (minute <= 0) {
				szTime = szTime + "00";
			} else if (minute < 10) {
				szTime = szTime + "0" + String.valueOf(minute);
			} else {
				szTime = szTime + String.valueOf(minute);
			}
			szTime = szTime + ":";

			if (second <= 0) {
				szTime = szTime + "00";
			} else if (second < 10) {
				szTime = szTime + "0" + String.valueOf(second);
			} else {
				szTime = szTime + String.valueOf(second);
			}
		}

		return szTime;
	}

	public static String getFormattedDateUtil(Date dtDate, String strFormatTo) {
		if (dtDate == null) {
			return "";
		}
		strFormatTo = strFormatTo.replace('/', '-');
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(strFormatTo);
			return formatter.format(dtDate);
		} catch (Exception e) {
			// Common.printLog("转换日期字符串格式时出错;" + e.getMessage());
			return "";
		}
	}

	/**
	 * 得出两个日期之间的间隔天数
	 * 
	 * @param strFromDate
	 *            格式为yyyyMMdd
	 * @param strToDate
	 *            格式为yyyyMMdd
	 * @return int
	 */
	public static int getBetweenDays(String strFromDate, String strToDate) {
		try {
			Calendar clFrom = new GregorianCalendar();
			int iYear = Integer.parseInt(strFromDate.substring(0, 4));
			int iMonth = Integer.parseInt(strFromDate.substring(4, 6));
			int iDay = Integer.parseInt(strFromDate.substring(6, 8));
			clFrom.set(iYear, iMonth, iDay, 0, 0, 0);
			Calendar clTo = new GregorianCalendar();
			iYear = Integer.parseInt(strToDate.substring(0, 4));
			iMonth = Integer.parseInt(strToDate.substring(4, 6));
			iDay = Integer.parseInt(strToDate.substring(6, 8));
			clTo.set(iYear, iMonth, iDay, 0, 0, 0);
			long llTmp = clTo.getTime().getTime() - clFrom.getTime().getTime();
			return new Long(llTmp / 1000 / 3600 / 24).intValue();
		} catch (Exception e) {
			return Integer.MIN_VALUE;
		}
	}

	/**
	 * 
	 * 字符串形式转化为Date类型 String类型按照format格式转为Date类型
	 **/
	public static Date fromStringToDate(String format, String dateTime) throws ParseException {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		date = sdf.parse(dateTime);
		return date;
	}

	/**
	 * 取两个Date之间的天数差<br>
	 * <br>
	 * 例：newerDate是6月3日，olderDate是5月31日，则应返回3 <br>
	 * 一个更极端的列子：newerDate是6月3日 00:01，olderDate是6月2日
	 * 23:59，则应返回1，说明相差一天，即便实际上只差2分钟 <br>
	 * 此代码来自网上 <br>
	 * http://blog.csdn.net/rmartin/article/details/1452867
	 * 
	 * @param newerDate
	 * @param olderDate
	 * @return
	 */
	public static int daysBetween(Date newerDate, Date olderDate) {
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime(newerDate);
		cReturnDate.setTime(olderDate);
		setTimeToMidnight(cNow);
		setTimeToMidnight(cReturnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsToDays(intervalMs);
	}

	private static int millisecondsToDays(long intervalMs) {
		return (int) (intervalMs / (1000 * 86400));
	}

	private static void setTimeToMidnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}

	public final static String sdfShort = "yyyyMMdd";
	public final static String sdfLong = "yyyy-MM-dd";
	public final static String sdfLongCn = "yyyy年MM月dd日";
	public final static String sdfShortU = "MMM dd";// ,Locale.ENGLISH;
	public final static String sdfLongU = "MMM dd,yyyy";// ,Locale.ENGLISH;
	public final static String sdfLongTime = "yyyyMMddHHmmss";
	public final static String sdfShortLongTimePlusCn = "yyyy年MM月dd日 HH:mm";
	public final static String sdfLongTimePlusMill = "yyyyMMddHHmmssSSSS";
	public final static String sdfMd = "MM月dd日";

	public static String getDateShort(Date date) {
		String nowDate = "";
		try {
			if (date != null)
				nowDate = DateFormatUtils.format(date, sdfShort);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}

	public static String getDateLong(Date date) {
		String nowDate = "";
		try {
			if (date != null)
				nowDate = DateFormatUtils.format(date, sdfLong);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}
	
	/**
	 * 得到系统当前年月日数据字符串
	 * @return strYMD 返回的结果，格式 yyyyMMdd ，String 类型
	 * @exception
	 */
	public static String getYMD() {
		String strYMD = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		strYMD = formatter.format(currentDateTime);
		return strYMD;
	}

	/**
	 * 得到系统当前年月日小时分秒数据字符串
	 * @return strYMDHMS 返回的结果，格式 yyyyMMddHHmmss，String 类型
	 * @exception
	 */
	public static String getHMS() {
		String strYMDHMS = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		strYMDHMS = formatter.format(currentDateTime);
		return strYMDHMS;
	}
}
