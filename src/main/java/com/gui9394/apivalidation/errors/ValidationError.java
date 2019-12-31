package com.gui9394.apivalidation.errors;

public class ValidationError {

	private String field;

	private Object value;

	private String message;

	public ValidationError(String field, Object value, String message) {
		this.field = field;
		this.value = value;
		this.message = message;
	}

	public String getField() {
		return this.field;
	}

	public Object getValue() {
		return this.value;
	}

	public String getMessage() {
		return this.message;
	}
}
