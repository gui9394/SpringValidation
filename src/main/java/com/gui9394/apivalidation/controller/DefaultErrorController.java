package com.gui9394.apivalidation.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping({ DefaultErrorController.ERROR_PATH })
public class DefaultErrorController extends AbstractErrorController {

    static final String ERROR_PATH = "/error";

    public DefaultErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping
    @ResponseBody
    public Map<String, Object> error(HttpServletRequest request) {
        return this.getErrorAttributes(request, false);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
