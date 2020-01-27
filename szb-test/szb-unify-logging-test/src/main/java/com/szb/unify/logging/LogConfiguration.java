package com.szb.unify.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcConfigurer
 * @Description TODO
 * @Author szb
 * @Date 2019/6/30 22:29
 * @Version 1.0
 **/
@Configuration
@EnableWebMvc
@Slf4j
public class LogConfiguration implements WebMvcConfigurer {

    /**
     * 自己定义的拦截器类
     * @return
     */
    LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.debug("------------registrying---------------");
        registry.addInterceptor(logInterceptor()).addPathPatterns("/**").excludePathPatterns(
                "/swagger-resources/**",
                "/static/**",
                "/csrf/**",
                "/error/**",
                "/webjars/**",
                "/null/swagger-resources/**",
                "/swagger-ui.html");
    }

}
