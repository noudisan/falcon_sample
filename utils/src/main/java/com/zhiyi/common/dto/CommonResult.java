package com.zhiyi.common.dto;


import java.io.Serializable;

/**
 * 基础传输返回的结果类型
 * 成功不需要设置msg，失败是调用doErrorHandle，传入错误信息，错误信息是提升给客户看的，所以不要写程序语言！！！
 */
public class CommonResult<T> implements Serializable {

	public static final int RESULT_STATUS_SUCCESS = 1;
	public static final int RESULT_STATUS_FAILURE = 0;

	private static final long serialVersionUID = -1382214402932618730L;
	private String msg = "";
	private int code = RESULT_STATUS_SUCCESS ;

	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void buildCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public void doErrorHandle(String errorMessage) {
		this.setCode(RESULT_STATUS_FAILURE);
		this.setMsg(errorMessage);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("返回的数据是 code [ ").append(code).append(" ]").append(" -msg [")
				.append(msg).append("]").append("-realData [")
				.append(data).append("]");
		return sb.toString();
	}
	
	public boolean isSuccess(){
		return RESULT_STATUS_SUCCESS == this.getCode();
	}
	public boolean isNotSuccess(){
		return ! isSuccess();
	}
}

