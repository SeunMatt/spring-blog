package com.smatt;

import com.smatt.config.BlogProperties;
import com.smatt.dao.PostRepository;
import com.smatt.dao.TagRepository;
import com.smatt.dao.UserRepository;
import com.smatt.lib.JMailChimp;
import com.smatt.models.Post;
import com.smatt.models.Tag;
import com.smatt.models.User;
import io.vavr.collection.Iterator;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertNotNull;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@TestPropertySource("/application-local.properties")
public class SpringBlogApplicationTests {

    Logger logger = Logger.getLogger(SpringBlogApplicationTests.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    BlogProperties blogProperties;

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

//    @Test
    public void givenKeywords_whenSeacrh_thenResults() {
        postRepository.findByPostContaining("This", new PageRequest(0,10));
    }


//    @Test
    public void givenTags_whenSave_thenNoError() {

        List<Tag> tags = (List<Tag>) tagRepository.findAll();

//	    Post p = new Post(2, "This is a simple Post");
//	    p.setTitle("Simple 1");
//	    p.setTags(new HashSet<>(Arrays.asList(tags.get(0), tags.get(1))));
//	    logger.info("saved post = " + postRepository.save(p));

	    Post p = postRepository.findOne("ThB2NCF7o5");
	    logger.info("Fetched post = \n" + p);
	    logger.info("===============================");
	    p.getTags().add(tags.get(2));
	    p = postRepository.save(p);
	    logger.info("updated Post = \n" + p);

    }

//    @Test
    public void givenTag_whenPosts_thenPostsWithTag() {
        Tag tag = tagRepository.findByTag("Java");
        List<Post> posts = tag.getPosts();
        logger.info("posts count == " + posts.size());
        posts.forEach(p -> {
            System.out.println(p.getTitle());
        });
    }


//    @Test
    public void whenJMailChimpAddSubscriber_thenAddSubscriber() throws Exception {
        JMailChimp jMailChimp = new JMailChimp(blogProperties.getMailChimpApiKey(), blogProperties.getMailChimpApiRoot());
        jMailChimp.addSubscriber("seats@gmail.com",
                blogProperties.getMailChimpListId(), JMailChimp.STATUS.SUBSCRIBED);
    }
}
