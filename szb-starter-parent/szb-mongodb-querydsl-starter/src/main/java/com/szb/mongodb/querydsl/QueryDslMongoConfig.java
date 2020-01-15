package com.szb.mongodb.querydsl;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.extern.slf4j.Slf4j;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName queryDslMongodbConfig
 * @Description TODO
 * @Author szb
 * @Date 2020/1/14 11:29
 * @Version 1.0
 **/
@Configuration
@Slf4j
@EnableConfigurationProperties({MongodbProperties.class})
public class QueryDslMongoConfig {

    @Bean
    public MongoClient mongoClient(MongodbProperties mongodbProperties) {
        // mongo开启权限验证
        // Upgrade mongo-java-driver to 3.0.3 and use :-
        // MongoCredential.createScramSha1Credential instead of
        // MongoCredential.createMongoCRCredential
        // MongoCredential createMongoCRCredential =
        // MongoCredential.createScramSha1Credential(mongoConfiguration.getDatabaseUserName(),
        // mongoConfiguration.getAuthenticationDatabase(),mongoConfiguration.getDatabasePassword().toCharArray());
        MongoCredential credential = MongoCredential.createCredential(
                mongodbProperties.getUsername(),
                mongodbProperties.getAuthenticationDatabase(), // 用户和密码定义数据库
                mongodbProperties.getPassword().toCharArray());

        ServerAddress serverAddress = new ServerAddress(mongodbProperties.getHost(), mongodbProperties.getPort());
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

    @Bean(name = "morphia")
    public Morphia morphia(MongodbProperties mongodbProperties) {
        Morphia morphia = new Morphia();
        morphia.mapPackage(mongodbProperties.getMorphiaMapPackage());
        return morphia;
    }

    @Bean(name = "datastore")
    public Datastore datastore(MongoClient mongoClient, Morphia morphia, MongodbProperties mongodbProperties) {
        Datastore datastore = morphia.createDatastore(mongoClient, mongodbProperties.getDatabase());
        datastore.ensureIndexes();
        return datastore;
    }

}





