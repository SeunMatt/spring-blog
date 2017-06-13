package com.smatt.controllers.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smatt.config.Roles;
import com.smatt.dao.CategoryRepository;
import com.smatt.dao.PostRepository;
import com.smatt.dao.SectionRepository;
import com.smatt.models.Post;
import com.smatt.models.User;
import com.smatt.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by smatt on 12/04/2017
 */

@Controller
@RequestMapping(value = "/eyin/posts")
public class AdminPostController {

    private PostRepository postRepository;
    private CategoryRepository categoryRepository;
    private SectionRepository sectionRepository;
    private PostService postService;

    private String index = "/eyin/posts";
    private Logger logger = Logger.getLogger(AdminPostController.class);

    @Autowired
    public AdminPostController(PostRepository pR, CategoryRepository cR, SectionRepository sR, PostService pS) {
        this.postRepository = pR;
        this.categoryRepository = cR;
        this.sectionRepository = sR;
        this.postService = pS;
    }

    @GetMapping(value = {"", "/"})
    public String index(ModelMap model, HttpSession session) {
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

        ModelMap validateMap = postService.validatePost(post, attr, file, index);

        //validate
        if(! (boolean) validateMap.get("status")) {
            attr = (RedirectAttributes) validateMap.get("attr");
            return validateMap.get("url").toString();
        }

        //this will either save or update as the case maybe
        ModelMap map = postService.savePost(post, category, section, file, session);

        attr.addFlashAttribute("success", map.get("status"));
        return map.get("url").toString();
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


    /*
    * Ajax invoked method for saving
//    * */
//    @PostMapping(value = {"/a"})
//    @ResponseBody
//    public String ajaxSave(
//            Post post, RedirectAttributes attr, HttpSession session, @RequestParam("category") String category,
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("section") String section
//    ) {
//
//            ModelMap validateMap = postService.validatePost(post, attr, file, index);
//            //validate
//            if(! (boolean) validateMap.get("status")) {
//                return "{\"error\":\"" + validateMap.get("error") + "\"}";
//            }
//
//            //this will either save or update as the case maybe
//            ModelMap map = postService.savePost(post, category, section, file, session);
//            return "{\"success\":\"success\",\"id\":\"" + map.get("post") + "\"}";
//    }


    @GetMapping(value = "/preview/{id}")
    public String preview(@PathVariable("id") String id, RedirectAttributes attr, ModelMap modelMap, HttpSession session) {
            Post post = null;

            if(StringUtils.isEmpty(id) || (post = postRepository.findOne(id)) == null) {
                attr.addFlashAttribute("post", post);
                return "redirect:" + index + "/edit";
            }
        modelMap.addAttribute("trendingPosts",  postRepository.findAllPublishedPosts(
                new PageRequest(0, 4, new Sort(Sort.Direction.DESC, "views"))) );
        modelMap.addAttribute("relatedPosts", postRepository.findByCategory(post.getCategory(), new PageRequest(0,5)));
        session.setAttribute("categories", categoryRepository.findAll());
        modelMap.addAttribute("post", post);
            return "admin/posts/preview";
    }


}

//       Query query = new Query();
//       query.addCriteria(Criteria.where("_id").is(id));
//       Update update = new Update();
//       update.inc("read", 1);
//       FindAndModifyOptions options = new FindAndModifyOptions();
//       options.returnNew(true);
//       logger.info("posts modified read" + mongoTemplate.findAndModify(query, update, options, Post.class));
