package com.gui9394.apivalidation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.gui9394.apivalidation.errors.BodyError;
import com.gui9394.apivalidation.errors.ValidationError;

@RestControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BodyError> methodArgumentNotValidException(MethodArgumentNotValidException exception) {

        LocalDateTime timeStamp = LocalDateTime.now();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<ValidationError> erros = exception.getBindingResult().getFieldErrors().stream()
                .map((FieldError mapper) -> {
                    return new ValidationError(mapper.getField(), mapper.getRejectedValue().toString(),
                            mapper.getDefaultMessage());
                }).collect(Collectors.toList());

        return new ResponseEntity<>(new BodyError(status, timeStamp, erros), status);
    }

    @ExceptionHandler(Exception.class)
    public HttpServletResponse allException(Exception exception, HttpStatus status, HttpServletResponse response) {

        return response;
    }
}
