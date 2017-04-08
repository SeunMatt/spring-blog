package com.smatt.dao;

import com.smatt.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by smatt on 22/03/2017.
 */
public interface PostRepository extends MongoRepository<Post, String> {
    public Post findByPost(String post);
}
