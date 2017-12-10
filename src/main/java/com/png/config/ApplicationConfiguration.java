package com.png.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Arindam on 7/5/2017.
 */
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    ApplicationMsgResourceConfiguration applicationMsgResourceConfiguration;
    @Autowired
    WebSecurityConfig webSecurityConfig;
    @Autowired
    RedisConfiguration redisConfiguration;
}
