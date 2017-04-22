package com.smatt.config;

import com.smatt.dao.UserRepository;
import com.smatt.exceptions.MyAccessDeniedHandler;
import com.smatt.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Created by smatt on 06/04/2017.
 */
@EnableWebSecurity
@Configuration
public class AuthConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserRepository userRepository;

    Logger logger = Logger.getLogger(AuthConfig.class);

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        logger.info("Access denied bean is being loaded");
        return new MyAccessDeniedHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//
//        http.regexMatcher("?/eyin*").authorizeRequests().anyRequest().authenticated();

        http      .authorizeRequests() //any request hitting this server should be check for auth with rules stated below
                    .regexMatchers("/eyin.*", "/eyin/files/.*") //if the path matches "/eyin**"
                    .authenticated()

//               uncomment below section if you want spring to handle the logic for ya
//                .and() //this is like we are done specifying rules for the antMatchers above. We wanna make another rule here
//                .formLogin() //we are using formlogin method and not httpBasic or any other method
//                    .loginPage("/login") //my custom login url that returns my login page is here
//                    .loginProcessingUrl("/login") //my custom url for handling the post request from login

                .and()
                    .rememberMe()
                .and()
                    .exceptionHandling()
                        .accessDeniedHandler(accessDeniedHandler())
                        .accessDeniedPage("/admin/login")
                        .authenticationEntryPoint((request, response, authException) -> {
                            //this is called for access denied or forbidden event - 403
                            //we just redirect the user to our login page ASAP
    //                        logger.info("Authentication Entry Point is invoked!");
                            response.sendRedirect("/login");
                        });
    }

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth, UserRepository userRepository) throws Exception {

        auth.userDetailsService((String email) -> {
            User user  = userRepository.findByEmail(email);

//            logger.info("userdetails called | username = " + username + " user.getUsername " + user.getUsername() + " user.getPassword " +
//                    user.getPassword());

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
        }).passwordEncoder(new BCryptPasswordEncoder());
    }


}
