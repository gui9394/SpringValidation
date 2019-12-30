package com.gui9394.apivalidation.errors;

public class ValidationError {

	private String field;

	private String value;

	private String message;

	public ValidationError(String field, String value, String message) {
		this.field = field;
		this.value = value;
		this.message = message;
	}

	public String getField() {
		return this.field;
	}

	public String getValue() {
		return this.value;
	}

	public String getMessage() {
		return this.message;
	}
}
