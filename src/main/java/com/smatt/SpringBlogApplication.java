package com.smatt;

import com.smatt.config.StorageProperties;
import com.smatt.dao.PostRepository;
import com.smatt.models.Post;
import com.smatt.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class SpringBlogApplication {

	@Autowired
	PostRepository postRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBlogApplication.class, args);
	}


	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
//			storageService.deleteAll();
			storageService.init();
		};
	}

}
