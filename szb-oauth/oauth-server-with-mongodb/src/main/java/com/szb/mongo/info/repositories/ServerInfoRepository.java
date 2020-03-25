package com.szb.mongo.info.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.szb.mongo.info.domain.ServerInfo;

public interface ServerInfoRepository extends MongoRepository<ServerInfo, String>, ServerInfoRepositoryBase {
}
