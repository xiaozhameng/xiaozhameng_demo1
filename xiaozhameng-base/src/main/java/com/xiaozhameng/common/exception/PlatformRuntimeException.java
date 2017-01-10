package com.xiaozhameng.common.exception;
import com.xiaozhameng.common.utils.MessageHelper;

/**
* @Description: 通用Runtime异常，所有自定义的checked异常需要继承此类
* @author liyi   
* @date 2015年4月17日 
* @version V1.0   
*/
public class PlatformRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -227961057827878851L;

	private String code;

	public PlatformRuntimeException() {
	}

	public PlatformRuntimeException(String code) {
		super(MessageHelper.getInstance().getMessageByCode(code) == null ? code : MessageHelper
				.getInstance().getMessageByCode(code));
		this.code = code;
	}

	public PlatformRuntimeException(Throwable t) {
		super(t);
	}

	public PlatformRuntimeException(String code, Throwable t) {
		super(MessageHelper.getInstance().getMessageByCode(code) == null ? code : MessageHelper
				.getInstance().getMessageByCode(code), t);
		this.code = code;
	}

	public PlatformRuntimeException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public PlatformRuntimeException(String code, Object[] msgArgs) {
		super(MessageHelper.getInstance().getMessageByCode(code, msgArgs));
		this.code = code;
	}
	
	public PlatformRuntimeException(String code, String msg, Throwable t) {
		super(msg, t);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
