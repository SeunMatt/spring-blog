package com.smatt.service;

import com.smatt.models.User;
import com.smatt.utils.URLHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.*;

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
    public void sendEmail(User user, String baseUrl) throws Exception {

        //process mail template
        StringWriter stringWriter = new StringWriter();
        Template temp = cfg.getTemplate("email/confirm_email.ftl");
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("link",  baseUrl + "/account/t/" + user.getToken());
        map.put("year", LocalDate.now().getYear() + "");
        temp.process(map, stringWriter);

        //send the message
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setFrom(env.getProperty("from-email"), "Spring Blog");
            mimeMessageHelper.setSubject("Spring-Blog: Confirm Email Address");
            mimeMessageHelper.setText( stringWriter.getBuffer().toString(), true);
        });

    }

}
