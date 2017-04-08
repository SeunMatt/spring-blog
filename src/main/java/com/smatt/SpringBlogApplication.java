package com.smatt;

import com.smatt.dao.PostRepository;
import com.smatt.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBlogApplication {

	@Autowired
	PostRepository postRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBlogApplication.class, args);
	}

//	@Override
//	public void run(String... strings) throws Exception {
//
//		postRepository.deleteAll();
//
//		postRepository.save(new Post("Test Post One"));
//		postRepository.save(new Post("Test Post Two"));
//		postRepository.save(new Post("Test Post Three"));
//
//		System.out.println("Find All");
//		System.out.println("--------------------------");
//		for(Post p : postRepository.findAll()) {
//			System.out.println(p.toString());
//		}
//
//		System.out.println();
//		System.out.println("FindByPost");
//		System.out.println("------------------------");
//		System.out.println(postRepository.findByPost("Test Post One"));
//
//	}
}
