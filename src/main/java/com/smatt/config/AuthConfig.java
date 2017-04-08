package com.smatt.config;

import com.smatt.dao.UserRepository;
import com.smatt.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by smatt on 06/04/2017.
 */
@EnableWebSecurity
@Configuration
public class AuthConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserRepository userRepository;

    Logger logger = Logger.getLogger(AuthConfig.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http .authorizeRequests() //any request hitting this server should be check for auth with rules stated below
                .antMatchers("/eyin**") //if the path matches "/eyin**"
                     .authenticated() //ensure the user is authenticated.
                    //any other rules for the above antMatchers can follow here
                .and() //this is like we are done specifying rules for the antMatchers above. We wanna make another rule here
                .formLogin() //we are using formlogin method and not httpBasic or any other method
                    .loginPage("/login") //my custom login url that returns my login page is here
                    .loginProcessingUrl("/login") //my custom url for handling the post request from login
                ; //terminate

    }

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth, UserRepository userRepository) throws Exception {
        logger.info("configGlobal Called");
        auth.userDetailsService((String username) -> {
            User user  = userRepository.findByUsername(username);
            logger.info("userdetails called | username = " + username + " user.getUsername " + user.getUsername() + " user.getPassword " +
                    user.getPassword());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
        }); //passwordEncoder(new BCryptPasswordEncoder())
    }


}
