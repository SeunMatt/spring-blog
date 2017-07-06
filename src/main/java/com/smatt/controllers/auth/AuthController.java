package com.smatt.controllers.auth;

import com.smatt.config.Constants;
import com.smatt.dao.UserRepository;
import com.smatt.models.User;
import com.smatt.models.UserRegistration;
import com.smatt.service.MySecurityService;
import com.smatt.service.AuthenticationService;
import com.smatt.service.SendConfirmLink;
import com.smatt.service.TokenGenerator;
import com.smatt.utils.URLHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by smatt on 10/04/2017.
 */
@Controller
public class AuthController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    SendConfirmLink sendConfirmLink;

    @Autowired
    TokenGenerator tokenGenerator;

    Logger logger  = Logger.getLogger(AuthController.class);
    AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = {"/login"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String login(ModelMap model, HttpServletRequest req,
                        @RequestParam(value = "email", required = false) String email, @RequestParam(value = "password", required = false) String password,
                        RedirectAttributes attr, HttpSession session) {

        if(StringUtils.equals(req.getMethod(), RequestMethod.GET.toString())) {
            return "admin/auth/login";
        }
        else if(StringUtils.equals(req.getMethod(), RequestMethod.POST.toString())) {
            return authenticationService.doLogin(email, password, attr, session);
        }
        return invalidRequestResponse(attr);
    }


    @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public String logout(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
        MySecurityService.doLogout(req, res);
        return "redirect:/login";
    }

    @RequestMapping( value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    public String register(ModelMap model, HttpServletRequest req, RedirectAttributes attr,
                           @RequestParam(value = "g-recaptcha-response", required = false) String recaptchaResp, UserRegistration userRegistration) {
        if(StringUtils.equals(req.getMethod(), RequestMethod.GET.toString())) {
            return "admin/auth/register";
        }
        if(StringUtils.equals(req.getMethod(), RequestMethod.POST.toString())) {
            return authenticationService.doRegister(userRegistration, attr, req, recaptchaResp);
        }

        return invalidRequestResponse(attr);
    }

    @GetMapping("/account/verify/{token}")
    public String verifyEmail(@PathVariable("token") String token, RedirectAttributes attr) {
        return authenticationService.verifyEmail(token, attr);
    }


    @RequestMapping(value = {"/account/reset/{token}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String resetPassword(@PathVariable(value = "token", required = false) String token, UserRegistration data, RedirectAttributes attr,
                                HttpServletRequest req, HttpSession session, ModelMap model) {

        User user;

        if(StringUtils.equals(req.getMethod(), RequestMethod.GET.toString())) {

            if(StringUtils.isEmpty(token) || (user  = userRepository.findByToken(token)) == null ) {
                attr.addFlashAttribute("error", "Invalid Account Reset Token. Enter your email to get a new token");
                return "redirect:/resend/email/auth";
            }
            session.setAttribute("USER_OBJECT", user);
            model.addAttribute("token", token);
            return "admin/auth/password_reset";
        }

        if(StringUtils.equals(req.getMethod(), RequestMethod.POST.toString())) {
            return authenticationService.resetPassword(session, token, data, attr);
        }

        return invalidRequestResponse(attr);
    }



    @RequestMapping(value = "/resend/email/{target}", method = {RequestMethod.GET, RequestMethod.POST})
    public String resendEmail(@PathVariable("target") String target, @RequestParam(value = "email", required = false) String email,
                              RedirectAttributes attr, HttpServletRequest req, ModelMap model) {

        if (StringUtils.equals(req.getMethod(), RequestMethod.GET.toString())) {
            model.put("path", target);
            return "admin/auth/email_reset";
        }
        else if (StringUtils.equals(req.getMethod(), RequestMethod.POST.toString())) {
            User user;

            if (StringUtils.isEmpty("email") || (user = userRepository.findByEmail(email)) == null) {
                attr.addFlashAttribute("error", "You have to supply your email");
                return "redirect:/resend/email";
            }

            try {

                if (StringUtils.isEmpty(user.getToken())) {
                    user.setToken(tokenGenerator.getToken(Constants.TOKEN_LENGTH));
                    user = userRepository.save(user);
                }

                if(target.equalsIgnoreCase("verify")) {
                    sendConfirmLink.sendConfirmEmail(user, URLHelper.getBaseUrl(req));
                    attr.addFlashAttribute("success", "Confirmation Link Sent Successfully. <br> " +
                            "<a href='/resend/email/verify'>Resend Confirmation Link</a>");
                } else if(target.equals("auth")) {
                    sendConfirmLink.sendPasswordResetEmail(user, URLHelper.getBaseUrl(req));
                    attr.addFlashAttribute("success", "Password Reset Link Sent Successfully. <br> " +
                            "<a href='/resend/email/auth'>Resend Link</a>");
                }

                return "redirect:/login";

            } catch (Exception e) {
                e.printStackTrace();
                attr.addFlashAttribute("error", "Server Error, please try again");
                return "redirect:/resend/email";
            }
        }

        attr.addFlashAttribute("error", "Invalid Request");
        return "redirect:/resend/email";
    }



    private String invalidRequestResponse(RedirectAttributes attr) {
        attr.addFlashAttribute("error", "Invalid Request Method");
        return "redirect:/";
    }
}
