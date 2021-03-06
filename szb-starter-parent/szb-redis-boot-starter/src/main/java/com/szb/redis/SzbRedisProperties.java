package com.szb.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("spring.szb.redis")
@Data
public class SzbRedisProperties {

    private int port = 6379;
	
    private String host = "192.168.252.8";
    
    private int database = 0;

    private String password = "";
}
