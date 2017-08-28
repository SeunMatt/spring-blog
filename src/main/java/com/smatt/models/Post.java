package com.smatt.models;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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

    @Column(columnDefinition = "longtext")
    private String post;

    private boolean featured;

    @Column(columnDefinition = "boolean")
    private boolean published;

    @OneToOne(targetEntity = User.class, cascade = {CascadeType.REMOVE})
    public User author;

//    @OneToOne
    @Transient
    public Section section;

    @OneToOne
    public Category category;

    @Access(AccessType.PROPERTY)
    @ManyToMany
    @JoinTable(name = "posts_tags", joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    public Set<Tag> tags = new HashSet<>();

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


    public Set<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
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
                "\ntags \n\t" + getTags();
    }

    public boolean validate() {
       return !StringUtils.isEmpty(post) && !StringUtils.isEmpty(title);
    }

}
