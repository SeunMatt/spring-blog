package com.smatt.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Table;
import java.util.Date;

/**
 * Created by smatt on 22/03/2017.
 */
@Document(collection = "posts")
public class Post
{

    @Id
    public String id;

    public String post = "";
    public String author = "";
    public String title = "";
    public String cover_pic = "";
    public int category_id;
    public int section_id;
    public Date createdAt;
    public Date updatedAt;
    public int likes;

   public Post() {
        createdAt = new Date();
        updatedAt = new Date();
   }

   public Post(String title, String post, String author) {

        this.title = title;
        this.author = author;
        this.post = post;
        createdAt = new Date();
        updatedAt = new Date();
        category_id = 1;
        section_id = 1;
    }

    public Post(String title, String post, String author, int category_id) {

        this.title = title;
        this.author = author;
        this.post = post;
        createdAt = new Date();
        updatedAt = new Date();
        this.category_id = category_id;
        section_id = 1;
    }

    public Post(String title, String post, String author, int category_id, int section_id) {

        this.title = title;
        this.author = author;
        this.post = post;
        createdAt = new Date();
        updatedAt = new Date();
        this.category_id = category_id;
        this.section_id = section_id;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public String toString() {
        return "post = " + post +
                "\ntitle = " + title +
                "\nauthor = " + author +
                "\ncreatedAt = " + createdAt +
                "\nupdatedAt = " + updatedAt +
                "\nlikes = " + likes;
    }

    public boolean validate() {
       return !post.isEmpty() && !title.isEmpty() && !author.isEmpty();
    }

}
