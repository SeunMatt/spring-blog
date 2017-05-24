package com.smatt.controllers.front;

import com.smatt.config.Constants;
import com.smatt.dao.CategoryRepository;
import com.smatt.dao.PostRepository;
import com.smatt.models.Category;
import com.smatt.models.Post;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

/**
 * Created by smatt on 21/03/2017.
 */
@Controller
public class HomeController {

    public PostRepository postRepository;
    public CategoryRepository categoryRepository;
    Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    public HomeController(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        Assert.notNull(postRepository, "postRepository is null in HomeController");
        Assert.notNull(categoryRepository, "categoryRepository is null in HomeController");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {

        List<Post> latestPosts = postRepository.findAllPosts(
                new PageRequest(0, 8, new Sort(Sort.Direction.DESC, "createdAt")));
        logger.info("latest posts returned = " + latestPosts.size());

        //2 left latest posts left
        if(latestPosts.size() > 2) {
            List<Post> latestPostsLeft = Arrays.asList(latestPosts.remove(0), latestPosts.remove(1));
        }

        List<Post> trendingPosts = postRepository.findAllPosts(
                    new PageRequest(0, 6, new Sort(Sort.Direction.DESC, "views")));

        logger.info("trending posts = " + trendingPosts.size() + "\n" + trendingPosts.toString());

        //featured posts
        List<Post> featuredPosts = postRepository.findByFeaturedEqualsOrderByCreatedAtDesc(
                true, new PageRequest(0, 3));
        logger.info("featured posts sorted by latest and limit ed to 3 == " + featuredPosts.size()  + "\n" + featuredPosts.toString());


        //announcements
        List<Post> announcements = postRepository.findByCategoryOrderByCreatedAtDesc(
               categoryRepository.findByCategory(Constants.CATEGORY_ANNOUNCEMENT), new PageRequest(0, 4));

        logger.info("announcements == " + announcements.size() + "\n" + announcements.toString());

//        modelMap.addAttribute("latestPosts", latestPosts);

        return "front/index";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "front/about";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "front/contact";
    }



}


/*
*     LocalDateTime dateTime = LocalDateTime.now()
            .minus(1, ChronoUnit.WEEKS)
            .minus(1, ChronoUnit.DAYS)
            .withHour(23).withMinute(59)
            .withSecond(0);

        List<Post> latestPosts = postRepository.findByCreatedAtGreaterThan(dateTime);
*
*
* */
