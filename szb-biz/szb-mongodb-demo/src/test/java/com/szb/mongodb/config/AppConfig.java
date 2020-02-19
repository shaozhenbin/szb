package com.szb.mongodb.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.szb.mongodb.SzbMongoRepositoryFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = {"com.szb.*"})
@EnableMongoRepositories(basePackages = {"com.szb.mongodb.repository"},
        repositoryFactoryBeanClass = SzbMongoRepositoryFactoryBean.class)
@Slf4j
public class AppConfig extends AbstractMongoClientConfiguration {

    private String connectionUrl = "mongodb://192.168.10.164:27017";

    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(connectionUrl);
    }
}
