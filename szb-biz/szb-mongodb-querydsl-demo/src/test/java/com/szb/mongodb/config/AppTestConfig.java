package com.szb.mongodb.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"com.szb.mongodb.*"})
@EnableMongoRepositories(basePackages = {"com.szb.mongodb.repository"})
@Slf4j
public class AppTestConfig extends AbstractMongoConfiguration implements EnvironmentAware {

    private Environment environment;

    @Override
    @Bean
    public MongoClient mongoClient() {
        // mongo开启权限验证
        // Upgrade mongo-java-driver to 3.0.3 and use :-
        // MongoCredential.createScramSha1Credential instead of
        // MongoCredential.createMongoCRCredential
        // MongoCredential createMongoCRCredential =
        // MongoCredential.createScramSha1Credential(mongoConfiguration.getDatabaseUserName(),
        // mongoConfiguration.getAuthenticationDatabase(),mongoConfiguration.getDatabasePassword().toCharArray());

        MongoCredential credential = MongoCredential.createCredential(
                environment.getProperty("spring.data.mongodb.username"),
                environment.getProperty("spring.data.mongodb.authenticationDatabase"), // 用户和密码定义数据库
                environment.getProperty("spring.data.mongodb.password").toCharArray());

        ServerAddress serverAddress = new ServerAddress(environment.getProperty("spring.data.mongodb.host"),
                Integer.parseInt(environment.getProperty("spring.data.mongodb.port")));

        MongoClientOptions.Builder build = new MongoClientOptions.Builder();
//		build.sslEnabled(true).sslInvalidHostNameAllowed(true);
        // build.connectionsPerHost(Integer.valueOf(maxConnect));
        // build.threadsAllowedToBlockForConnectionMultiplier(Integer.valueOf(maxWaitThread));
//		build.connectTimeout(60 * 1000);
//		build.maxWaitTime(60* 1000);
        MongoClientOptions options = build.build();
        log.debug("mongodb连接成功--------------------->{}", "Connect to database successfully");
        return new MongoClient(serverAddress, credential, options);
    }

    @Override
    protected String getDatabaseName() {
        return environment.getProperty("spring.data.mongodb.database");
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
