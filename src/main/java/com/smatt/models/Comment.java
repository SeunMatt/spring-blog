package com.smatt.models;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by smatt on 21/07/2017.
 */
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    private String id;

    private String name;
    private String email;
    private String comment;
    private String postId;
    private String childCommentId;
    private String parentCommentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean notify;

    public Comment() {}

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getChildCommentId() {
        return childCommentId;
    }

    public void setChildCommentId(String childCommentId) {
        this.childCommentId = childCommentId;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    @Override
    public String toString() {
        return "Comment: " + this.getComment()
                + "\nName: " + this.getName()
                + " | Email: " + this.getEmail()
                + " | PostId: " + this.getPostId()
                + " | Notify: " + this.isNotify();
    }

    @PrePersist
    public void preSave() {
        if(createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if(StringUtils.isEmpty(id)) {
            id = RandomStringUtils.randomAlphanumeric(10);
        }
        //always a new one
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public boolean isValid() {
        return !StringUtils.isEmpty(this.getComment())
                && !StringUtils.isEmpty(this.getEmail())
                && !StringUtils.isEmpty(this.getName());
    }


}
