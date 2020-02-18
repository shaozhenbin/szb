package com.szb.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BasicEntity
 * @Description TODO
 * @Author szb
 * @Date 2020/1/28 18:50
 * @Version 1.0
 **/
@Setter
@Getter
@MappedSuperclass
@JsonIgnoreProperties(value = {"domainEvents"})
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;

    private String groupBy;

    @Transient
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public void addAsyncEvent(DomainEvent domainEvent) {
        this.domainEvents.add(domainEvent);
    }

    @DomainEvents
    public List<DomainEvent> domainEvents(){
        return domainEvents;
    }

    @AfterDomainEventPublication
    public void clearEvents() {
        domainEvents.clear();
    }
}
