package com.szb.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @ClassName DefaultRepositoryFactoryBean
 * @Description TODO
 * @Author szb
 * @Date 2020/1/16 22:39
 * @Version 1.0
 **/
public class SzbJpaRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
        extends JpaRepositoryFactoryBean<T, S, ID> {

    public SzbJpaRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    /**
     * Returns a {@link RepositoryFactorySupport}.
     *
     * @param entityManager
     * @return
     */
    protected RepositoryFactorySupport createRepositoryFactory(
            EntityManager entityManager) {

        return new SzbJpaRepositoryFactory(entityManager);
    }
}


