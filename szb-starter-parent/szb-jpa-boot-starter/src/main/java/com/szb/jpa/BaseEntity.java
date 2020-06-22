package com.szb.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;
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
@MappedSuperclass
@JsonIgnoreProperties(value = {"domainEvents"})
public abstract class BaseEntity<ID extends Serializable> extends AbstractEntity<ID> {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(
            name = "ID"
    )
    private ID id;
    @Version
    @Column(
            name = "VERSION",
            length = 10
    )
    private Long version;

    @CreationTimestamp()
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "CREATE_DATE",
            insertable = true,
            updatable = false
    )
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "UPDATE_DATE",
            insertable = false,
            updatable = true
    )
    private Date updateDate;

    @CreatedBy
    @Column(
            name = "CREATE_BY",
            length = 30,
            insertable = true,
            updatable = false

    )
    private String createBy;

    @CreatedBy
    @Column(
            name = "GROUP_BY",
            length = 700,
            insertable = true,
            updatable = false
    )
    private String groupBy;

    @LastModifiedBy
    @Column(
            name = "UPDATE_BY",
            length = 30,
            insertable = false,
            updatable = true
    )
    private String updateBy;

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
