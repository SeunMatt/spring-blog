package com.smatt.dao;

import com.smatt.models.Category;
import com.smatt.models.Post;
import com.smatt.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
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

   @Query("select p from Post p where p.published = true and p.category like ?1 order by p.createdAt desc")
   Page<Post> findByCategory(Category category, Pageable pageable);

   @Query("select p from Post p where p.published = true and p.category like ?1 and p.id <> ?2 order by p.createdAt desc")
   List<Post> findRelatedInCategory(Category category, String excludeId);

   //this method will get all posts, sort
   @Query("select p from Post p")
   List<Post> findAllPosts(Pageable pageable);

//   any caller that want to use this can specify the sorting order via the Pageable interface
   @Query("select p from Post p where p.published = true")
   List<Post> findAllPublishedPosts(Pageable p);

//   this is meant to be listing all posts
   @Query("select p from Post p where p.published = true order by p.createdAt desc")
   Page<Post> findAllPublishedPostsPaged(Pageable p);

   @Query("select p from Post p where p.featured = true and p.published = true order by p.createdAt desc")
   List<Post> findFeaturedPosts(Pageable p);

   Page<Post> findByPostContaining(String keyword, Pageable p);

}
