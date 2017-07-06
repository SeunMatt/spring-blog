package com.smatt.models;

import com.smatt.config.Constants;
import com.smatt.service.TokenGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by smatt on 12/04/2017.
 * This POJO will be used to model user input for registration
 */

public class UserRegistration {

    private String name, email, username, password, password_confirmation;

    public UserRegistration() {}


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public boolean passwordMatch() {

        return getPassword() != null && getPassword_confirmation() != null &&
                getPassword().equals(getPassword_confirmation());
    }

    public boolean isValidDetails() {
        return !getUsername().isEmpty() && !getEmail().isEmpty()
                && !getName().isEmpty() && !getPassword().isEmpty()
                && !getPassword_confirmation().isEmpty();
    }

    public String toString() {
        return "Username = " + getUsername() +
                "\nEmail = " + getEmail() +
                "\nName = " + getName() +
                "\nPassword = " + getPassword() +
                "\nPassword Confirm = " + getPassword_confirmation();
    }

    //this method will create the user object that will be saved in the database
    public User  createUserObject(TokenGenerator tokenGenerator) {

        User user = new User();
        user.setUsername(getUsername());
        user.setEmail(getEmail());
        user.setName(getName());
        user.setPassword(new BCryptPasswordEncoder().encode(getPassword()));
        user.setToken(tokenGenerator.getToken(Constants.TOKEN_LENGTH));
        user.setConfirmEmail(false);
        return user;
    }
}
