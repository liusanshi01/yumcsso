package com.para4digm.yumcdpl.util.annotation;

/**
 * 通用响应
 * @author:
 * Created on 2016年2月25日 上午8:51:03
 * @modify author:修改人
 * Modify on 修改时间
*/
public class GenericResponse<T> {
	
	private String requestId;

	// 错误类型
	private String code;

	// 执行成功还是失败。
	private Object success;
	
	private String message;

	// 错误类型，如果是BUSI_ERROR，则为业务异常，业务异常不打印异常日志。
	private String errorType;
	
	private T data;

	public static String busiErrorType = "BUSI_ERROR";
	
	public GenericResponse() {
		super();
	}

	public GenericResponse(String requestId, T result) {
		this(requestId, "200", true, "成功", result);
	}
	
	public GenericResponse(String requestId, String code, boolean success,
                           String message, T data) {
		super();
		this.requestId = requestId;
		this.code = code;
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public GenericResponse(String requestId, String code, boolean success,
                           String message, T result, String errorType) {
		super();
		this.requestId = requestId;
		this.code = code;
		this.success = success;
		this.message = message;
		this.data = result;
		this.errorType = errorType;
	}

	//**********************
	// Getter & Setter
	//**********************
	public String getRequestId() {
		return requestId;
	}

	public String getCode() {
		return code;
	}
	
	public void setSuccess(Object success){
		this.success = success;
	}

	public boolean isSuccess() {
		if(!Boolean.class.isAssignableFrom(success.getClass())){
			if("1".equals(success.toString())){
				success = true;
			}else{
				success = false;
			}
		}
		return (boolean)success;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return "success: " + isSuccess() 
				+ ", code: " + getCode() 
				+ ", msg: " + getMessage()
				+ ", requestId: " + getRequestId()
				+ ", errorType: " + getErrorType()
				+ ", data: " + getData();
	}

	public Object getSuccess() {
		return success;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(T result) {
		this.data = result;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
}
