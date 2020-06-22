package com.szb.mongo.oauth.repositories;

import java.util.List;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.szb.mongo.oauth.domain.MongoOAuth2AccessToken;

@Component
public class MongoOAuth2AccessTokenRepositoryImpl implements MongoOAuth2AccessTokenRepositoryBase {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public MongoOAuth2AccessTokenRepositoryImpl(final MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public MongoOAuth2AccessToken findByTokenId(final String tokenId) {
		final Query query = Query.query(Criteria.where("tokenId").is(tokenId));
		return mongoTemplate.findOne(query, MongoOAuth2AccessToken.class);
	}

	@Override
	public boolean deleteByTokenId(final String tokenId) {
		final Query query = Query.query(Criteria.where("tokenId").is(tokenId));
		final DeleteResult removeResult = mongoTemplate.remove(query, MongoOAuth2AccessToken.class);
		return removeResult.getDeletedCount() == 1;
	}

	@Override
	public boolean deleteByRefreshTokenId(String refreshTokenId) {
		final Query query = Query.query(Criteria.where("refreshToken").is(refreshTokenId));
		final DeleteResult removeResult = mongoTemplate.remove(query, MongoOAuth2AccessToken.class);
		return removeResult.getDeletedCount() == 1;
	}

	@Override
	public MongoOAuth2AccessToken findByAuthenticationId(String key) {
		final Query query = Query.query(Criteria.where("authenticationId").is(key));
		return mongoTemplate.findOne(query, MongoOAuth2AccessToken.class);
	}

	@Override
	public List<MongoOAuth2AccessToken> findByUsernameAndClientId(final String username,
			final String clientId) {
		final Query query = Query.query(Criteria.where("username").is(username).andOperator(Criteria.where("clientId").is(clientId)));
		return mongoTemplate.find(query, MongoOAuth2AccessToken.class);
	}

	@Override
	public List<MongoOAuth2AccessToken> findByClientId(final String clientId) {
		final Query query = Query.query(Criteria.where("clientId").is(clientId));
		return mongoTemplate.find(query, MongoOAuth2AccessToken.class);
	}
}
