package com.szb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories(basePackages = {"com.szb.mongo.info.repositories",
//		"com.szb.mongo.oauth.repositories", "com.szb.mongo.user.repositories"})
public class AuthServerMain {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerMain.class, args);
	}

}
