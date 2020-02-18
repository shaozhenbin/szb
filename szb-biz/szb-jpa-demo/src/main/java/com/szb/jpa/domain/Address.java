package com.szb.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.szb.jpa.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName Address
 * @Description TODO
 * @Author szb
 * @Date 2020/1/5 22:22
 * @Version 1.0
 **/
@Entity
@Table(name = "address")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"person", "domainEvents"})
public class Address extends BaseEntity implements Serializable {

//    @Id
//    @GeneratedValue(generator = "idGenerator")
//    @GenericGenerator(name = "idGenerator", strategy = "uuid")
//    private String id;

    @Column(name = "CITY", length = 20, nullable = false)
    private String city;

    @OneToOne(cascade = CascadeType.PERSIST, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID")
    private Person person;
}
