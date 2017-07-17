package com.smatt.service;

import com.smatt.models.Contact;
import com.smatt.models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * Created by smatt on 16/07/2017.
 */
@Service
public class ContactService {

    private Configuration cfg;
    private JavaMailSender javaMailSender;
    private Environment env;

    @Autowired
    public ContactService(@Qualifier("freeMarkerConfiguration") Configuration cfg, JavaMailSender javaMailSender, Environment env) {
            this.cfg = cfg;
            this.javaMailSender = javaMailSender;
            this.env = env;
    }

    @Async
    public void sendEmail(Contact contact) throws Exception {

        String templateDir = "email/contact.ftl";
        String subject = "Spring-Blog: You have a message from Contact Form";

        //process mail template
        StringWriter stringWriter = new StringWriter();
        Template temp = cfg.getTemplate(templateDir);
        Map<String, Object> map = new HashMap<>();
        map.put("contact", contact);
        map.put("year", LocalDate.now().getYear() + "");
        temp.process(map, stringWriter);

        //send the message
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(env.getProperty("admin-contact"));
            mimeMessageHelper.setFrom(contact.getEmail(), contact.getName());
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText( stringWriter.getBuffer().toString(), true);
        });
    }

}
