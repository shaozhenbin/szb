package com.szb.jpa.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sun.scenario.effect.impl.prism.ps.PPSBlend_ADDPeer;
import com.szb.jpa.async.event.PersonEvent;
import com.szb.jpa.async.predicate.PersonPredicate;
import com.szb.jpa.cache.manager.PersonCacheManager;
import com.szb.jpa.domain.Person;
import com.szb.jpa.domain.QPerson;
import com.szb.jpa.repository.PersonRepository;
import com.szb.jpa.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PersonServiceImpl
 * @Description TODO
 * @Author szb
 * @Date 2020/1/11 10:16
 * @Version 1.0
 **/
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    private JPAQueryFactory queryFactory;
    private PersonRepository personRepository;
    private PersonCacheManager personCacheManager;

    public PersonServiceImpl(JPAQueryFactory queryFactory,
                             PersonRepository personRepository,
                             PersonCacheManager personCacheManager) {
        this.queryFactory = queryFactory;
        this.personRepository = personRepository;
        this.personCacheManager = personCacheManager;
    }

    @Override
    public void save(Person person) {
        log.debug("-------save person--------");
        person = personRepository.save(person);
        log.debug("-----------cache person------------");
        personCacheManager.cachePersonDetails(person);
    }

    @Override
    public Person findByCode(String code) {
        QPerson person = QPerson.person;
        Person p = personCacheManager.getPerson(code);
        if(p == null) {
            p = personRepository.findOne(person.code.eq(code))
                    .orElse(null);
        }
        return p;
    }

    @Override
    public List<Person> getPerson(String... codes) {
        log.debug("-----------getPerson------------");
        QPerson person = QPerson.person;
        JPAQuery<Person> query = queryFactory.selectFrom(person);
        BooleanBuilder builder = new BooleanBuilder();
        for (String code : codes) {
            builder.or(person.code.eq(code));
        }
        query.where(builder);
        return query.fetch();
    }

    @Override
    public void savePerson(Person person) {
        log.debug("-------savePerson--------");

        PersonEvent personEvent = PersonEvent.builder()
                .person(person).build();
        personEvent.setActionType(PersonPredicate.ASYNC_EVENT_01);
        log.debug("-------add async event01----------");
        person.addAsyncEvent(personEvent);

        PersonEvent personEvent02 = PersonEvent.builder()
                .person(person).build();
        personEvent02.setActionType(PersonPredicate.ASYNC_EVENT_02);
        log.debug("-------add async event02----------");
        person.addAsyncEvent(personEvent02);

        personRepository.save(person);
        log.debug("-------savePerson--------success");
    }

    @Override
    public List<Person> getAllCachePerson() {
        return personCacheManager.getAllCachePerson();
    }


}
