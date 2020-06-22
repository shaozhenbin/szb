package com.szb.mongo.oauth.repositories;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.szb.mongo.oauth.domain.MongoOAuth2RefreshToken;

@Component
public class MongoOAuth2RefreshTokenRepositoryImpl implements MongoOAuth2RefreshTokenRepositoryBase {

    private MongoTemplate mongoTemplate;

    @Autowired
    public MongoOAuth2RefreshTokenRepositoryImpl(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public MongoOAuth2RefreshToken findByTokenId(final String tokenId) {
        final Query query = Query.query(Criteria.where("tokenId").is(tokenId));
        return mongoTemplate.findOne(query, MongoOAuth2RefreshToken.class);
    }

    @Override
    public boolean deleteByTokenId(String tokenId) {
        final Query query = Query.query(Criteria.where("tokenId").is(tokenId));
        final DeleteResult removeResult = mongoTemplate.remove(query, MongoOAuth2RefreshToken.class);
        return removeResult.getDeletedCount() == 1;
    }
}
