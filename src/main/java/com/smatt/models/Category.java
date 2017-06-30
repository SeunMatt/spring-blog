package com.smatt.models;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by smatt on 22/04/2017.
 */
@Entity
@Table(name = "categories")
public class Category {

    @Id
    private String id;
    @Column(unique = true)
    private String category;
    private int articleCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Transient
	Logger logger = Logger.getLogger(Category.class);

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

    @Override
    public String toString() {
        return "Category = " + getCategory();
    }

	@Override
	public boolean equals(Object obj) {
    	if(obj instanceof Category )
			return StringUtils.equals(this.getCategory(), ((Category)obj).getCategory());
    	return false;
	}

}
