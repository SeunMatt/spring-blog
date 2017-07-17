package com.smatt.controllers.front;

import com.smatt.dao.CategoryRepository;
import com.smatt.dao.PostRepository;
import com.smatt.models.Category;
import com.smatt.models.Contact;
import com.smatt.models.Post;
import com.smatt.service.ContactService;
import com.smatt.utils.URLHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smatt on 21/03/2017.
 */
@Controller
public class HomeController {

	private PostRepository postRepository;
    private CategoryRepository categoryRepository;
    private ContactService contactService;

    Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    public HomeController(PostRepository pRepo, CategoryRepository cRepo, ContactService c) {
        this.postRepository = pRepo;
        this.categoryRepository = cRepo;
        this.contactService = c;
        Assert.notNull(postRepository, "postRepository is null in HomeController");
        Assert.notNull(categoryRepository, "categoryRepository is null in HomeController");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpServletRequest request, HttpSession session) {

        List<Post> trendingPosts = postRepository.findAllPublishedPosts(
                    new PageRequest(0, 4, new Sort(Sort.Direction.DESC, "views")));

//        logger.info("trending posts = " + trendingPosts.size() + "\n" + trendingPosts.toString());

        //featured posts
        List<Post> featuredPosts = postRepository.findFeaturedPosts(
                new PageRequest(0, 3));
//        logger.info("featured posts sorted by latest and limit ed to 3 == " + featuredPosts.size()  + "\n" + featuredPosts.toString());

		modelMap.addAttribute("trendingPosts", trendingPosts);
        modelMap.addAttribute("featuredPosts", featuredPosts);

        List<Category> categories = (List<Category>) categoryRepository.findAll();
       	List< Map<String, Page<Post>> > superList = new ArrayList<>();


		categories.stream().forEachOrdered(c -> {
			HashMap<String, Page<Post>> map = new HashMap<>();
			map.put(c.getCategory(), postRepository.findByCategory(c, new PageRequest(0, 4, Sort.Direction.DESC, "createdAt")));
			superList.add(map);
		});

//        superList.forEach((m) -> System.out.println(m.keySet().toString() + " count == " + m.get(m.keySet().toArray()[0]).getTotalElements()));

        modelMap.addAttribute("superList", superList);

        session.setAttribute("categories", categories);

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
                contactService.sendEmail(contact);
                attr.addFlashAttribute("success", "Your message has been sent successfully! I'll be in touch soon");
                return "redirect:/contact";
            } catch (Exception e) {
                logger.error("Error Sending Contact Form Message \n" + e.getMessage());
                e.printStackTrace();
                //TODO
                // contact the guy back to reach you
                attr.addFlashAttribute("error", "Oops! Error Occurred, please try again");
                return "redirect:/contact";
            }
        }
    }

}