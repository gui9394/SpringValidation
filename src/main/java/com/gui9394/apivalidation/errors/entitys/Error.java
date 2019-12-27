package com.gui9394.apivalidation.errors.entitys;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.http.HttpStatus;

public class Error {

	private LocalDateTime timeStamp;

	private HttpStatus status;

	private List<ValidationError> erros = new ArrayList<>();

	public Error(LocalDateTime timeStamp, HttpStatus status, List<ValidationError> erros) {
		this.timeStamp = timeStamp;
		this.status = status;
		this.erros = erros;
	}

	public LocalDateTime getTimeStamp() {
		return this.timeStamp;
	}

	@JsonProperty(value = "status")
	public int getStatus() {
		return this.status.value();
	}

	@JsonProperty(value = "message")
	public String getMessage() {
		return this.status.getReasonPhrase();
	}

	public List<ValidationError> getErros() {
		return this.erros;
	}

}
