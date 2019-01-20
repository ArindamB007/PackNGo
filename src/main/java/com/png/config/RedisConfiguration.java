package com.png.config;

/**
 * Created by Arindam on 7/5/2017.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/*@Configuration
@EnableRedisHttpSession
public class RedisConfiguration extends AbstractHttpSessionApplicationInitializer {
    @Bean
    public HttpSessionStrategy httpSessionStrategy(){
        return new HeaderHttpSessionStrategy();
    }
    *//*@Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }*//*
}*/
