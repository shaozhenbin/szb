package com.szb.mongodb.service.impl;

import com.szb.mongodb.morphia.Student;
import com.szb.mongodb.service.StudentService;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * @ClassName StudentServiceImpl
 * @Description TODO
 * @Author szb
 * @Date 2020/1/14 17:58
 * @Version 1.0
 **/
public class StudentServiceImpl implements StudentService {


    private final Morphia morphia;
    private final Datastore datastore;

    public StudentServiceImpl(Morphia morphia, Datastore datastore) {
        this.morphia = morphia;
        this.datastore = datastore;
    }


    @Override
    public void save(Student student) {

    }

    @Override
    public Student findByName(String name) {
        return null;
    }
}
