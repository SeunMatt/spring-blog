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

        if(Objects.isNull(post)) {
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
    public String readByCategory(ModelMap modelMap, @PathVariable(value = "page", required = false) Integer page,
                                 @PathVariable("category") String category) {

//        logger.info("page == " + page);
        logger.info("Category = " + category);

        if(Objects.isNull(page) || page < 0) page = 0;

        if(StringUtils.isEmpty(category)) {
            //return a 404 posts not found
            //temporarily redirecting to index
            return "redirect:/";
        }

        Page<Post> posts = postRepository.findByCategory(
                categoryRepository.findByCategory(category), new PageRequest(page, Constants.MAX_POST_PER_PAGE));

        logger.info("post count = " + posts.getTotalElements());

        if(posts.getTotalElements() <= 0) {
            //return to a page for empty category content
            //for now return to index
            return "redirect:/";
        }


        int totalPages = posts.getTotalPages();
        int currentPage = posts.getNumber();
//        logger.info("currentPage = " + currentPage);
        modelMap.addAttribute("title", StringUtils.capitalize(category).concat(" Stories"));
        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("currentPage", (currentPage + 1));
        modelMap.addAttribute("totalPages", totalPages);
        if( (currentPage + 1) < totalPages )
            modelMap.addAttribute("nextLink", "/posts/" + category + "/" + (currentPage + 1)); //older posts
        if( (currentPage - 1) >= 0 )
            modelMap.addAttribute("prevLink", "/posts/" + category + "/" + (currentPage - 1)); //newer posts

        modelMap.addAttribute("trendingPosts",  postRepository.findAllPublishedPosts(
                new PageRequest(0, 4, new Sort(Sort.Direction.DESC, "views"))) );
        return "front/posts";
    }


}
