package com.smatt.service;

import com.smatt.config.BlogProperties;
import com.smatt.lib.JMailChimp;
import io.vavr.control.Try;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by smatt on 29/08/2017.
 */
@Service
public class NewsletterService {

    private BlogProperties blogProperties;
    private Logger logger = Logger.getLogger(NewsletterService.class);

    @Autowired
    public NewsletterService(BlogProperties mP) {
        this.blogProperties = mP;
    }


    @Async
    public void subscribe(String email) {

        JMailChimp jMailChimp = new JMailChimp(blogProperties.getMailChimpApiKey(), blogProperties.getMailChimpApiRoot());

        String result = Try.of(() -> jMailChimp.addSubscriber(email, blogProperties.getMailChimpListId(), JMailChimp.STATUS.PENDING))
             .recover(e -> "Error Adding Subscriber " + email + " to mailing list. \n" + e.getLocalizedMessage())
             .get();

        logger.info("NewsletterService.subscribe: " + result);
    }

}
