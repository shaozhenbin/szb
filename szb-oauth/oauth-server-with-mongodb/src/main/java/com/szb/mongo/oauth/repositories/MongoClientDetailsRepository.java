package com.szb.mongo.oauth.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.szb.mongo.oauth.domain.MongoClientDetails;

public interface MongoClientDetailsRepository extends MongoRepository<MongoClientDetails, String>, MongoClientDetailsRepositoryBase {
}
