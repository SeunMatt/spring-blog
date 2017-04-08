package com.smatt.controllers;

import com.smatt.dao.PostRepository;
import com.smatt.models.User;
import com.smatt.service.UserService;

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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @GetMapping(value = "/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping( value = "/register")
    public String register() {
        return "admin/register";
    }

    @PostMapping(value = "/login")
    public String doLogin(User user) {
    	
        logger.info("username = " + user.getUsername() + " password == " + user.getPassword());

        if(user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            logger.info("Username/Pwd is empty");
            return "redirect:/login?error";
        }
        
	     if(doAuth(user.getUsername(), user.getPassword())) {
	   		logger.info("Authenticated username = " + findLoggedInUsername());
			 return "redirect:/eyin";
	   	 } else {
	   		logger.info(String.format("User %s Not Authenticated", user.getUsername()));
	   		 return "redirect:/login?error";
	   	 }
	   	 
	      
    }
    
    
    public boolean doAuth(String username, String password) {
    	
	     UsernamePasswordAuthenticationToken authToken = 
	   			 new UsernamePasswordAuthenticationToken(username, password);

	   	 logger.info("authToken principal == " + authToken.getPrincipal() +
	   			 " credentials = " + authToken.getCredentials());

	   	 if(authManager == null) {
	   	     logger.info("authManager is null");
	   		 return false;
	   	 }
	   	 
	   	 try {
	   		
	   		 authToken = (UsernamePasswordAuthenticationToken) authManager.authenticate(authToken);
	   		 if(authToken.isAuthenticated()) {
		   		  SecurityContextHolder.getContext().setAuthentication(authToken);
		   		  logger.info("User " + findLoggedInUsername() + " just logged in!");
		   		  return true;
	   		 }
	   		 
	   		 return false;
	   		
	   	 }catch(BadCredentialsException e) {
	   	     logger.info("BadCredentialsException for username " + username + " in doAuth");
	   		 return false;
	   	 }catch(Exception ex) {
	   		logger.info("Other exceptions occurred in doAuth " + ex.getMessage());
	   		 return false;
	   	 }

    }

    
    
    public String findLoggedInUsername() {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.getPrincipal() != null && auth.getPrincipal() instanceof UserDetails) {
        	return ((UserDetails) auth.getPrincipal()).getUsername();
        }
         logger.info("findLoggedInUsername is null");
         return null;
    }


    public void doLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, auth );
       new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY).logout(request, response, auth);
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
