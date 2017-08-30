package com.smatt.controllers.admin;

import com.smatt.dao.SectionRepository;
import com.smatt.dao.TagRepository;
import com.smatt.models.Section;
import com.smatt.models.Tag;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by smatt on 22/04/2017.
 */
@Controller
@RequestMapping(value = "/eyin/tags")
public class AdminTagController {

    private String index = "/eyin/tags";
    private TagRepository tagRepository;
    private Logger logger = Logger.getLogger(AdminTagController.class);

    @Autowired
    public AdminTagController(TagRepository tr) {
        this.tagRepository = tr;
    }


    @GetMapping(value = {"", "/"})
    public String index(ModelMap model) {
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute("tagMenu", true);
        return "admin/tags/index";
    }

    @PostMapping(value = {"", "/"})
    public String add(RedirectAttributes attr, @RequestParam(value = "tag") String tag) {
        tagRepository.save(new Tag(tag));
        attr.addFlashAttribute("success", "New Tag added Successfully");
        return "redirect:"+index;
    }

    @PostMapping(value = "/delete")
    public String delete(RedirectAttributes attr, @RequestParam(value = "id") String id) {
       Tag tag = tagRepository.findOne(id);

        if(tag == null) {
            attr.addFlashAttribute("error", "Tag Not Found");
        } else {
            tagRepository.delete(tag);
            attr.addFlashAttribute("success", "Tag has been deleted successfully");
        }
        return "redirect:"+index;
    }

    @PostMapping(value = "/update")
    public String update(RedirectAttributes attr,Tag tag) {

        Tag existingT = tagRepository.findOne(tag.getId());
        if(existingT == null) {
            attr.addFlashAttribute("error", "Invalid Request, Missing Parameter");
        } else {
            existingT.setTag(tag.getTag());
            tagRepository.save(existingT);
            attr.addFlashAttribute("success", "Tag has been updated successfully");
        }
        return "redirect:"+index;
    }
}
