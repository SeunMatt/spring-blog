package com.smatt.models;

import com.smatt.config.Roles;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by smatt on 22/03/2017.
 */
@Entity
@Table(name = "users")
public class User {

    @Transient
    Logger logger = Logger.getLogger(User.class);

    @Id
    private String id;
    private String name;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;

    private String password;
    private String profilePic;
    private String bio;

    private String role;
    private Date createdAt, updatedAt;
    private boolean banned;
    private String token;
    private boolean confirmEmail;

    //i.e One user has many posts
    //this will create another table users_posts
    //I don't want that extra table so am gonna disable it
    //and fetch all posts for a user with Query in service/controllers
//    @OneToMany(targetEntity = Post.class)
//    public List<Post> posts;


    public User() { }

    public User(String username, String password) {
        this.username = username;
        this.password =  password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(boolean confirmEmail) {
        this.confirmEmail = confirmEmail;
    }


    @PrePersist
    public void preSave() {
        if(StringUtils.isEmpty(role)) {
            role = Roles.WRITER.toString();
        }
        if(createdAt == null) {
            createdAt = new Date();
        }
        if(StringUtils.isEmpty(id)) {
            id = RandomStringUtils.randomAlphanumeric(10);
        }
        //always a new one
        updatedAt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }

    @Override
    public String toString() {
        return "\nid " + id +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nBio: " + bio +
                "\nusername: " + username +
                "\nbanned: " + banned +
                "\nroleId " + role +
                "\nprofilePic " + profilePic;
    }


}
