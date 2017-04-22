package com.smatt.controllers.admin;


import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by smatt on 05/04/2017.
 */
@Controller
@RequestMapping(value = "/eyin")
public class AdminIndexController {

    Logger logger = Logger.getLogger(AdminIndexController.class);

    @GetMapping
    public String index(ModelMap model) {
        model.addAttribute("dashboardMenu", true);
        return "admin/index";
    }


}
