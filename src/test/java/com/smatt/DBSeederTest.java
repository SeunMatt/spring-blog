package com.smatt;

import com.smatt.config.Roles;
import com.smatt.dao.CategoryRepository;
import com.smatt.dao.SectionRepository;
import com.smatt.dao.UserRepository;
import com.smatt.models.Category;
import com.smatt.models.Section;
import com.smatt.models.User;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by smatt on 16/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DBSeederTest {

    Logger logger = Logger.getLogger(DBSeederTest.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads() { }


    @Test
    public void seedCategoryTable() {
        Category c = new Category("Technology");
        Category c2 = new Category("General");
        Category c3 = new Category("Business");
        categoryRepository.save(Arrays.asList(c,c2,c3));
        logger.info("category table seeded");
    }

    @Test
    public void seedSectionsTable() {
        Section s = new Section("General");
        Section s2 = new Section("International Business");
        Section s3 = new Section("Local Business");
        Section s4 = new Section("Biotechnology");
        sectionRepository.save(Arrays.asList(s,s2,s3,s4));
        logger.info("sections table seeded");
    }

    @Test
    public void seedUsersTable() {
        User user = new User();
        user.setName("Admin Super");
        user.setUsername("admin");
        user.setEmail("test@test.com");
        user.setPassword(new BCryptPasswordEncoder().encode("test123"));
        user.setRole(Roles.SUPER_ADMIN.toString());
        user.setBanned(false);
        user.setConfirmEmail(true);
        userRepository.save(user);
    }


}
