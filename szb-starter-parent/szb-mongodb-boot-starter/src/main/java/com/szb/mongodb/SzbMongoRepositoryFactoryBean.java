package com.szb.mongodb;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

/**
 * @ClassName SzbMongoRepositoryFactoryBean
 * @Description TODO
 * @Author szb
 * @Date 2020/2/18 22:39
 * @Version 1.0
 **/
public class SzbMongoRepositoryFactoryBean<T extends MongoRepository<S, ID>, S, ID extends Serializable>
        extends MongoRepositoryFactoryBean<T, S, ID>  {

    private final MongoOperations mongoOperations;

    public SzbMongoRepositoryFactoryBean(Class<? extends T> repositoryInterface, MongoOperations mongoOperations) {
        super(repositoryInterface);
        this.mongoOperations = mongoOperations;
    }

    protected RepositoryFactorySupport createRepositoryFactory() {
        SzbMongoRepositoryFactory szbMongoRepositoryFactory =
                new SzbMongoRepositoryFactory(mongoOperations);
//        szbMongoRepositoryFactory.addRepositoryProxyPostProcessor();
        return szbMongoRepositoryFactory;
    }
}
