package com.szb.mongo.oauth.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.szb.mongo.oauth.domain.MongoOAuth2AccessToken;

public interface MongoOAuth2AccessTokenRepository extends MongoRepository<MongoOAuth2AccessToken, String>, MongoOAuth2AccessTokenRepositoryBase {

}
