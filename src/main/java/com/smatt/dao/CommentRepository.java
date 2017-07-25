package com.smatt.dao;

import com.smatt.models.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by smatt on 21/07/2017.
 */
public interface CommentRepository extends CrudRepository<Comment, String> {

    @Query("SELECT c FROM Comment c WHERE c.postId = ?1 AND c.parentCommentId = null")
    List<Comment> findDirectComments(String postId);

}
