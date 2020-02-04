package com.szb.jpa.async.event;

import com.szb.jpa.domain.Person;
import lombok.*;

/**
 * @ClassName PersonEvent
 * @Description TODO
 * @Author szb
 * @Date 2020/1/28 18:05
 * @Version 1.0
 **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonEvent extends DomainEvent {
    private Person person;

}
