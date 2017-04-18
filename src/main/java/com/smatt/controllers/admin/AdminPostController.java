package com.smatt.controllers.admin;

import com.smatt.dao.PostRepository;
import com.smatt.models.Post;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * Created by smatt on 12/04/2017.
 */

@Controller
@RequestMapping(value = "/eyin/posts")
public class AdminPostController {

    @Autowired
    PostRepository postRepository;

    Logger logger = Logger.getLogger(AdminPostController.class);

    @GetMapping(value = {"", "/"})
    public String index(ModelMap model) {
       logger.info("post when reading = \n" + postRepository.findAll().get(0).toString());
       model.addAttribute("posts", postRepository.findAll());
       return "admin/posts/index";
    }

    @PostMapping(value = {"", "/"})
    public String save(ModelMap model, Post post, RedirectAttributes attr) {

        if(!post.validate()) {
            attr.addFlashAttribute("post", post);
            attr.addFlashAttribute("error", "Some Important Parameters are missing. Post must have title, author and post body");
            return "redirect:/eyin/posts/edit";
        }

        if(post.getId() != null && !post.getId().isEmpty()) {
            //updating
             Post existP = postRepository.findOne(post.getId());
            if(existP != null) {
                existP.setPost(post.getPost());
                existP.setAuthor(post.getAuthor());
                existP.setUpdatedAt(new Date());
                existP.setCategory_id(post.getCategory_id());
                existP.setSection_id(post.getSection_id());
                existP.setCover_pic(post.getCover_pic());
                existP.setPublished(post.isPublished());
                logger.info("updated post = " + postRepository.save(existP).toString());
                attr.addFlashAttribute("success", "Post updated Successfully");
                return "redirect:/eyin/posts/read/" + existP.getId();
            }
        } else {
            //saving afresh
            post.setCreatedAt(new Date());
            post.setUpdatedAt(new Date());
            Post savedPost = postRepository.insert(post);
            logger.info("Newly saved post obj ==\n " + savedPost.toString());
            attr.addFlashAttribute("success", "Post saved Successfully");
            return "redirect:/eyin/posts/read/" + savedPost.getId();
        }

        attr.addFlashAttribute("error", "Unable to Find or Create Post");
        return "redirect:";
    }

   @GetMapping(value = "/read/{id}")
    public String read(@PathVariable String id, ModelMap model) {
        model.addAttribute("post", postRepository.findOne(id));
        return "admin/posts/post";
    }

    @GetMapping(value = {"/edit/{id}", "/edit"})
    public String edit(@PathVariable(required = false) String id, ModelMap model) {
        if(id != null && !id.isEmpty()) model.addAttribute("post", postRepository.findOne(id));
        return "admin/posts/edit";
    }



    @PostMapping(value = "/delete")
    public String delete(@RequestParam("id") String id, RedirectAttributes attr) {
        logger.info("Delete Invoked and id == " + id);
        postRepository.delete(id);
        attr.addFlashAttribute("success", "Post Deleted Successfully");
        return "redirect:";
    }




}
