package com.szb.mongodb;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName MongoProperties
 * @Description TODO
 * @Author szb
 * @Date 2020/1/14 15:35
 * @Version 1.0
 **/
@ConfigurationProperties("spring.mongodb")
@Data
public class MongodbProperties {

    private String host = "192.168.252.8";

    private int port = 27017;

    private String username = "szb";

    private String password = "123456";

    private String database = "test";

    private String authenticationDatabase = "admin";

    private String morphiaMapPackage = "";
}
