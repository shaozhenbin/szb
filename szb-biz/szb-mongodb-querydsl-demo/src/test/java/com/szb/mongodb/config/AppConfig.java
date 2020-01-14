package com.szb.mongodb.config;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = {"com.szb.*"})
@EnableMongoRepositories(basePackages = {"com.szb.mongodb.repository"})
@Slf4j
public class AppConfig extends AbstractMongoConfiguration {

    @Override
    @Bean
    public MongoClient mongoClient() {

        ServerAddress serverAddress = new ServerAddress("192.168.10.164", 27017);
        log.debug("mongodb连接成功--------------------->{}", "Connect to database successfully");
        return new MongoClient(serverAddress);
    }

    @Override
    protected String getDatabaseName() {
        return "test";
    }

}
