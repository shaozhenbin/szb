package com.szb.jpa.repository;

import com.szb.jpa.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String>
        , QuerydslPredicateExecutor<Person> {

    Optional<Person> findByCode(String code);
}
