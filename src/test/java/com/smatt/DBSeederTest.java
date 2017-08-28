package com.smatt;

import com.smatt.dao.CategoryRepository;
import com.smatt.dao.SectionRepository;
import com.smatt.dao.UserRepository;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Autowired
    JdbcTemplate jdbcTemplate;


}
