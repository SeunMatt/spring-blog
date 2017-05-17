package com.smatt.models;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by smatt on 22/04/2017.
 */
@Entity
@Table(name = "categories")
public class Category {

    @Id
    private String id;
    private String category;
    private int articleCount;
    private Date createdAt;
    private Date updatedAt;

    public Category() {
        id = RandomStringUtils.randomAlphanumeric(10);
    }

    public Category(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
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


    @PrePersist
    public void preSave() {
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
        return "category = " + category +
                "\ncreatedAt = " + createdAt +
                "\nupdatedAt = " + updatedAt +
                "\nid = " + id;
    }
}
