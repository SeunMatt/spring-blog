package com.smatt.controllers.admin;

import com.smatt.dao.CategoryRepository;
import com.smatt.dao.SectionRepository;
import com.smatt.models.Category;
import com.smatt.models.Section;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * Created by smatt on 22/04/2017.
 */
@Controller
@RequestMapping(value = "/eyin/sections")
public class AdminSectionController {

    private String index = "/eyin/sections";

    @Autowired
    SectionRepository sectionRepository;

    Logger logger = Logger.getLogger(AdminSectionController.class);

    @GetMapping(value = {"", "/"})
    public String index(ModelMap model) {
        model.addAttribute("sections", sectionRepository.findAll());
        model.addAttribute("sectionMenu", true);
        return "admin/sections/index";
    }

    @PostMapping(value = {"", "/"})
    public String add(RedirectAttributes attr, @RequestParam(value = "section") String section) {
        sectionRepository.save(new Section(section));
        attr.addFlashAttribute("success", "New Section added Successfully");
        return "redirect:"+index;
    }

    @PostMapping(value = "/delete")
    public String delete(RedirectAttributes attr, @RequestParam(value = "id") String id) {
//        logger.info("id of category == " + id);
       Section section = sectionRepository.findOne(id);

        if(section == null) {
            attr.addFlashAttribute("error", "Section Not Found");
        } else {
            sectionRepository.delete(section);
            attr.addFlashAttribute("success", "Section has been deleted successfully");
        }
        return "redirect:"+index;
    }

    @PostMapping(value = "/update")
    public String update(RedirectAttributes attr, Section section) {

        Section existingS = sectionRepository.findOne(section.getId());
        if(existingS == null) {
            attr.addFlashAttribute("error", "Invalid Request, Missing Parameter");
        } else {
            existingS.setSection(section.getSection());
            sectionRepository.save(existingS);
            attr.addFlashAttribute("success", "Section has been updated successfully");
        }
        return "redirect:"+index;
    }
}
