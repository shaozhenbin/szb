package com.szb.mongodb;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @ClassName SzbMongoRepositoryImpl
 * @Description TODO
 * @Author szb
 * @Date 2020/2/18 22:17
 * @Version 1.0
 **/
@NoRepositoryBean
public class SzbMongoRepositoryImpl<T, ID extends Serializable> extends
        SimpleMongoRepository<T, ID> implements SzbMongoRepository<T, ID>, Serializable{

    public SzbMongoRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }
}
