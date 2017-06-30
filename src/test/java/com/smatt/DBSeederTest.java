package com.smatt;

import com.smatt.dao.CategoryRepository;
import com.smatt.dao.SectionRepository;
import com.smatt.dao.UserRepository;
import com.smatt.models.Section;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

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
