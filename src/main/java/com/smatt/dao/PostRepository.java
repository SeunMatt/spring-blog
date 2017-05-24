package com.smatt.dao;

import com.smatt.models.Category;
import com.smatt.models.Post;
import com.smatt.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by smatt on 22/03/2017.
 */
public interface PostRepository extends CrudRepository<Post, String> {

   List<Post> findByAuthor(User author);

   //this will get the announcements
   List<Post> findByCategoryOrderByCreatedAtDesc(String category);

   List<Post> findByCategoryOrderByCreatedAtDesc(Category category, Pageable pageable);

   //this will get featured posts
   List<Post> findByFeaturedEqualsOrderByCreatedAtDesc(boolean featured, Pageable pageable);

   //this method will get all posts, sort
   @Query("select p from Post p")
   List<Post> findAllPosts(Pageable pageable);
}
