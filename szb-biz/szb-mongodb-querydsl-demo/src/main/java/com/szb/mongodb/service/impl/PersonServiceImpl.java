package com.szb.mongodb.service.impl;

import com.szb.mongodb.domain.Address;
import com.szb.mongodb.domain.Person;
import com.szb.mongodb.domain.QPerson;
import com.szb.mongodb.queryfactory.PersonQueryFactory;
import com.szb.mongodb.repository.IPersonRepository;
import com.szb.mongodb.service.PersonService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PersonServiceImpl
 * @Description TODO
 * @Author szb
 * @Date 2020/1/14 14:33
 * @Version 1.0
 **/
@Service
public class PersonServiceImpl implements PersonService {

    private final IPersonRepository personRepository;
    private final PersonQueryFactory personQueryFactory;
    private final MongoTemplate mongoTemplate;

    public PersonServiceImpl(IPersonRepository personRepository, PersonQueryFactory personQueryFactory, MongoTemplate mongoTemplate) {
        this.personRepository = personRepository;
        this.personQueryFactory = personQueryFactory;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public List<Address> getAddress(String code) {
        return null;
    }

    @Override
    public List<Person> getPerson(String... code) {

        return null;
    }

    @Override
    public Person getPerson(String code) {
        QPerson person = QPerson.person;
        return personQueryFactory
                .selectFrom(person)
                .where(person.code.eq(code))
                .fetchFirst();
    }

    @Override
    public List<Person> findAll() {
        return mongoTemplate.findAll(Person.class);
    }


}
