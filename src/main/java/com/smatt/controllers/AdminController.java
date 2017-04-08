package com.smatt.controllers;


import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by smatt on 05/04/2017.
 */
@Controller
@RequestMapping(value = "/eyin")
public class AdminController {

    Logger logger = Logger.getLogger(AdminController.class);

    @GetMapping
    public String index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Eyin index visited ! auth username = " + ((UserDetails)auth.getPrincipal()).getUsername());
        return "admin/index";
    }


}
