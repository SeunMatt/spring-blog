package com.smatt.controllers.admin;


import com.smatt.config.Constants;
import com.smatt.dao.CategoryRepository;
import com.smatt.dao.PostRepository;
import com.smatt.dao.SectionRepository;
import com.smatt.models.Post;
import com.smatt.models.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by smatt on 05/04/2017.
 */
@Controller
@RequestMapping(value = "/eyin")
public class AdminIndexController {

    Logger logger = Logger.getLogger(AdminIndexController.class);

    @Autowired
    PostRepository postRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SectionRepository sectionRepository;

    @GetMapping
    public String index(ModelMap model, HttpSession session) {
        model.addAttribute("dashboardMenu", true);
        User user = (User) session.getAttribute(Constants.LOGGED_IN_USER);

        List<Post> userPosts = postRepository.findByAuthor(user);
        //total posts by logged-in user
        model.addAttribute("userPosts", userPosts.size());
        model.addAttribute("userPostViewsCount", userPosts.stream().collect(Collectors.summingInt(p -> p.getViews())));

        if(StringUtils.equals(user.getRole(), Constants.ROLE_SUPER_ADMIN) ||
                StringUtils.equals(user.getRole(), Constants.ROLE_EDITOR)) {
            List<Post> allPosts = (List<Post>) postRepository.findAll();
            model.addAttribute("categoryCount", ((List)categoryRepository.findAll()).size());
            model.addAttribute("sectionCount", ((List)sectionRepository.findAll()).size());
            model.addAttribute("postCount", allPosts.size());
            model.addAttribute("viewCount", allPosts.stream().collect(Collectors.summingInt(p -> p.getViews())));
        }

        //total views of posts by the user
        int total = 0;


        return "admin/index";
    }


}
