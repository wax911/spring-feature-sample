package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@SpringBootApplication
@ConfigurationPropertiesScan("com.example.demo.core.settings")
public class SpringFeatureSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFeatureSampleApplication.class, args);
	}
}
