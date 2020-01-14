package com.szb.mongodb.domain;

import lombok.*;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;
import java.util.Set;

//@Document(collection = "person")
@Entity("person")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String code;

    private String name;

    private String email;

    @Embedded
    private Set<Address> addressSet;


}
