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
@ConfigurationProperties("spring.szb.mongodb")
@Data
public class MongodbProperties {

    private String host = "39.108.78.239";

    private int port = 27017;

    private String username = "szb";

    private String password = "123456";

    private String database = "test";

    private String authenticationDatabase = "admin";

    private String morphiaMapPackage = "";
}
