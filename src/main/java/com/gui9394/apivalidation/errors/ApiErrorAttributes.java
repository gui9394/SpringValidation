package com.gui9394.apivalidation.errors;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
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

public class ApiErrorAttributes extends DefaultErrorAttributes {

    BodyError bodyError;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = new HashMap<String, Object>();

        LocalDateTime timeStamp = LocalDateTime.now();
        HttpStatus httpStatus = getHttpStatus(webRequest);
        String message = getMessage(webRequest);
        List<ValidationError> erros = getFieldsErrors(webRequest);

        map.put("timestamp", timeStamp);
        map.put("status", httpStatus.value());
        map.put("error", httpStatus.getReasonPhrase());
        map.put("message", message);
        map.put("errors", erros);

        return map;
    }

    private HttpStatus getHttpStatus(WebRequest webRequest) {
        Integer status = getAttribute(webRequest, "javax.servlet.error.status_code");

        return (status == null) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(status);
    }

    private List<ValidationError> getFieldsErrors1(WebRequest webRequest) {
        List<ValidationError> fieldErrors = new ArrayList<>();

        Throwable error = getError(webRequest);

        if (error == null) {
            return fieldErrors;
        }

        System.out.println("+++++++++++ " + error.getMessage());

        ValidationError invalidFormatError = extractFromInvalidFormatException(error);
        if (invalidFormatError != null) {
            fieldErrors.add(invalidFormatError);
        }

        List<ValidationError> validationErrors = extractFromBindingResult(error);
        if (validationErrors != null) {
            fieldErrors.addAll(validationErrors);
        }

        return fieldErrors;
    }

    private ValidationError extractFromInvalidFormatException(Throwable error) {
        InvalidFormatException exception = null;

        if (error instanceof InvalidFormatException) {
            exception = (InvalidFormatException) error;
        } else if (error.getCause() instanceof InvalidFormatException) {
            exception = (InvalidFormatException) error.getCause();
        }

        if (exception != null) {
            return new ValidationError(exception.getPath().get(0).getFieldName(), exception.getValue(),
                    "value is invalid for field");
        }

        return null;
    }

    private List<ValidationError> extractFromBindingResult(Throwable error) {
        BindingResult result = null;

        if (error instanceof BindingResult) {
            result = (BindingResult) error;
        } else if (error instanceof MethodArgumentNotValidException) {
            result = ((MethodArgumentNotValidException) error).getBindingResult();
        }

        if (result != null) {
            return result.getFieldErrors().stream().map((FieldError mapper) -> {
                return new ValidationError(mapper.getField(), mapper.getRejectedValue(), mapper.getDefaultMessage());
            }).collect(Collectors.toList());
        }

        return null;
    }

    private String getMessage(WebRequest webRequest) {
        Throwable error = getError(webRequest);

        if (error == null) {
            return "No message available";
        }

        if (error instanceof HttpMessageNotReadableException && error.getCause() instanceof InvalidFormatException) {
            InvalidFormatException exception = (InvalidFormatException) error.getCause();

            return "Error validating field request '" + exception.getPath().get(0).getFieldName() + "'";
        } else if (error instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) error).getBindingResult();

            return "Error validating request. Error count " + bindingResult.getErrorCount();
        }

        return error.getMessage();
    }

    private List<ValidationError> getFieldsErrors(WebRequest webRequest) {
        Throwable error = getError(webRequest);

        if (error == null) {
            return new ArrayList<>();
        }

        if (error instanceof HttpMessageNotReadableException && error.getCause() instanceof InvalidFormatException) {
            InvalidFormatException exception = (InvalidFormatException) error.getCause();

            return Arrays.asList(new ValidationError(exception.getPath().get(0).getFieldName(), exception.getValue(),
                    "value is invalid for field"));
        } else if (error instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) error).getBindingResult();

            return bindingResult.getFieldErrors().stream().map((FieldError mapper) -> {
                return new ValidationError(mapper.getField(), mapper.getRejectedValue(), mapper.getDefaultMessage());
            }).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }
}
