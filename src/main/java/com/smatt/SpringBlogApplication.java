package com.smatt;

import com.smatt.addons.LocalDateTimeTemplateFormatFactory;
import com.smatt.config.StorageProperties;
import com.smatt.dao.PostRepository;
import com.smatt.models.Post;
import com.smatt.service.StorageService;
import freemarker.core.TemplateDateFormat;
import freemarker.core.TemplateDateFormatFactory;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.ObjectWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties({StorageProperties.class})
public class SpringBlogApplication {

	@Autowired
    Configuration cfg;


	public static void main(String[] args) {
		SpringApplication.run(SpringBlogApplication.class, args);
	}


	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
//			storageService.deleteAll();
			storageService.init();

			//format the template
            Map<String, TemplateDateFormatFactory> customDateFormats = new HashMap<>();
            customDateFormats.put("localdatetime", LocalDateTimeTemplateFormatFactory.INSTANCE);
            cfg.setCustomDateFormats(customDateFormats);
			DefaultObjectWrapper objectWrapper = new DefaultObjectWrapper();
			objectWrapper.setIterableSupport(true);
            cfg.setObjectWrapper(objectWrapper);
		};
	}

}
