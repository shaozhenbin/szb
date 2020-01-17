package com.szb.mongodb.repository;

import com.szb.mongodb.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IPersonRepository extends MongoRepository<Person, String>,
        QuerydslPredicateExecutor<Person> {

}
