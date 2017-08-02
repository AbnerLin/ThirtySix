package com.thirtySix.dto;

public class AjaxRDTO {
	private static final boolean SUCCESS = true;
	private static final boolean FAIL = false;

	private boolean status = FAIL;

	private String message = "";

	private String action = "";

	private Object data;

	public boolean getStatus() {
		return this.status;
	}

	public void setStatusOK() {
		this.status = SUCCESS;
	}

	public void setStatusFail() {
		this.status = FAIL;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(final String action) {
		this.action = action;
	}

	public Object getData() {
		return this.data;
	}

	public void setData(final Object data) {
		this.data = data;
	}
}
