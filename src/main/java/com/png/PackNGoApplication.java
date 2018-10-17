package com.png;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.util.Arrays;



@SpringBootApplication
@EnableAsync
public class PackNGoApplication extends SpringBootServletInitializer{
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PackNGoApplication.class);
    }
	
    public static void main(String[] args) throws IOException{
    	ConfigurableApplicationContext ctx = SpringApplication.run(PackNGoApplication.class, args); 
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
