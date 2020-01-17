package com.szb.jpa.repository;

import com.szb.jpa.SzbJpaRepository;
import com.szb.jpa.domain.Person;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface PersonRepository extends SzbJpaRepository<Person, String>
    , QuerydslPredicateExecutor<Person> {

    Optional<Person> findByCode(String code);
}
