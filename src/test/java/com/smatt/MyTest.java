package com.smatt;

import com.smatt.models.Post;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by smatt on 21/04/2017.
 */
public class MyTest {

    Logger logger = Logger.getLogger(SpringBlogApplicationTests.class);

    public MyTest() {}

    @Test
    public void testRegex() {
        String filename = "this.is.a.file.jpg";
        logger.info("extension = " + filename.substring(filename.lastIndexOf(".")));

        Post post = new Post();
        post.setCoverPic("sample.jpg");

        logger.info("post = " + post.toString());
    }


}
