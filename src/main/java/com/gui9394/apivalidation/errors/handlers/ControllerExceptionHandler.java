package com.gui9394.apivalidation.errors.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gui9394.apivalidation.errors.entitys.Error;
import com.gui9394.apivalidation.errors.entitys.ValidationError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> ticketFinalizadoException(MethodArgumentNotValidException exception, HttpServletRequest request) {

        List<ValidationError> erros = new ArrayList<>();

        for (FieldError validationError : exception.getBindingResult().getFieldErrors()) {
            erros.add(new ValidationError(validationError.getField(), validationError.getRejectedValue().toString(),
                    validationError.getDefaultMessage()));
        }

        Error error = new Error(LocalDateTime.now(), HttpStatus.BAD_REQUEST, erros);

        System.out.println(request.getPathInfo());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> allException(Exception exception) {

        List<ValidationError> erros = new ArrayList<>();

        Error error = new Error(LocalDateTime.now(), HttpStatus.BAD_REQUEST, erros);

        System.out.println(" ----  Erro capturado  ----  ");
        System.out.println(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
