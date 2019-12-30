package com.gui9394.apivalidation.configs;

import com.gui9394.apivalidation.errors.ApiErrorAttributes;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebErrorConfiguration {

    @Bean
    public ErrorAttributes errorAttributes() {
        return new ApiErrorAttributes();
    }

}
