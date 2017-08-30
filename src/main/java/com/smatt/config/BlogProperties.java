package com.smatt.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by smatt on 29/08/2017.
 */
@Component
@ConfigurationProperties("smattblog")
public class BlogProperties {

    private String mailChimpApiKey = null,
            mailChimpListId = null,
            mailChimpApiRoot = null;

    public String getMailChimpApiKey() {
        if(StringUtils.isEmpty(this.mailChimpApiKey)) {
            throw new RuntimeException("MailChimp API Key is not set. " +
                    "Add the following entries to your properties file: " +
                    "smattblog.mailchimp.mailChimpApiKey=YOUR_MAILCHIMP_API_KEY");
        }
        return mailChimpApiKey;
    }

    public void setMailChimpApiKey(String mailChimpApiKey) {
        this.mailChimpApiKey = mailChimpApiKey;
    }

    public String getMailChimpListId() {
        if(StringUtils.isEmpty(this.mailChimpListId)) {
            throw new RuntimeException("MailChimp List ID is not set. " +
                    "Add the following entries to your properties file: " +
                    "smattblog.mailchimp.mailChimpListId=YOUR_MAILCHIMP_LIST_ID");
        }
        return mailChimpListId;
    }

    public void setMailChimpListId(String mailChimpListId) {
        this.mailChimpListId = mailChimpListId;
    }

    public String getMailChimpApiRoot() {
        if(StringUtils.isEmpty(this.mailChimpListId)) {
            throw new RuntimeException("MailChimp API Root is not set. " +
                    "Add the following entries to your properties file: " +
                    "smattblog.mailchimp.mailChimpApiRoot=YOUR_MAILCHIMP_API_ROOT");
        }
        return mailChimpApiRoot;
    }

    public void setMailChimpApiRoot(String mailChimpApiRoot) {
        this.mailChimpApiRoot = mailChimpApiRoot;
    }
}
