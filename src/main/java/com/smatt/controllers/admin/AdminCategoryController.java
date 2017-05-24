package com.smatt.controllers.admin;

import com.smatt.dao.CategoryRepository;
import com.smatt.models.Category;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping(value = "/eyin/categories")
public class AdminCategoryController {

    private String index = "/eyin/categories";

    @Autowired
    CategoryRepository categoryRepository;

    Logger logger = Logger.getLogger(AdminCategoryController.class);

    @GetMapping(value = {"", "/"})
    public String index(ModelMap model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("categoryMenu", true);
        return "admin/categories/index";
    }

    @PostMapping(value = {"", "/"})
    public String add(RedirectAttributes attr, @RequestParam(value = "category") String category) {
        categoryRepository.save(new Category(category));
        attr.addFlashAttribute("success", "New Category Saved Successfully");
        return "redirect:"+index;
    }

    @PostMapping(value = "/delete")
    public String delete(RedirectAttributes attr, @RequestParam(value = "id") String id) {

//        logger.info("id of category == " + id);

        Category category = categoryRepository.findOne(id);

        if(category == null) {
            attr.addFlashAttribute("error", "Category Not Found");
        } else {
            categoryRepository.delete(category);
            attr.addFlashAttribute("success", "Category has been deleted successfully");
        }
        return "redirect:"+index;
    }

    @PostMapping(value = "/update")
    public String update(RedirectAttributes attr, Category category) {

        Category existingC = categoryRepository.findOne(category.getId());

        if(existingC == null) {
            attr.addFlashAttribute("error", "Invalid Request, Missing Parameter");
        } else {
            existingC.setCategory(category.getCategory());
            categoryRepository.save(existingC);
            attr.addFlashAttribute("success", "Category has been updated successfully");
        }
        return "redirect:"+index;
    }





}
