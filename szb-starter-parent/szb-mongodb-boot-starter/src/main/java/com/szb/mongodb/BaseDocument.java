package com.szb.mongodb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@JsonIgnoreProperties(value = {"domainEvents"})
public abstract class BaseDocument<ID extends Serializable> extends AbstractDocument<ID> {

    @Id
    private ID id;

    @Version
    private Long version;

    @CreatedDate
    private Date createDate;

    @CreatedBy
    private String createBy;

    @CreatedBy
    private String groupBy;

    @LastModifiedDate
    private Date updateDate;

    @LastModifiedBy
    private String updateBy;

    public BaseDocument() {
        super();
    }

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
