package com.smatt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.smatt.models.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.smatt.dao.UserRepository;

/**
 * This class will handle registration of new user
 */

//@Configuration
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public AuthenticationManager authManager;
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserService(){
		System.out.println("AuthManager null in user service = " + (authManager  == null));
		System.out.println("userRepository null in userservice = " + (userRepository  == null));
		
//		System.out.println("all userRepo " + userRepository.findAll().toString());
		
	}
	
	
	
	public void save(User user) {
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		//if you want to set roles do it here
		//probably enter the user_id as user_id on the roles table
		userRepository.save(user);
	}
	
	
	public boolean login(String username, String password) {
    	
//    	UserDetails userDetails = UserDetails
    	 UsernamePasswordAuthenticationToken authToken = 
    			 new UsernamePasswordAuthenticationToken(username, password);
    	 System.out.println("authToken principal == " + authToken.getPrincipal() + 
    			 "credentials = " + authToken.getCredentials());
//    	 System.out.println("details == " + authToken.getDetails().toString());
    	 System.out.println("auth name = " + authToken.getName());
    	 
    	 if(authManager == null) {
    		 System.out.println("null authManager");
    		 return false;
    	 }
    	 
    	 
    	 authToken = (UsernamePasswordAuthenticationToken) authManager.authenticate(authToken);
    	 
    	 return authToken.isAuthenticated();
    	    	  
    	
    }
		

}
