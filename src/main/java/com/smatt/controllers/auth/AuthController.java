package com.smatt.controllers.auth;

import com.smatt.dao.UserRepository;
import com.smatt.models.User;
import com.smatt.models.UserRegistration;
import com.smatt.service.MySecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by smatt on 10/04/2017.
 */
@Controller
public class AuthController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession session;

    Logger logger  = Logger.getLogger(AuthController.class);

    @GetMapping(value = "/login")
    public String login(ModelMap model) {
        return "admin/login";
    }


    @PostMapping(value = "/login")
    public String doLogin(User user, RedirectAttributes redirectAttributes) {

//        logger.info("username = " + user.getEmail() + " password == " + user.getPassword());

        if(user.isCredentialsValid()) {
            redirectAttributes.addFlashAttribute("error", "Email/Password is Empty");
            return "redirect:/login";
        }

        if(doAuth(user.getEmail(), user.getPassword())) {
            //used to display username on template
            session.setAttribute("username", MySecurityService.findLoggedInUsername());
            return "redirect:/eyin";
        } else {
            redirectAttributes.addFlashAttribute("error", "Username/Password Do Not Match");
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public String logout(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
        doLogout(req, res);
        return "redirect:/login";
    }

    @GetMapping( value = "/register")
    public String register(ModelMap model) {
        return "admin/register";
    }

    @PostMapping(value = "/register")
    public String doRegister(UserRegistration userRegistration, RedirectAttributes attr) {

//        logger.info("reg info = " + userRegistration.toString());

        if(!userRegistration.isValidDetails()) {
           attr.addFlashAttribute("error", "One or More Field is Missing");
            attr.addFlashAttribute("formdata", userRegistration);
            return "redirect:/register";
        }

        if(!userRegistration.passwordMatch()) {
            attr.addFlashAttribute("error", "Passwords Do Not Match");
            attr.addFlashAttribute("formdata", userRegistration);
            return "redirect:/register";
        }
        User user = userRegistration.getUserObject();
        userRepository.save(user);

        //log the user in automatically
        if(doAuth(user.getEmail(), userRegistration.getPassword())) {
            //used to display username on template
            attr.addFlashAttribute("success", "Welcome " + MySecurityService.findLoggedInUsername() + ", click on the profile picture to change it. You can add your status or bio in the settings tab");
            return "redirect:/eyin/users/profile";
        } else {
            logger.info("auto auth failed!");
            attr.addFlashAttribute("success", "Registration Successful! Please login with your credentials");
            return "redirect:/login";
        }

    }


    public boolean doAuth(String email, String password) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(email, password);

        if(authManager == null) {
//            logger.info("authManager is null");
            return false;
        }

        try {

            authToken = (UsernamePasswordAuthenticationToken) authManager.authenticate(authToken);
            if(authToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authToken);
                session.setAttribute("user", userRepository.findByUsername(MySecurityService.findLoggedInUsername()));
//                logger.info("User " + MySecurityService.findLoggedInUsername() + " just logged in!");
                return true;
            }

            return false;

        }catch(BadCredentialsException e) {
//            logger.info("bad credentials exception");
//            e.printStackTrace();
            return false;
        }catch(Exception ex) {
            logger.info("Other exceptions occurred in doAuth " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }

    }





    public void doLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, auth );
        new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY).logout(request, response, auth);
    }

}
