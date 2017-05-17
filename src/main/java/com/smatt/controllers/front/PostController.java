package com.smatt.controllers.front;

import com.smatt.dao.PostRepository;
import com.smatt.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by smatt on 04/04/2017.
 */

@Controller
//@RequestMapping(value = "/app")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    public String index(ModelMap model) {
        model.addAttribute("posts", postRepository.findAll());
        return "posts";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public @ResponseBody String add(Post post, ModelMap model) {
       if(post.validate()) { postRepository.save(post); }
       else { System.out.println("Error Invalid Object"); }
       return post.toString();
    }

    @GetMapping(path = "/post/{id}")
    public String read(@PathVariable String id, ModelMap model) {
        model.addAttribute("post", postRepository.findOne(id));
        return "post";
    }

    @PostMapping("/post/{id}")
    public String update(@RequestParam("id") String id, ModelMap model) {
        Post p = postRepository.findOne(id);
        if(postRepository == null) {
            model.addAttribute("error", "Post Not Found");
        } else {
            model.addAttribute("post", p);
        }

        return "redirect: /post";
    }

    @DeleteMapping("/post/{id}")
    public String delete(@RequestParam("id") String id, ModelMap model) {
        postRepository.delete(id);
        model.addAttribute("status", "Post Deleted Successfully");
        return "redirect: /post";
    }


}
