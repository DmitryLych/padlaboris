package com.instinctools.padlaboris.config;

import com.instinctools.padlaboris.application.interceptor.AccessControlHeaderInterceptor;
import com.instinctools.padlaboris.application.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Configuration for interceptors.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor());
        registry.addInterceptor(new AccessControlHeaderInterceptor());
    }
}
