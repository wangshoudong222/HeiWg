package com.wangshoudog.jgatewayrequest.bean;

/**
 * 网关信息统一返回
 */
public class WlWgResponseBean {
	/**
	 * 状态码
	 */
	public String code;
	/**
	 * 信息
	 */
    public String message;
	/**
	 * 数据
	 */
	public String data;

	public WlWgErrorResponse error_response;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public WlWgErrorResponse getError_response() {
		return error_response;
	}

	public void setError_response(WlWgErrorResponse error_response) {
		this.error_response = error_response;
	}
}
