package com.smatt.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smatt.config.Constants;
import com.smatt.dao.UserRepository;
import com.smatt.models.User;
import com.smatt.models.UserRegistration;
import com.smatt.utils.URLHelper;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by smatt on 06/07/2017.
 */

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private Logger logger = Logger.getLogger(AuthenticationService.class);
    private SendConfirmLink sendConfirmLink;
    private TokenGenerator tokenGenerator;
    AuthenticationManager authManager;


    @Autowired
    public AuthenticationService(UserRepository userRepository, SendConfirmLink sendConfirmLink,
                                 TokenGenerator tokenGenerator, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
        this.sendConfirmLink = sendConfirmLink;
        this.authManager = authenticationManager;
    }


    public String doRegister(UserRegistration userRegistration, RedirectAttributes attr, HttpServletRequest req,
                             String recaptchaResp) {

//        logger.info("reg info = " + userRegistration.toString());
//        logger.info("recaptcha response = " + recaptchaResp);

        //verify recaptch
        if(!verifyReCaptcha(recaptchaResp)) {
            attr.addFlashAttribute("error", "reCaptcha Validation Failed! <br> click the I'm not a robot box");
            attr.addFlashAttribute("formdata", userRegistration);
            return "redirect:/register";
        }

        //validation
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

        if(userRepository.findByUsername(userRegistration.getUsername()) != null) {
            attr.addFlashAttribute("error", "Username not available, pick another one please");
            attr.addFlashAttribute("formdata", userRegistration);
            return "redirect:/register";
        }

        if(userRepository.findByEmail(userRegistration.getEmail()) != null) {
            attr.addFlashAttribute("error", "Email not available, use another one please");
            attr.addFlashAttribute("formdata", userRegistration);
            return "redirect:/register";
        }


        //create the user object
        User user = userRegistration.createUserObject(tokenGenerator);

        userRepository.save(user);

        try {
            sendConfirmLink.sendConfirmEmail(user, URLHelper.getBaseUrl(req));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Unable to Send Email to " + user.getEmail() + "\n Error: " + e.getMessage());

        }

        attr.addFlashAttribute("success", "Registration Successful! A confirmation link has been sent to your email. " +
                "Visit your email to confirm your account and then login with your credentials <br>" +
                "<a href='/resend/email/verify'>Resend Confirmation Link</a>");

        return "redirect:/login";

    }

    public String doLogin(String email, String password, RedirectAttributes redirectAttributes, HttpSession session) {

        User user;

        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password) ||
                (user = userRepository.findByEmail(email)) == null) {
            redirectAttributes.addFlashAttribute("error", "Email/Password is Empty");
            return "redirect:/login";
        }

        if(!user.isConfirmEmail()) {
            redirectAttributes.addFlashAttribute("error", "You have not confirm your email address yet. " +
                    "<br>Go to your inbox and use the link sent to you <br> OR <br>" +
                    "<a href='/resend/email'>Resend Confirmation Link</a>");
            return "redirect:/login";
        }


        if(doAuth(email, password, session)) {
            //used to display username on template
            String intentURI = (String) session.getAttribute(Constants.INTENDED_URI);
            if(!StringUtils.isEmpty(intentURI)) {
                //session expired login
                session.removeAttribute(Constants.INTENDED_URI);
                return "redirect:"+intentURI;
            } else {
                //fresh login
                return "redirect:/eyin";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Username/Password Do Not Match");
            return "redirect:/login";
        }
    }

    public String resetPassword(HttpSession session, String token, UserRegistration data, RedirectAttributes attr ) {
        User user = (User) session.getAttribute("USER_OBJECT");
        if(!data.passwordMatch()) {
            attr.addFlashAttribute("error", "Passwords Do Not Match");
            return "redirect:/account/reset/"+token;
        }
        user.setPassword(new BCryptPasswordEncoder().encode(data.getPassword()));
        user.setToken(null);
        userRepository.save(user);
        if(doAuth(user.getEmail(), data.getPassword(), session)) {
            return "redirect:/eyin";
        } else {
            attr.addFlashAttribute("status", "Password Reset Successfully. Kindly login with your new password here");
            return "redirect:/login";
        }
    }

    public String verifyEmail(String token, RedirectAttributes attr) {
        User user;

        if(StringUtils.isEmpty(token) || (user = userRepository.findByToken(token)) == null ||
                !StringUtils.equals(user.getToken(), token)) {
            attr.addFlashAttribute("error", "Invalid Link! <br> " + "<a href='/resend/email/verify'>Resend Confirmation Link</a>");
            return "redirect:/login";
        }

        user.setConfirmEmail(true);
        user.setToken(null);
        userRepository.save(user);

        attr.addFlashAttribute("success", "Email Verified Successfully. Login with your credentials");
        return "redirect:/login";
    }

    private boolean verifyReCaptcha(String response) {
        if(StringUtils.isEmpty(response))
            return false;

        OkHttpClient client = new OkHttpClient();
        okhttp3.RequestBody body = new FormBody.Builder()
                .add("secret", "6LcpmiAUAAAAAFEggh_5HTBuSLXx3UuSz4mXQ6k_")
                .add("response", response)
                .build();
        Request request = new Request.Builder()
                .url("https://www.google.com/recaptcha/api/siteverify")
                .post(body)
                .build();
        try {
            Response serverResp = client.newCall(request).execute();
            String jsonResp = serverResp.body().string();
//            logger.info("raw reCaptcha response == " + jsonResp);
            JsonNode jsonRespObj = new ObjectMapper().readTree(jsonResp);
            return jsonRespObj.get("success").booleanValue();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean doAuth(String email, String password, HttpSession session) {

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
                session.setAttribute(Constants.LOGGED_IN_USER, userRepository.findByUsername(MySecurityService.findLoggedInUsername()));
                session.setAttribute("constant", new Constants());
//                logger.info("User " + MySecurityService.findLoggedInUsername() + " just logged in!");
                return true;
            }

            return false;

        }catch(BadCredentialsException e) {
            logger.info("bad credentials exception");
            e.printStackTrace();
            return false;
        }catch(Exception ex) {
            logger.info("Other exceptions occurred in doAuth " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }

    }

}
