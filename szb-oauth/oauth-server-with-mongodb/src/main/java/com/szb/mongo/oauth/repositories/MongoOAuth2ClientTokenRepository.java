package com.szb.mongo.oauth.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.szb.mongo.oauth.domain.MongoOAuth2ClientToken;

public interface MongoOAuth2ClientTokenRepository extends MongoRepository<MongoOAuth2ClientToken, String>, MongoOAuth2ClientTokenRepositoryBase {
}
