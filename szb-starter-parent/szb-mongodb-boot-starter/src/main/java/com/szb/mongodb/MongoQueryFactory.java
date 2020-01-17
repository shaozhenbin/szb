package com.szb.mongodb;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.mongodb.morphia.MorphiaQuery;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MongoQueryFactory
 * @Description TODO
 * @Author szb
 * @Date 2020/1/14 21:28
 * @Version 1.0
 **/
@Configuration
public class MongoQueryFactory<T, QT extends EntityPathBase<T>> {

    private final Morphia morphia;

    private final Datastore datastore;

    public MongoQueryFactory(Morphia morphia, Datastore datastore) {
        this.morphia = morphia;
        this.datastore = datastore;
    }

    public MorphiaQuery<T> selectFrom(QT qt) {
        return new MorphiaQuery<T>(morphia, datastore, qt);
    }
}
