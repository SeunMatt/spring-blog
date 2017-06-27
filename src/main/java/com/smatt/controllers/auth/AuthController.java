package com.smatt.controllers.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smatt.config.Constants;
import com.smatt.dao.UserRepository;
import com.smatt.models.User;
import com.smatt.models.UserRegistration;
import com.smatt.service.MySecurityService;
import com.smatt.service.SendConfirmLink;
import com.smatt.service.TokenGenerator;
import com.smatt.utils.URLHelper;
import okhttp3.*;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

    @Autowired
    SendConfirmLink sendConfirmLink;

    @Autowired
    TokenGenerator tokenGenerator;

    Logger logger  = Logger.getLogger(AuthController.class);

    @GetMapping(value = "/login")
    public String login(ModelMap model) {
        return "admin/login";
    }

    @PostMapping(value = "/login")
    public String doLogin(@RequestParam("email") String email, @RequestParam("password") String password,
                          RedirectAttributes redirectAttributes) {

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


        if(doAuth(email, password)) {
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

    @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public String logout(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
        MySecurityService.doLogout(req, res);
        return "redirect:/login";
    }

    @GetMapping( value = "/register")
    public String register(ModelMap model) {
        return "admin/register";
    }

    @PostMapping(value = "/register")
    public String doRegister(UserRegistration userRegistration, RedirectAttributes attr, HttpServletRequest req,
                             @RequestParam("g-recaptcha-response") String recaptchaResp) {

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
            logger.info("baseUrl in Auth register: " + URLHelper.getBaseUrl(req));
            sendConfirmLink.sendEmail(user, req);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Unable to Send Email to " + user.getEmail() + "\n Error: " + e.getMessage());

        }

        attr.addFlashAttribute("success", "Registration Successful! A confirmation link has been sent to your email. " +
                "Visit your email to confirm your account and then login with your credentials <br>" +
                "<a href='/resend/email'>Resend Confirmation Link</a>");

        return "redirect:/login";

    }

    @GetMapping("/account/t/{token}")
    public String verifyEmail(@PathVariable("token") String token, RedirectAttributes attr) {

        User user = null;

        if(StringUtils.isEmpty(token) || (user = userRepository.findByToken(token)) == null ||
                !StringUtils.equals(user.getToken(), token)) {
            attr.addFlashAttribute("error", "Invalid Link! <br> " + "<a href='/resend/emil'>Resend Confirmation Link</a>");
            return "redirect:/login";
        }

        user.setConfirmEmail(true);
        user.setToken(null);
        userRepository.save(user);

        attr.addFlashAttribute("success", "Email Verified Successfully. Login with your credentials");
        return "redirect:/login";

    }

    @RequestMapping(value = "/resend/email", method = {RequestMethod.GET, RequestMethod.POST})
    public String resendEmail(@RequestParam(value = "email", required = false) String email,
                              RedirectAttributes attr, HttpServletRequest req) {

        if (StringUtils.equals(req.getMethod(), RequestMethod.GET.toString())) {
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

                sendConfirmLink.sendEmail(user, req);
                attr.addFlashAttribute("success", "Confirmation Link Sent Successfully. <br> " +
                        "<a href='/resend/email'>Resend Confirmation Link</a>");
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



    private boolean verifyReCaptcha(String response) {

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


}
