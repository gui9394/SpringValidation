package com.gui9394.apivalidation.errors.entitys;

public class ItemError {

	private String message;

	private int code;

	public ItemError(String message, int code) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public int getCode() {
		return this.code;
	}

}
