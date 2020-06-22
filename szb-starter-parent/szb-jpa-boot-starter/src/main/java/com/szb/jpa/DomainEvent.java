package com.szb.jpa;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName BasicEvent
 * @Description TODO
 * @Author szb
 * @Date 2020/1/28 18:40
 * @Version 1.0
 **/
@Getter
@Setter
public abstract class DomainEvent {

    private String actionType;

}
