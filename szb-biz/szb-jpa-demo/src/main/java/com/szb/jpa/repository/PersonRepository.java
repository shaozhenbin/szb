package com.szb.jpa.repository;

import com.szb.jpa.SzbQuerydslJpaRepository;
import com.szb.jpa.domain.Person;

import java.util.Optional;

public interface PersonRepository extends SzbQuerydslJpaRepository<Person, String> {

    Optional<Person> findByCode(String code);
}
