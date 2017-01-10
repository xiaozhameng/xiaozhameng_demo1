package com.xiaozhameng.common.message;

import com.xiaozhameng.common.utils.MessageHelper;


/**
 * 返回数据 的 封装对象
 * 
 * @author liuqc
 * 
 * @param <T>
 */
public class ResultData<T> implements java.io.Serializable {
	private static final long serialVersionUID = 4917480918640310535L;

	private String code = CommonMessage.SUCCESS;// 消息代码
	private String msg = "操作成功";// 消息描述
	private T data;// 数据对象

	public ResultData() {
	}

	public ResultData(String code) {
		this.code = code;
		this.msg = MessageHelper.getInstance().getMessageByCode(this.code);
	}

	public ResultData(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		this.msg = MessageHelper.getInstance().getMessageByCode(this.code);
	}

	public String getMsg() {
		if(msg == null || msg.length() == 0){
			msg = "未知错误";
		}
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccessful() {
		return CommonMessage.SUCCESS.equals(this.code);
	}
	public boolean isDoing() {
		return CommonMessage.IS_DOING.equals(this.code);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultData [code=");
		builder.append(code);
		builder.append(", msg=");
		builder.append(msg);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
}
