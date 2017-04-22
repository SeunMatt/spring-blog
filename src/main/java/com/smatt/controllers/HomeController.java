package com.smatt.controllers;

import com.smatt.dao.PostRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
