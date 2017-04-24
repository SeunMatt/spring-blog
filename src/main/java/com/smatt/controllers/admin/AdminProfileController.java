package com.smatt.controllers.admin;

import com.smatt.dao.UserRepository;
import com.smatt.models.User;
import com.smatt.service.MySecurityService;
import com.smatt.service.StorageService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.plugin.liveconnect.SecurityContextHelper;

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
    StorageService storageService;

    String index = "/eyin/users/profile";
    Logger logger = Logger.getLogger(AdminProfileController.class);


    @GetMapping(value = {"", "/"})
    public String index(ModelMap model, RedirectAttributes attr) {
        User user = userRepository.findByUsername(MySecurityService.findLoggedInUsername());
//        logger.info("user profile = " + user.toString());
        if(user == null) {
            attr.addFlashAttribute("error", "You have to login first");
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "admin/profile/index";
    }

    @PostMapping(value = {"", "/"})
    public String update(RedirectAttributes attr, User user) {

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
        return "redirect:"+index;
    }

    @PostMapping(value = {"/pic"})
    @ResponseBody
    public String updateProfilePic(@RequestParam("file") MultipartFile file, RedirectAttributes attr) {
        String fileName = storageService.store(file);
        User user = userRepository.findByUsername(MySecurityService.findLoggedInUsername());
        if(!StringUtils.isEmpty(user.getProfilePic())) storageService.delete(user.getProfilePic());
        user.setProfilePic(fileName);
        userRepository.save(user);
        return "success";
    }



}
