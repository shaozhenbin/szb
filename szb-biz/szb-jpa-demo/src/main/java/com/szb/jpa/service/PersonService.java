package com.szb.jpa.service;

import com.szb.jpa.domain.Person;

import java.util.List;

public interface PersonService {

    void save(Person person);

    Person findByCode(String code);

    List<Person> getPerson(String... code);

    void savePerson(Person person);

    List<Person> getAllCachePerson();
}
