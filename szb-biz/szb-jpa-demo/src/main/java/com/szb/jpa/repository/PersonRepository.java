package com.szb.jpa.repository;

import com.szb.jpa.SzbQuerydslJpaRepository;
import com.szb.jpa.domain.Person;
import com.szb.jpa.dto.PersonDto;

import javax.persistence.EntityResult;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends SzbQuerydslJpaRepository<Person, String> {

//    Optional<Person> findByCode(String code);

    PersonDto findByCode(String code);


}
