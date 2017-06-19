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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBlogApplicationTests {

    Logger logger = Logger.getLogger(SpringBlogApplicationTests.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

//    @Test
	public void contextLoads() { }

// 	@Test
    public void whenCreatingPost_thenNoError() {

        User user = new User("smatt", "smatt");

        User savedU = userRepository.save(user);

        Post post = new Post(0, "Post Test");
        post.setAuthor(savedU);
        Post savedP = postRepository.save(post);
        logger.info("saved post == " + savedP.toString());

    }

    @Test
    public void givenKeywords_whenSeacrh_thenResults() {

        Page<Post> result = postRepository.findByPostContaining("This", new PageRequest(0,10));
        assertNotNull(result);
        System.out.println("max = " + result.getTotalElements());
    }

}
