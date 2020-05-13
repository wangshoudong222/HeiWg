package com.wangshoudog.jgatewayrequest.bean;

/**
 * 业务bean
 *
 */
public class BusinessBean {
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

	public BusinessBean() {

	}

	public BusinessBean(WlWgResponseBean responseBean) {
		this.code = responseBean.getCode();
		this.data = responseBean.getData();
		this.message = responseBean.getMessage();
	}

	public BusinessBean(WlWgErrorResponse errorResponse) {
		this.code = String.valueOf(errorResponse.getCode());
		this.message = errorResponse.getZh_desc();
	}

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
}
