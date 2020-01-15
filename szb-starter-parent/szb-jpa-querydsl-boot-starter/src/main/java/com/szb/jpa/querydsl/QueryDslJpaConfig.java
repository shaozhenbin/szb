package com.szb.jpa.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @ClassName QueryDslConfig
 * @Description TODO
 * @Author szb
 * @Date 2020/1/11 14:41
 * @Version 1.0
 **/
@Configuration
public class QueryDslJpaConfig {


    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }

}
