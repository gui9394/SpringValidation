package com.gui9394.apivalidation.errors;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.gui9394.apivalidation.errors.BodyError;

public class ApiErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(final WebRequest webRequest, final boolean includeStackTrace) {
        BodyError bodyError = new BodyError(getHttpStatus(webRequest), LocalDateTime.now());

        Map<String, Object> map = new HashMap<String, Object>();

        Arrays.stream(BodyError.class.getDeclaredFields()).forEach((Field field) -> {
            field.setAccessible(true);

            try {
                map.put(field.getName(), field.get(bodyError));
            } catch (Exception e) {
            }
        });

        return map;
    }

    private HttpStatus getHttpStatus(final RequestAttributes requestAttributes) {
        final Integer status = getAttribute(requestAttributes, "javax.servlet.error.status_code");

        if (status == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return HttpStatus.valueOf(status);
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(final RequestAttributes requestAttributes, final String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

}
