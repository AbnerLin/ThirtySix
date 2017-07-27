package com.thirtySix.dto;

public class AjaxRDTO {
	private static final int SUCCESS = 1;
	private static final int FAIL = 0;

	private int status = FAIL;

	private String message = "";

	private String action = "";

	private Object data;

	public int getStatus() {
		return status;
	}

	public void setStatusOK() {
		this.status = SUCCESS;
	}

	public void setStatusFail() {
		this.status = FAIL;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
