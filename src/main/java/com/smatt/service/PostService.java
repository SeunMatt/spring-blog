package com.smatt.service;

import com.smatt.dao.PostRepository;
import com.smatt.models.Post;
import com.sun.xml.internal.ws.util.CompletedFuture;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * Created by smatt on 12/06/2017.
 *
 */
@Service
public class PostService {

    PostRepository postRepository;
    Logger logger = Logger.getLogger(PostService.class);

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /*
    * This method will be used to update post object Async
    * especially increasing the views count when a post is read
    * */
    @Async
    public CompletableFuture<Post> updatePostAsyn(Post post) {
        return CompletableFuture.completedFuture(postRepository.save(post));
    }


}
