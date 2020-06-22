package com.szb.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

public interface SzbMongoRepository<T, ID extends Serializable>
        extends MongoRepository<T, ID> {
}
