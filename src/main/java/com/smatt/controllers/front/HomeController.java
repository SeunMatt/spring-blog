package com.smatt.controllers.front;

import com.smatt.config.Constants;
import com.smatt.dao.CategoryRepository;
import com.smatt.dao.PostRepository;
import com.smatt.models.Category;
import com.smatt.models.Post;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by smatt on 21/03/2017.
 */
@Controller
public class HomeController {

	private HttpSession session;
	PostRepository postRepository;
    CategoryRepository categoryRepository;

    Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    public HomeController(PostRepository postRepository, CategoryRepository categoryRepository, HttpSession session) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.session = session;
        Assert.notNull(postRepository, "postRepository is null in HomeController");
        Assert.notNull(categoryRepository, "categoryRepository is null in HomeController");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {

        List<Post> trendingPosts = postRepository.findAllPublishedPosts(
                    new PageRequest(0, 4, new Sort(Sort.Direction.DESC, "views")));

//        logger.info("trending posts = " + trendingPosts.size() + "\n" + trendingPosts.toString());

        //featured posts
        List<Post> featuredPosts = postRepository.findFeaturedPosts(
                new PageRequest(0, 3));
//        logger.info("featured posts sorted by latest and limit ed to 3 == " + featuredPosts.size()  + "\n" + featuredPosts.toString());

		modelMap.addAttribute("trendingPosts", trendingPosts);
        modelMap.addAttribute("featuredPosts", featuredPosts);

        List<Category> categories = (List<Category>) categoryRepository.findAll();
        List<Post> allPosts = postRepository.findAllPosts(new PageRequest(0, 4, new Sort(Sort.Direction.DESC, "createdAt")));

       	List< Map<String, Page<Post>> > superList = new ArrayList<>();


		//TODO look at replacing this algo with another that uses Streams API
		categories.stream().forEachOrdered(c -> {
			HashMap<String, Page<Post>> map = new HashMap<>();
			map.put(c.getCategory(), postRepository.findByCategory(c, new PageRequest(0, 4, Sort.Direction.DESC, "createdAt")));
			superList.add(map);
		});

		 //TODO implement this and make it work
//        categories.stream().forEachOrdered(c -> {
//        	HashMap<String, List<Object>> map = new HashMap<>();
//			map.put(c.getCategory(), allPosts.stream()
//					.map(post -> {
//						if(c.equals(post.getCategory()))
//							return post;
//						return null;
//					})
////					.filter(p ->  p != null)
//					.collect(Collectors.toList()));
//			superList.add(map);
//		});

        superList.forEach((m) -> System.out.println(m.keySet().toString() + " count == " + m.get(m.keySet().toArray()[0]).getTotalElements()));

        modelMap.addAttribute("superList", superList);

        session.setAttribute("categories", categories);

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
