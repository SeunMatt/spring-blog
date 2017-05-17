package com.smatt.models;

import com.smatt.config.Roles;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GeneratorType;

import java.util.Date;
import javax.persistence.*;

/**
 * Created by smatt on 22/03/2017.
 */

@Entity
@Table(name = "posts")
public class Post
{

    @Id
    public String id;
    public String title;
    public String coverPic;
    public Date createdAt;
    public Date updatedAt;
    public int likes;
    public int views;
    public String post;

    @Column(columnDefinition = "boolean")
    public boolean published;

    @OneToOne(targetEntity = User.class, cascade = {CascadeType.REMOVE})
    public User author;

    @OneToOne
    public Section section;

    @OneToOne
    public Category category;




    public Post() { }

    public Post(int views, String post) {
        this.views = views;
        this.post = post;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
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
        return "id = " + id +
                "\npost = " + post +
                "\ntitle = " + title +
                "\ncreatedAt = " + createdAt +
                "\nupdatedAt = " + getUpdatedAt() +
                "\nlikes = " + likes +
                "\npublished = " + published +
                "\ncoverPic = " + coverPic +
                "\nauthor:\n\t" + getAuthor() +
                "\ncategory \n\t" + getCategory() +
                "\nsection \n\t" + getSection();
    }

    public boolean validate() {
       return !StringUtils.isEmpty(post) && !StringUtils.isEmpty(title);
    }

}
