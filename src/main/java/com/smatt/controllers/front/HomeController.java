package com.smatt.controllers.front;

import com.smatt.config.Constants;
import com.smatt.dao.CategoryRepository;
import com.smatt.dao.PostRepository;
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

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by smatt on 21/03/2017.
 */
@Controller
public class HomeController {

    PostRepository postRepository;
    CategoryRepository categoryRepository;
    EntityManager entityManager;

    Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    public HomeController(PostRepository postRepository, CategoryRepository categoryRepository, EntityManager entityManager) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
        Assert.notNull(postRepository, "postRepository is null in HomeController");
        Assert.notNull(categoryRepository, "categoryRepository is null in HomeController");
        Assert.notNull(entityManager, "entityManager is null in HomeController");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {

        List<Post> latestPosts = postRepository.findAllPublishedPosts(
                new PageRequest(0, 8, new Sort(Sort.Direction.DESC, "createdAt")));
        logger.info("latest posts returned = " + latestPosts.size());

        List<Post> latestPostsLeft = new ArrayList<>();

        //2 left latest posts left
        if(latestPosts.size() > 2) {
            latestPostsLeft.addAll(Arrays.asList(latestPosts.remove(0), latestPosts.remove(1)));
        }



        logger.info("latest posts  left  = " + latestPostsLeft.size());

        List<Post> trendingPosts = postRepository.findAllPublishedPosts(
                    new PageRequest(0, 6, new Sort(Sort.Direction.DESC, "views")));

        logger.info("trending posts = " + trendingPosts.size() + "\n" + trendingPosts.toString());

        //featured posts
        List<Post> featuredPosts = postRepository.findFeaturedPosts(
                new PageRequest(0, 3));
        logger.info("featured posts sorted by latest and limit ed to 3 == " + featuredPosts.size()  + "\n" + featuredPosts.toString());


        //announcements
        List<Post> announcements = postRepository.findByCategoryOrderByCreatedAtDesc(
               categoryRepository.findByCategory(Constants.CATEGORY_ANNOUNCEMENT), new PageRequest(0, 4));

        logger.info("announcements == " + announcements.size() + "\n" + announcements.toString());

        modelMap.addAttribute("latestPosts", latestPosts);
        modelMap.addAttribute("latestPostsLeft", latestPostsLeft);
        modelMap.addAttribute("announcements", announcements);
        modelMap.addAttribute("trendingPosts", trendingPosts);
        modelMap.addAttribute("featuredPosts", featuredPosts);

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
*         logger.info("em = " + entityManager.createQuery("select p from Post p").setMaxResults(1).getSingleResult().toString());

* */
