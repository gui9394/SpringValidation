package com.gui9394.apivalidation.errors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gui9394.apivalidation.errors.ValidationError;

import org.springframework.http.HttpStatus;

public class BodyError {

    private int status;

    private String error;

    private String message;

    private LocalDateTime timestamp;

    private List<ValidationError> errors;

    public BodyError(HttpStatus status, LocalDateTime timestamp, String message) {
        this(status, timestamp, message, new ArrayList<>());
    }

    public BodyError(HttpStatus status, LocalDateTime timestamp, String message, List<ValidationError> errors) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<ValidationError> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}
