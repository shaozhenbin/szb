package com.szb.mongodb.morphia;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * @ClassName Student
 * @Description TODO
 * @Author szb
 * @Date 2020/1/14 17:54
 * @Version 1.0
 **/
@Entity("students")
public class Student {

    @Id
    private String id;

    private String name;

    private String phone;

}
