package com.szb.mongodb;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.data.repository.core.support.RepositoryFragment;

import java.io.Serializable;

/**
 * @ClassName SzbMongoRepositoryFactory
 * @Description TODO
 * @Author szb
 * @Date 2020/2/18 22:25
 * @Version 1.0
 **/
public class SzbMongoRepositoryFactory extends MongoRepositoryFactory {

    public SzbMongoRepositoryFactory(MongoOperations mongoOperations) {
        super(mongoOperations);
    }

    protected RepositoryComposition.RepositoryFragments getRepositoryFragments(RepositoryMetadata metadata) {
        RepositoryComposition.RepositoryFragments fragments = super.getRepositoryFragments(metadata);;
        if (SzbMongoRepositoryImpl.class.isAssignableFrom(
                metadata.getRepositoryInterface())) {

            MongoEntityInformation<?, Serializable> entityInformation =
                    getEntityInformation(metadata.getDomainType());

            Object queryableFragment = getTargetRepositoryViaReflection(
                    SzbMongoRepositoryImpl.class, entityInformation);

            fragments = fragments.append(RepositoryFragment.implemented(queryableFragment));
        }

        return fragments;
    }
}
