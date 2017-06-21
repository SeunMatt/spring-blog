package com.smatt.controllers.front;

import com.smatt.config.Constants;
import com.smatt.dao.CategoryRepository;
import com.smatt.dao.PostRepository;
import com.smatt.models.Category;
import com.smatt.models.Post;
import com.smatt.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import java.util.Objects;

/**
 * Created by smatt on 04/04/2017.
 */

@Controller
//@RequestMapping(value = "/app")
public class PostController {

    private final PostService postService;
    PostRepository postRepository;
    CategoryRepository categoryRepository;
    Logger logger = Logger.getLogger(PostController.class);

    @Autowired
    public PostController(PostRepository postRepository, CategoryRepository categoryRepository, PostService postService) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.postService = postService;
    }


    @GetMapping("/posts")
    public String index(ModelMap model) {
        model.addAttribute("posts", postRepository.findAll());
        return "front/posts";
    }


    //read a single post
    @GetMapping(value = "/p/{id}")
    public String read(ModelMap modelMap, RedirectAttributes redirectAttributes, @PathVariable("id") String id) {
        Post post = postRepository.findOne(id);

        if(post == null || !post.isPublished()) {
            redirectAttributes.addFlashAttribute("error", "Post Not Found");
            return "redirect:/";
        }

        post.setViews(post.getViews() + 1);

        //increase views async
        postService.updatePostAsyn(post);

        modelMap.addAttribute("trendingPosts",  postRepository.findAllPublishedPosts(
                    new PageRequest(0, 4, new Sort(Sort.Direction.DESC, "views"))) );
        modelMap.addAttribute("relatedPosts", postRepository.findByCategory(post.getCategory(), new PageRequest(0,5)));
        modelMap.addAttribute("post", post);
        return "front/post";
    }


    //view all posts available in a category
    @GetMapping(value = {"/posts/{category}/{page}", "/posts/{category}"})
    public String readByCategory(ModelMap modelMap, RedirectAttributes attr,
      @PathVariable(value = "page", required = false) Integer page, @PathVariable("category") String category) {

//        logger.info("Category = " + category + " page = " + page);

        if(StringUtils.isEmpty(category)) {
            attr.addFlashAttribute("status", "This Category \"" + category + "\" is not found!");
            return "redirect:/";
        }

        if(page == null || page < 0) page = 0;

        Page<Post> posts = postRepository.findByCategory(categoryRepository.findByCategory(category),
                    new PageRequest(page, Constants.MAX_POST_PER_PAGE));

        logger.info("post count = " + posts.getTotalElements());

        if(posts.getTotalElements() <= 0) {
            attr.addFlashAttribute("status", "There are no posts yet for this category. Kindly select another");
            return "redirect:/";
        }


        modelMap = postService.showPaginatedPostList(
                modelMap,
                posts,
                StringUtils.capitalize(category).concat(" Stories"),
                "/posts/" + category
                );

        return "front/posts";
    }


    @GetMapping(value = {"/search/{keyword}", "/search/{keyword}/{page}"})
    public String search(ModelMap modelMap, RedirectAttributes attr, @PathVariable(value = "page", required = false) Integer page,
            @PathVariable("keyword") String keyword) {

        logger.info("keyword = " + keyword);

        if(StringUtils.isEmpty(keyword) || keyword.length() < 4) {
            return "redirect:/";
        }

        if(page == null || page < 0) page = 0;

        Page<Post> posts = postRepository.findByPostContaining(keyword,
                new PageRequest(page, Constants.MAX_POST_PER_PAGE));

        logger.info("search post count = " + posts.getTotalElements());

        if(posts.getTotalElements() <= 0) {
            attr.addFlashAttribute("status", "Search for phrase \"" + keyword + "\" not found! Refine and try again please");
            return "redirect:/";
        }

        modelMap = postService.showPaginatedPostList(
                modelMap,
                posts,
                "Search Result(s)",
                "/search/" + keyword
        );

        return "front/posts";
    }

}
