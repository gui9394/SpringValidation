package com.gui9394.apivalidation.errors.handlers;

import com.gui9394.apivalidation.errors.entitys.APIError;
import com.gui9394.apivalidation.errors.entitys.ErrorItem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> ticketFinalizadoException(MethodArgumentNotValidException exception) {
        APIError error = new APIError();

        for (ObjectError errors : exception.getBindingResult().getAllErrors()) {
            error.getItems().add(new ErrorItem("Requisição mal formada", errors.getDefaultMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}