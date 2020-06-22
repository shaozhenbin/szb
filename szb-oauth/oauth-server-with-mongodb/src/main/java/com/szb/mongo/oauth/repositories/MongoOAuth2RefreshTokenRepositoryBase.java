package com.szb.mongo.oauth.repositories;

import com.szb.mongo.oauth.domain.MongoOAuth2RefreshToken;

public interface MongoOAuth2RefreshTokenRepositoryBase {
    MongoOAuth2RefreshToken findByTokenId(String tokenId);

    boolean deleteByTokenId(String tokenId);
}
