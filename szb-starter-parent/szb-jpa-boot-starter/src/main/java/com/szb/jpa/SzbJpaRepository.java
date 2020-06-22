package com.szb.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @ClassName GenericRepository
 * @Description TODO
 * @Author szb
 * @Date 2020/1/16 22:29
 * @Version 1.0
 **/
@NoRepositoryBean
public interface SzbJpaRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>{
}
