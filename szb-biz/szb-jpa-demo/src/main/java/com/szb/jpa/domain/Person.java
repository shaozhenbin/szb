package com.szb.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.szb.jpa.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName Person
 * @Description TODO
 * @Author szb
 * @Date 2020/1/5 22:21
 * @Version 1.0
 **/
@Entity
@Table(name = "person")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person extends BaseEntity<String> implements Serializable {

    @Column(name = "CODE", unique = true, nullable = false, length = 32)
    private String code;

    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    /**
     * 一对一关系
     */
    @OneToOne(mappedBy = "person", cascade = {
            CascadeType.MERGE,//级联更新
            CascadeType.PERSIST,//级联持久化实体
            CascadeType.REMOVE,//级联删除
            CascadeType.DETACH//
//            CascadeType.ALL 级联所有权限
    }, fetch = FetchType.LAZY)
    private Address address;

}
