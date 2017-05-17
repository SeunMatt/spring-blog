package com.smatt;

import com.smatt.dao.PostRepository;
import com.smatt.dao.UserRepository;
import com.smatt.models.Post;
import com.smatt.models.User;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBlogApplicationTests {

    Logger logger = Logger.getLogger(SpringBlogApplicationTests.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Test
	public void contextLoads() { }

//	@Test
//    public void whenCreatingPost_thenNoError() {
//
//        User user = new User("smatt", "smatt");
//
//        User savedU = userRepository.save(user);
//
//        Post post = new Post(0, "Post Test");
//        post.setAuthor(savedU);
//        Post savedP = postRepository.save(post);
//        logger.info("saved post == " + savedP.toString());
//
//    }


}
