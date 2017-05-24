package com.smatt.models;

import com.smatt.config.Roles;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GeneratorType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/**
 * Created by smatt on 22/03/2017.
 */

@Entity
@Table(name = "posts")
public class Post
{

    @Id
    private String id;
    private String title;
    private String coverPic;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int likes;
    private int views;
    private String post;
    private boolean featured;

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

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
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
        return "id = " + id +
                "\npost = " + getPost() +
                "\ntitle = " + getTitle() +
                "\ncreatedAt = " + getCreatedAt() +
                "\nupdatedAt = " + getUpdatedAt() +
                "\nlikes = " + getLikes() +
                "\nviews = " + getViews() +
                "\npublished = " + isPublished() +
                "\nfeatured = " + isFeatured() +
                "\ncoverPic = " + getCoverPic() +
                "\nauthor:\n\t" + (!Objects.isNull(getAuthor()) ? getAuthor().getUsername() : "null") +
                "\ncategory \n\t" + getCategory() +
                "\nsection \n\t" + getSection();
    }

    public boolean validate() {
       return !StringUtils.isEmpty(post) && !StringUtils.isEmpty(title);
    }

}
