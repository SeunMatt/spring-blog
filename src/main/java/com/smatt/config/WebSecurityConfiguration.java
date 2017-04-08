package com.smatt.config;

import com.smatt.dao.UserRepository;
import com.smatt.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by smatt on 06/04/2017.
 * This class configures how Spring Framework will be getting the user details from the database
 */

//@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;
    
    
  

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
    	
    	System.out.println("WebSecurityConfiguration called");
    	
        auth.userDetailsService((String username) -> {
            User user  = userRepository.findByUsername(username);
            System.out.println("userdetails called | username = " + username + " user.getUsername " + user.getUsername() + " user.getPassword " + 
            user.getPassword());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
			        true, true, true, true,
			        AuthorityUtils.createAuthorityList("USER"));
        }); //passwordEncoder(new BCryptPasswordEncoder())
     }
    
    
    



}
