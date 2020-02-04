package com.szb.jpa.cache.manager;

import com.szb.jpa.domain.Person;

import java.util.List;

/**
 * @ClassName personCacheManager
 * @Description TODO
 * @Author szb
 * @Date 2020/1/11 10:03
 * @Version 1.0
 **/
public interface PersonCacheManager {

    void cachePersonDetails(Person person);

    Person getPerson(String code);

    List<Person> getAllCachePerson();
}
