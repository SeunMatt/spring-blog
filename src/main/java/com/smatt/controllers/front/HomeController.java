package com.smatt.controllers.front;

import com.smatt.dao.CategoryRepository;
import com.smatt.dao.PostRepository;
import com.smatt.models.Category;
import com.smatt.models.Contact;
import com.smatt.models.Post;
import com.smatt.service.ContactService;
import com.smatt.service.NewsletterService;
import com.smatt.utils.URLHelper;
import com.smatt.utils.Utility;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by smatt on 21/03/2017.
 */
@Controller
public class HomeController {

	private PostRepository postRepository;
    private CategoryRepository categoryRepository;
    private ContactService contactService;
    private NewsletterService newsletterService;

    Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    public HomeController(PostRepository pRepo, CategoryRepository cRepo, ContactService c, NewsletterService n) {
        this.postRepository = pRepo;
        this.categoryRepository = cRepo;
        this.contactService = c;
        this.newsletterService = n;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpServletRequest request, HttpSession session) {


        //featured posts
        List<Post> featuredPosts = postRepository.findFeaturedPosts(
                new PageRequest(0, 2))
                .stream()
                .map(p -> {
                    p.setPost(Utility.makePreviewText(p.getPost()));
                    return p;
                }).collect(Collectors.toList());

//        logger.info("featured posts sorted by latest and limit ed to 3 == " + featuredPosts.size()  + "\n" + featuredPosts.toString());

		modelMap.addAttribute("featuredPosts", featuredPosts);

        List<Category> categories = (List<Category>) categoryRepository.findAll();
       	List< Map<String, Page<Post>> > superList = new ArrayList<>();


		categories.stream().forEachOrdered(c -> {
			HashMap<String, Page<Post>> map = new HashMap<>();
			map.put(c.getCategory(), postRepository.findByCategory(c, new PageRequest(0, 4, Sort.Direction.DESC, "createdAt")));
			superList.add(map);
		});

        modelMap.addAttribute("superList", superList);

        return "front/index";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "front/about";
    }

    @RequestMapping(value = "/contact", method = {RequestMethod.GET, RequestMethod.POST})
    public String contact(HttpServletRequest req, Contact contact, RedirectAttributes attr, ModelMap modelMap) {
        if(req.getMethod().equals(RequestMethod.GET.toString())) {
            return "front/contact";
        }
        else {

            if(!contact.isValidDetails()) {
                attr.addFlashAttribute("error", "Missing Required Parameter");
                return "redirect:/contact";
            }

            try {
                logger.info("Contact Attempt: " + contact.toString());
                contactService.sendEmail(contact);
                attr.addFlashAttribute("success", "Your message has been sent successfully! I'll be in touch soon");
                return "redirect:/contact";
            } catch (Exception e) {
                logger.error("Error Sending Contact Form Message \n" + e.getMessage());
                e.printStackTrace();
                attr.addFlashAttribute("error", "Oops! Error Occurred, please try again");
                return "redirect:/contact";
            }
        }
    }

    @RequestMapping("/newsletter/subscribe")
    public String subscribe(HttpServletRequest req, ModelMap modelMap, RedirectAttributes attr,
                            @RequestParam(value = "email", required = false) String email) {

        if(req.getMethod().equals(RequestMethod.GET.toString())) {
            modelMap.addAttribute("previousLink", "/" + req.getRequestURI());
            return "front/newsletter_subscribed";
        }
        else if(req.getMethod().equals(RequestMethod.POST.toString())) {

            if (StringUtils.isEmpty("email")) {
                attr.addFlashAttribute("error", "Email Should be Valid");
                return "redirect:/" + req.getRequestURI();
            }

            newsletterService.subscribe(email);
            return "redirect:/newsletter/subscribe";
        }

        return "redirect:/";

    }


}