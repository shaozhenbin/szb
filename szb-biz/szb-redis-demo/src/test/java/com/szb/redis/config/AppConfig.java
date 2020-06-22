package com.szb.redis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @ClassName AppConfig
 * @Description TODO
 * @Author szb
 * @Date 2020/1/17 10:32
 * @Version 1.0
 **/
@Configuration
@ComponentScan(basePackages = {"com.szb.*"})
@PropertySource("classpath:application.properties")
public class AppConfig {

}
