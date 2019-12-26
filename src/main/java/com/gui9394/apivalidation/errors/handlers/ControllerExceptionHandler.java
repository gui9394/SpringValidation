package com.gui9394.apivalidation.errors.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gui9394.apivalidation.errors.entitys.Error;
import com.gui9394.apivalidation.errors.entitys.ItemError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> ticketFinalizadoException(MethodArgumentNotValidException exception) {
        
        List<ItemError> erros = new ArrayList<>();
        
        for (ObjectError errors : exception.getBindingResult().getAllErrors()) {
            erros.add(new ItemError(errors.getDefaultMessage()));
        }

        Error error = new Error(LocalDateTime.now(), HttpStatus.BAD_REQUEST, erros);

        System.out.println(" ----  Erro capturado  ----  ");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> allExceptin(Exception exception) {
        
        List<ItemError> erros = new ArrayList<>();

        Error error = new Error(LocalDateTime.now(), HttpStatus.BAD_REQUEST, erros);

        System.out.println(" ----  Erro capturado  ----  ");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
