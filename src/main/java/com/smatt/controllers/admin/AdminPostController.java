package com.smatt.controllers.admin;

import com.smatt.config.Constants;
import com.smatt.config.Roles;
import com.smatt.dao.CategoryRepository;
import com.smatt.dao.PostRepository;
import com.smatt.dao.SectionRepository;
import com.smatt.models.Post;
import com.smatt.models.User;
import com.smatt.service.StorageService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by smatt on 12/04/2017.
 */

@Controller
@RequestMapping(value = "/eyin/posts")
public class AdminPostController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SectionRepository sectionRepository;

    private String index = "/eyin/posts";

    @Autowired
    StorageService storageService;

    @Autowired
    HttpSession session;

    Logger logger = Logger.getLogger(AdminPostController.class);

    @GetMapping(value = {"", "/"})
    public String index(ModelMap model) {

       if(Roles.SUPER_ADMIN.toString().equals(((User) session.getAttribute("user")).getRole()) || Roles.EDITOR.toString().equals(((User) session.getAttribute("user")).getRole())) {
         //show all posts
          model.addAttribute("posts", postRepository.findAll());
       } else {
           //just show the ones for the user
           model.addAttribute("posts", postRepository.findByAuthor((User)session.getAttribute("user")));
       }

       model.addAttribute("postMenu", true);
       return "admin/posts/index";
    }

    @PostMapping(value = {"", "/"})
    public String save(Post post, @RequestParam("file") MultipartFile file, @RequestParam("category") String category,
                       @RequestParam("section") String section, RedirectAttributes attr, HttpSession session) {
        logger.info("post incoming == " + post.toString());

        if(!post.validate()) {
            attr.addFlashAttribute("post", post);
            attr.addFlashAttribute("error", "Some Important Parameters are missing. Post must have title and post body");
            return "redirect:"+index+"/edit";
        }

        if(file != null && !file.isEmpty() && !file.getContentType().contains("image")) {
            attr.addFlashAttribute("post", post);
            attr.addFlashAttribute("error", "Only Image files are allowed kindly try again please");
            return "redirect:"+index+"/edit";
        }

        if(!StringUtils.isEmpty(post.getId())) {
            //updating
             Post existP = postRepository.findOne(post.getId());
             logger.info("existing post = " + existP.toString());
            if(existP != null) {
                existP.setPost(post.getPost());
                existP.setCategory(categoryRepository.findOne(category));
                existP.setSection(sectionRepository.findOne(section));
                existP.setPublished(post.isPublished());

                if(!StringUtils.isEmpty(post.getCoverPic()) && !StringUtils.equals(existP.getCoverPic(), post.getCoverPic()) && !file.isEmpty()) {
                   logger.info("coverPic changed called");
                    //I have changed the coverpic while updating so delete the old one
                    String oldPix = existP.getCoverPic();
                    existP.setCoverPic(storageService.store(file));
                    if(!StringUtils.isEmpty(oldPix)) storageService.delete(oldPix);
                }
                logger.info("updated post = " + postRepository.save(existP).toString());
                attr.addFlashAttribute("success", "Post updated Successfully");
                return "redirect:/eyin/posts/read/" + existP.getId();
            }
        }
        else {
            //saving afresh
            if(file != null && !file.isEmpty()) {
                post.setCoverPic(storageService.store(file));
            }
            post.setAuthor( ((User) session.getAttribute(Constants.LOGGED_IN_USER)));
            post.setCategory(categoryRepository.findOne(category));
            post.setSection(sectionRepository.findOne(section));
            Post savedPost = postRepository.save(post);
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
       model.addAttribute("postMenu", true);
       return "admin/posts/post";
    }

    @GetMapping(value = {"/edit/{id}", "/edit"})
    public String edit(@PathVariable(required = false) String id, ModelMap model) {
        model.addAttribute("postMenu", true);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("sections", sectionRepository.findAll());
        if(!StringUtils.isEmpty(id))
            model.addAttribute("post", postRepository.findOne(id));
        return "admin/posts/edit";
    }


    @PostMapping(value = "/delete")
    public String delete(@RequestParam(value = "id") String id, RedirectAttributes attr) {
//        logger.info("Delete Invoked and id == " + id);
        postRepository.delete(id);
        attr.addFlashAttribute("success", "Post Deleted Successfully");
        return "redirect:"+index;
    }

}

//       Query query = new Query();
//       query.addCriteria(Criteria.where("_id").is(id));
//       Update update = new Update();
//       update.inc("read", 1);
//       FindAndModifyOptions options = new FindAndModifyOptions();
//       options.returnNew(true);
//       logger.info("posts modified read" + mongoTemplate.findAndModify(query, update, options, Post.class));
