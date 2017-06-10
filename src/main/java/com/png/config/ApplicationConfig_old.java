package com.png.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*@Configuration
@EnableWebMvc
public class ApplicationConfig_old extends WebMvcConfigurerAdapter {
	@Bean
	  public ViewResolver internalResourceViewResolver() {
	      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	      //resolver.setPrefix("/views/");
	      //resolver.setApplicationContext(applicationContext);
	      //resolver.setViewNames("/views/*");
	      resolver.setViewNames(new String[] {"views/*"});
	      resolver.setOrder(Ordered.HIGHEST_PRECEDENCE +2);
	      resolver.setSuffix(".html");
	      return resolver;
	  }
	
	@Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }  

}*/
