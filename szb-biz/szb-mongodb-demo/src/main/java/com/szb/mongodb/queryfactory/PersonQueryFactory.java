package com.szb.mongodb.queryfactory;

import com.szb.mongodb.domain.Person;
import com.szb.mongodb.domain.QPerson;
import com.szb.mongodb.MongoQueryFactory;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.stereotype.Component;

/**
 * @ClassName PersonQueryFactory
 * @Description TODO
 * @Author szb
 * @Date 2020/1/14 21:39
 * @Version 1.0
 **/
@Component
public class PersonQueryFactory extends MongoQueryFactory<Person, QPerson> {

    public PersonQueryFactory(Morphia morphia, Datastore datastore) {
        super(morphia, datastore);
    }
}
