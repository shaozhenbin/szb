package com.szb.jpa.repository;

import com.szb.jpa.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {

    Optional<Person> findByCode(String code);
}
