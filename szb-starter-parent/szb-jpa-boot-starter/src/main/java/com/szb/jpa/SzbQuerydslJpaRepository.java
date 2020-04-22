package com.szb.jpa;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.io.Serializable;

/**
 * @ClassName SzbRepository
 * @Description TODO
 * @Author szb
 * @Date 2020/4/2 13:21
 * @Version 1.0
 **/
public interface SzbQuerydslJpaRepository<T, ID extends Serializable> extends SzbJpaRepository<T, ID>,
        QuerydslPredicateExecutor<T> {

}
