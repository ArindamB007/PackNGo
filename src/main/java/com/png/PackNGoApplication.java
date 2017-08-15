package com.png;

import java.io.IOException;
import java.util.Arrays;

import com.png.menu.MenuMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
public class PackNGoApplication extends SpringBootServletInitializer{
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PackNGoApplication.class);
    }
	
    public static void main(String[] args) throws IOException{
    	ConfigurableApplicationContext ctx = SpringApplication.run(PackNGoApplication.class, args); 
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        MenuMapper.getMenu();
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
