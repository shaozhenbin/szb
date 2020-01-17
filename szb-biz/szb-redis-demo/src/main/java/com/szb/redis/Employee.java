package com.szb.redis;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName employee
 * @Description TODO
 * @Author szb
 * @Date 2020/1/17 10:17
 * @Version 1.0
 **/
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements Serializable{

    private String code;

    private String name;

    private String telephone;

    private String address;

    private String department;


}
