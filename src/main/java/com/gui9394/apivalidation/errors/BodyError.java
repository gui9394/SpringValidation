package com.gui9394.apivalidation.errors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gui9394.apivalidation.errors.ValidationError;

import org.springframework.http.HttpStatus;

public class BodyError {

    private int status;

    private String error;

    private LocalDateTime timestamp;

    private List<ValidationError> errors;

    public BodyError(HttpStatus status, LocalDateTime timestamp) {
        this(status, timestamp, new ArrayList<>());
    }

    public BodyError(HttpStatus status, LocalDateTime timestamp, List<ValidationError> errors) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public int getStatus() {
        return this.status;
    }

    public String getError() {
        return this.error;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public List<ValidationError> getErros() {
        return this.errors;
    }
}
