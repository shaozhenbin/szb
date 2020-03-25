package com.szb.mongo.oauth.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.szb.mongo.oauth.domain.MongoApproval;

public interface MongoApprovalRepository extends MongoRepository<MongoApproval, String>, MongoApprovalRepositoryBase {
}
