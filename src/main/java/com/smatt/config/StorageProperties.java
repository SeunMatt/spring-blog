package com.smatt.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by smatt on 19/04/2017.
 */

@ConfigurationProperties("storage")
public class StorageProperties {

        /**
         * Folder location for storing files
         */



        Logger logger = Logger.getLogger(StorageProperties.class);

        private String location = "upload-dir";

        @Autowired
        public StorageProperties(ResourceLoader resourceLoader) {
//            location = this.getClass().getClassLoader().getResource("").getPath() + "public/uploads";
            if(resourceLoader != null) {
                try {
                    Resource resource = resourceLoader.getResource("/admin/css/sweetalert.css");
                    logger.info("resourceLoader toString " + resource.toString());
                    logger.info("resourceLoader exists " + resource.exists());
//                    logger.info("resourceLoader path " + resource.getFile().getPath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("resourceLoader classpath " + resourceLoader.getResource("classpath:"));
            } else {
                logger.info("resourceLoader is Null");
            }

//            logger.info("resource Path " + this.getClass().getClassLoader().getResource("").getPath());
//            logger.info("resource file " + this.getClass().getClassLoader().getResource("").getFile());
//            logger.info("file " + this.getClass().getClassLoader().getResource("").getPath() + "public/upload-dir");
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
}
