package com.xiaozhameng.common.message;

/**
 * 通用消息
 * 
 * @author liuqc
 *
 */
public class CommonMessage {
	/**
	 * 操作成功
	 */
	public final static String SUCCESS = "C00000";
	/**
	 * 操作失败
	 */
	public final static String ERROR = "C00001";
	/**
	 * 对象为空
	 */
	public static final String OBJECT_IS_NULL = "C00002";
	/**
	 * 操作出现异常
	 */
	public static final String HAS_EXCEPTION = "C00003";
	/**
	 * 参数验证失败
	 */
	public static final String PARAM_VALIDATE_FAIL = "C00004";
	/**
	 * 业务订单号生成失败
	 */
	public static final String BUSINESS_ID_CREATE_FAIL = "C00005";

	/**异常**/
	public static final String EXCEPTION = "C00006";

	/**系统正在处理中，请稍后查看处理结果**/
	public static final String IS_DOING = "C00007";
	/**
	 * 重复操作
	 */
	public final static String DUPLICATE = "C00008";
	/**
	 * 未知
	 */
	public final static String UNKNOWN = "C00009";
	
	/**
	 * 恶意用户
	 */
	public final static String MALICIOUS_USER = "C00010";
}
