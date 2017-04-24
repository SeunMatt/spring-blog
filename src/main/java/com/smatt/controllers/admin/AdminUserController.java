package com.smatt.controllers.admin;

import com.smatt.dao.UserRepository;
import com.smatt.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * Created by smatt on 22/04/2017.
 */
@Controller
@RequestMapping(value = "/eyin/users")
public class AdminUserController {

    @Autowired
    UserRepository userRepository;
    private String index = "/eyin/users";
    Logger logger = Logger.getLogger(AdminUserController.class);


    @GetMapping(value = {"", "/"})
    public String index(ModelMap model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("userMenu", true);
        return "admin/users/index";
    }

    @GetMapping(value = "/view/{id}")
    public String view(ModelMap model, RedirectAttributes attr, @PathVariable String id) {
        User user = userRepository.findOne(id);
        if(user == null) {
            attr.addFlashAttribute("error", "User Not Found! Invalid Parameter");
            return "redirect:"+index;
        }

        model.addAttribute("user", user);
        model.addAttribute("userMenu", true);

        return "admin/users/view";
    }


    @PostMapping(value = "/ban")
    public String ban(RedirectAttributes attr, @RequestParam(value = "id") String id, @RequestParam(value = "bstatus") String bstatus) {
        User user = userRepository.findOne(id);
        if(user == null) {
            attr.addFlashAttribute("error", "User Object Not Found. Missing Parameter!");
            return "redirect:/eyin/users/view/"+id;
        }

        user.setBanned(Boolean.valueOf(bstatus));
        user.setUpdatedAt(new Date());
        userRepository.save(user);

        attr.addFlashAttribute("success", "User " + user.getName() + " has been banned successfully");
        return "redirect:/eyin/users/view/"+id;
    }

    @PostMapping(value = "/delete")
    public String delete(RedirectAttributes attr, @RequestParam(value = "id") String id) {
        User user = userRepository.findOne(id);
        if(user == null) {
            attr.addFlashAttribute("error", "User Object Not Found. Missing Parameter!");
            return "redirect:"+index;
        }

        userRepository.delete(user);

        attr.addFlashAttribute("success", "User has been deleted successfully");
        return "redirect:"+index;
    }




}
