package com.smatt.controllers.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by smatt on 21/06/2017.
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController {


    @GetMapping(value = "/404")
    public String handle404() {
        return "error/404";
    }

    @GetMapping(value = {"/", "/500", ""})
    public String handleGeneralError() {
        return "error/500";
    }

    @GetMapping(value = "/accessdenied")
    public String handleAccessDenied() {
        return "error/accessdenied";
    }


}
