package com.szb.mongodb.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.szb.mongodb.domain.QPerson;
import com.szb.mongodb.queryfactory.PersonQueryFactory;
import com.szb.mongodb.repository.IPersonRepository;
import com.szb.mongodb.service.PersonService;
import com.szb.mongodb.domain.Address;
import com.szb.mongodb.domain.Person;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

        QPerson person = QPerson.person;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(person.code.eq(code));
        Person p = personQueryFactory.selectFrom(person).where(booleanBuilder)
                .fetchFirst(person.name, person.addressSet);
        log.debug("p ----------> {}", p.toString());
        return null;
    }

    @Override
    public List<Person> getPerson(String... code) {

        return null;
    }

    @Override
    public Person getPerson(String code) {
        QPerson person = QPerson.person;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(person.code.eq(code));
        return personQueryFactory
                .selectFrom(person)
                .where(booleanBuilder)
                .fetchFirst(person.code, person.name);
    }

    @Override
    public List<Person> findAll() {
        return mongoTemplate.findAll(Person.class);
    }


}
