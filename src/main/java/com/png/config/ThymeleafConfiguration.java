package com.png.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.spring4.SpringTemplateEngine;
import java.nio.charset.StandardCharsets;

@Configuration
public class ThymeleafConfiguration {
    @Bean
    public SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlViewTemplateResolver());
        templateEngine.addTemplateResolver(htmlEmailTemplateResolver());
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver htmlViewTemplateResolver(){
        SpringResourceTemplateResolver resourceTemplateResolver = new SpringResourceTemplateResolver();
        resourceTemplateResolver.setCheckExistence(true);
        resourceTemplateResolver.setOrder(0);
        resourceTemplateResolver.setPrefix("classpath:/templates/");
        resourceTemplateResolver.setSuffix(".html");
        resourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        resourceTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return resourceTemplateResolver;
    }

    @Bean
    public SpringResourceTemplateResolver htmlEmailTemplateResolver(){
        SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
        emailTemplateResolver.setOrder(1);
        emailTemplateResolver.setPrefix("classpath:/email-templates/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return emailTemplateResolver;
    }
}
