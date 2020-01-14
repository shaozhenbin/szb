package com.szb.mongodb.service;

import com.szb.mongodb.morphia.Student;

/**
 * @ClassName StudentService
 * @Description TODO
 * @Author szb
 * @Date 2020/1/14 17:56
 * @Version 1.0
 **/
public interface StudentService {

    void save(Student student);

    Student findByName(String name);

}
