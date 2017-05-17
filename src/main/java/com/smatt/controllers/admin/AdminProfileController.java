package com.smatt.controllers.admin;

import com.smatt.config.Constants;
import com.smatt.dao.PostRepository;
import com.smatt.dao.UserRepository;
import com.smatt.models.User;
import com.smatt.service.MySecurityService;
import com.smatt.service.StorageService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by smatt on 23/04/2017.
 */
@Controller
@RequestMapping(value = "/eyin/users/profile")
public class AdminProfileController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    StorageService storageService;

    String index = "/eyin/users/profile";
    Logger logger = Logger.getLogger(AdminProfileController.class);


    @GetMapping(value = {"", "/"})
    public String index(ModelMap model, RedirectAttributes attr, HttpSession session) {
        model.addAttribute("posts", postRepository.findByAuthor( ((User) session.getAttribute("user")) ) );
        return "admin/profile/index";
    }

    @PostMapping(value = {"", "/"})
    public String update(RedirectAttributes attr, User user, HttpSession session) {

        User existingU = userRepository.findOne(user.getId());

        if(existingU == null) {
            attr.addFlashAttribute("error", "User Object Not Found. Missing Parameter!");
            return "redirect:"+index;
        }

        existingU.setUpdatedAt(new Date());
        existingU.setEmail(user.getEmail());
        existingU.setName(user.getName());
        existingU.setUsername(user.getUsername());
        existingU.setBio(user.getBio());
        userRepository.save(existingU);

        attr.addFlashAttribute("success", "Profile Updated Successfully");
        session.removeAttribute(Constants.LOGGED_IN_USER);
        session.setAttribute(Constants.LOGGED_IN_USER, existingU);
        return "redirect:"+index;
    }

    @PostMapping(value = {"/pic"})
    @ResponseBody
    public String updateProfilePic(@RequestParam("file") MultipartFile file, RedirectAttributes attr, HttpSession session) {
        String fileName = storageService.store(file);
        User user = userRepository.findByUsername(MySecurityService.findLoggedInUsername());
        String oldProfilePic = user.getProfilePic();
        user.setProfilePic(fileName);
        userRepository.save(user);
        session.removeAttribute(Constants.LOGGED_IN_USER);
        session.setAttribute(Constants.LOGGED_IN_USER, user);
        if(!StringUtils.isEmpty(oldProfilePic)) storageService.delete(oldProfilePic);
        return "success";
    }



}
