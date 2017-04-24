package com.smatt.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by smatt on 22/03/2017.
 */
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String profilePic;
    private String bio;
    private int roleId;
    private Date createdAt, updatedAt;
    private boolean banned;



    public User() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    public User(String username, String password) {
        this.username = username;
        this.password =  password;
        createdAt = new Date();
        updatedAt = new Date();
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }


    public boolean isCredentialsValid() {
        return getEmail().isEmpty() && getPassword().isEmpty();
    }


    @Override
    public String toString() {
        return "\nid " + id +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nBio: " + bio +
                "\nusername: " + username +
                "\nbanned: " + banned +
                "\nroleId " + roleId +
                "\nprofilePic " + profilePic;
    }


}
