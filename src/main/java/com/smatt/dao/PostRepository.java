package com.smatt.dao;

import com.smatt.models.Post;
import com.smatt.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Transient;
import java.util.List;

/**
 * Created by smatt on 22/03/2017.
 */
public interface PostRepository extends CrudRepository<Post, String> {
//   Post findByPost(String post);

   List<Post> findByAuthor(User author);

}
