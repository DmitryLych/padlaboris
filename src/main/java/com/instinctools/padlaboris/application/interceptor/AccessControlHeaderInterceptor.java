package com.instinctools.padlaboris.application.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor for handling access control headers.
 */
public class AccessControlHeaderInterceptor extends HandlerInterceptorAdapter {

    /**
     * Method where Access Control headers are set.
     * @param request http request
     * @param response http response
     * @param handler handler
     * @return call to super
     * @throws Exception thrown if something goes wrong
     */
    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "access-token, Token, Content-Type");
        response.setHeader("Access-Control-Allow-Methods",
                "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Expose-Headers", "access-token, Content-Type");
        return super.preHandle(request, response, handler);
    }
}
