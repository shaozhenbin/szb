package com.szb.jpa;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.data.repository.core.support.RepositoryFragment;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @ClassName DefaultRepositoryFactory
 * @Description TODO
 * @Author szb
 * @Date 2020/1/16 22:41
 * @Version 1.0
 **/
public  class SzbJpaRepositoryFactory extends JpaRepositoryFactory {

    private final EntityManager entityManager;

    public SzbJpaRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;

    }

    @Override
    protected RepositoryComposition.RepositoryFragments getRepositoryFragments(RepositoryMetadata metadata) {
        RepositoryComposition.RepositoryFragments fragments = super.getRepositoryFragments(metadata);

        //GenericRepositoryImpl
        if (SzbJpaRepositoryImpl.class.isAssignableFrom(
                metadata.getRepositoryInterface())) {

            JpaEntityInformation<?, Serializable> entityInformation =
                    getEntityInformation(metadata.getDomainType());

            Object queryableFragment = getTargetRepositoryViaReflection(
                    SzbJpaRepositoryImpl.class, entityInformation, entityManager);

            fragments = fragments.append(RepositoryFragment.implemented(queryableFragment));
        }
        return fragments;
    }
}
