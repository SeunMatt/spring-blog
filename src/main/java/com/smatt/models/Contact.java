package com.smatt.models;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by smatt on 16/07/2017.
 */
public class Contact {

    private String name, email, message;

    public Contact(){ }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValidDetails() {
        return !StringUtils.isEmpty(this.email)
                && !StringUtils.isEmpty(this.name)
                && !StringUtils.isEmpty(this.message);
    }

    @Override
    public String toString() {
        return "Name: " + this.name +
                "\nEmail: " + this.email
                + "\nMessage: " + this.message;
    }
}
