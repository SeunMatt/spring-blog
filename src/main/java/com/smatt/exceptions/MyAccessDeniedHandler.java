package com.smatt.exceptions;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by smatt on 10/04/2017.
 */
@ControllerAdvice
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    Logger logger = Logger.getLogger(MyAccessDeniedHandler.class);

    public MyAccessDeniedHandler() {
        logger.info("My Access Denied Handler Invoked");
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        logger.info("Access denied Handler Invoked!");
        e.printStackTrace();
        httpServletResponse.sendRedirect("/login");
    }
}
