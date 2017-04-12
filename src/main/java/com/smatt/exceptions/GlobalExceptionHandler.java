package com.smatt.exceptions;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by smatt on 10/04/2017.
 */
@ControllerAdvice
@Controller
public class GlobalExceptionHandler {

    Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String generalExceptionHandler(Exception e) {
        logger.info("Global Exception Handler Invoked ! " + e.getMessage());
        e.printStackTrace();
        return "redirect:/login";
    }




}
