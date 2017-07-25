package com.smatt.seeders;

import com.smatt.config.Roles;
import com.smatt.dao.CategoryRepository;
import com.smatt.dao.SectionRepository;
import com.smatt.dao.UserRepository;
import com.smatt.models.Category;
import com.smatt.models.Section;
import com.smatt.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by smatt on 29/06/2017.
 */
@Component
public class DatabaseSeeder {

    private Logger logger = Logger.getLogger(DatabaseSeeder.class);
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private SectionRepository sectionRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseSeeder(
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            SectionRepository sectionRepository,
            JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.sectionRepository = sectionRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
        seedCategoryTable();
        seedSectionsTable();
    }

    private void seedCategoryTable() {
        String dc0 = "Technology", dc1 = "General", dc2 = "Life";
        String sql = "SELECT category FROM categories c WHERE c.category IN (\"" + dc0 + "\", \"" + dc1 + "\", \"" + dc2 + "\")";
        List<Category> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(rs == null || rs.size() <= 0) {
            Category c = new Category("Technology");
            Category c2 = new Category("General");
            Category c3 = new Category("Life");
            categoryRepository.save(Arrays.asList(c, c2, c3));
            logger.info("category table seeded");
        }else {
            logger.info("Category Seeding Not Required");
        }
    }

    private void seedSectionsTable() {
        String ds = "General", ds1 = "PHP", ds2 = "JavaScript", ds3 = "Motivation";
        String sql = "SELECT * FROM sections s WHERE s.section IN (\"" + ds + "\", \"" + ds1 + "\", \"" + ds2 + "\"," +
                " \"" + ds3 + "\")";
        List<Section> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(rs == null || rs.size() <= 0) {
            Section s = new Section("Java");
            Section s2 = new Section("PHP");
            Section s3 = new Section("JavaScript");
            Section s4 = new Section("Motivation");
            sectionRepository.save(Arrays.asList(s, s2, s3, s4));
            logger.info("sections table seeded");
        } else {
            logger.info("Sections Seeding Not Required.");
        }
    }

    private void seedUsersTable() {
        String sql = "SELECT username, email FROM users U WHERE U.username = \"admin\" OR " +
                "U.email = \"test@test.com\" LIMIT 1";
        List<User> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(u == null || u.size() <= 0) {
             User user = new User();
             user.setName("Spring Blog");
             user.setUsername("admin");
             user.setEmail("test@test.com");
             user.setPassword(new BCryptPasswordEncoder().encode("test123"));
             user.setRole(Roles.SUPER_ADMIN.toString());
             user.setBanned(false);
             user.setConfirmEmail(true);
             userRepository.save(user);
             logger.info("Users Seeded");
        } else {
            logger.info("Users Seeding Not Required");
        }
    }



}
