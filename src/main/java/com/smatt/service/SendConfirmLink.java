package com.smatt.service;

import com.smatt.models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by smatt on 28/04/2017.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class SendConfirmLink {

    JavaMailSender javaMailSender;
    Environment env;
    Configuration cfg;
    Logger logger = Logger.getLogger(SendConfirmLink.class);

    @Autowired
    public SendConfirmLink(JavaMailSender javaMailSender, Environment env, Configuration cfg) {
        this.javaMailSender = javaMailSender;
        this.env = env;
        this.cfg = cfg;
    }

    @Async
    public void sendConfirmEmail(User user, String baseUrl) throws Exception {
        sendEmail(user, baseUrl, false);
    }

    @Async
    public void sendPasswordResetEmail(User user, String baseUrl) throws Exception{
        sendEmail(user, baseUrl, true);
    }

    private void sendEmail(User user, String baseUrl, boolean reset) throws Exception {

        String templateDir = (reset) ? "email/password_reset_email.ftl" : "email/confirm_email.ftl";
        String link = (reset) ? baseUrl + "/account/reset/" + user.getToken() : baseUrl + "/account/verify/" + user.getToken();
        String subject = (reset) ? "Spring-Blog: Password Reset" : "Spring-Blog:  Confirm Email Address";

        //process mail template
        StringWriter stringWriter = new StringWriter();
        Template temp = cfg.getTemplate(templateDir);
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("link",  link);
        map.put("year", LocalDate.now().getYear() + "");
        temp.process(map, stringWriter);

        //send the message
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setFrom(env.getProperty("from-email"), "Spring Blog");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText( stringWriter.getBuffer().toString(), true);
        });
    }

}
