package com.smatt.models;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by smatt on 22/04/2017.
 */
@Entity
@Table(name = "sections")
public class Section {

    @Id
    private String id;
    private String section;
    private int articleCount;
    private Date createdAt;
    private Date updatedAt;


    public Section() {
        id = RandomStringUtils.randomAlphanumeric(10);
    }

    public Section(String section) {
        this.section = section;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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

}
