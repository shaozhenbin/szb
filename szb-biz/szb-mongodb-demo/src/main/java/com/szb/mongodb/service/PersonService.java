package com.szb.mongodb.service;

import com.szb.mongodb.domain.Address;
import com.szb.mongodb.domain.Person;

import java.util.List;

/**
 * @ClassName PersonService
 * @Description TODO
 * @Author szb
 * @Date 2020/1/14 14:33
 * @Version 1.0
 **/
public interface PersonService {

    void save(Person person);

    List<Address> getAddress(String code);

    List<Person> getPerson(String... code);

    Person getPerson(String code);

    List<Person> findAll();
}
