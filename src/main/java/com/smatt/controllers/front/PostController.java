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
        return "front/posts";
    }

    @GetMapping(path = "/post/{id}")
    public String read(@PathVariable String id, ModelMap model) {
        model.addAttribute("post", postRepository.findOne(id));
        return "front/post";
    }


}
