package com.gui9394.apivalidation.error;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@Component
public class ApiErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = new HashMap<String, Object>();

        LocalDateTime timeStamp = LocalDateTime.now();
        HttpStatus httpStatus = getHttpStatus(webRequest);
        String message = getMessage(webRequest);
        List<ApiFieldError> erros = getFieldsErrors(webRequest);

        map.put("timestamp", timeStamp);
        map.put("status", httpStatus.value());
        map.put("error", httpStatus.getReasonPhrase());
        map.put("message", message);
        map.put("errors", erros);

        return map;
    }

    private HttpStatus getHttpStatus(WebRequest webRequest) {
        Integer status = (Integer) webRequest.getAttribute("javax.servlet.error.status_code",
                RequestAttributes.SCOPE_REQUEST);

        return (status == null) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(status);
    }

    private String getMessage(WebRequest webRequest) {
        Throwable error = getError(webRequest);

        if (error == null) {
            return "No message available";
        }

        if ((error instanceof HttpMessageNotReadableException)
                && (error.getCause() instanceof InvalidFormatException)) {
            InvalidFormatException exception = (InvalidFormatException) error.getCause();

            return "Error validating field request '" + exception.getPath().get(0).getFieldName() + "'";
        } else if (error instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) error).getBindingResult();

            return "Error validating request. Error count " + bindingResult.getErrorCount();
        }

        return error.getMessage();
    }

    private List<ApiFieldError> getFieldsErrors(WebRequest webRequest) {
        Throwable error = getError(webRequest);

        if (error == null) {
            return new ArrayList<>();
        }

        if ((error instanceof HttpMessageNotReadableException)
                && (error.getCause() instanceof InvalidFormatException)) {
            InvalidFormatException exception = (InvalidFormatException) error.getCause();

            return Arrays.asList(new ApiFieldError(exception.getPath().get(0).getFieldName(), exception.getValue(),
                    "value is invalid for field"));
        } else if (error instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) error).getBindingResult();

            return bindingResult.getFieldErrors().stream().map((FieldError mapper) -> {
                return new ApiFieldError(mapper.getField(), mapper.getRejectedValue(), mapper.getDefaultMessage());
            }).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
