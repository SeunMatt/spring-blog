package com.smatt.controllers;

import com.smatt.dao.PostRepository;
import com.smatt.models.User;
import com.smatt.service.UserService;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by smatt on 21/03/2017.
 */
@Controller
public class HomeController {

//    @Autowired
//    public UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authManager;
	
    @Autowired
    public PostRepository postRepository;

    Logger logger = Logger.getLogger(HomeController.class);


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "about";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }



//    @GetMapping(value = "/users/add")
//    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
//        userRepository.save(new User(name, email));
//        return "new user added";
//    }
//
//
//    @GetMapping(path = "/users/all")
//    public @ResponseBody Iterable<User> getAllUsers() {
//        return userRepository.findAll();
//    }


}
