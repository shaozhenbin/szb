package com.szb.jpa.async.processor;

import com.szb.jpa.async.event.PersonEvent;
import com.szb.jpa.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @ClassName PersonProcessor
 * @Description TODO
 * @Author szb
 * @Date 2020/1/28 18:11
 * @Version 1.0
 **/
@Component
@Slf4j
public class PersonProcessor {


    @Async
    @TransactionalEventListener(condition = "@personPredicate.asyncEvent01(#personEvent)")
    void asyncEvent01(PersonEvent personEvent) {
        log.debug("----------begin asyncEvent01----------------");
        Person person = personEvent.getPerson();
        String actionType = personEvent.getActionType();
        log.debug("actionType -----------> {}", actionType);
        log.debug("----------success asyncEvent01----------------");
    }


    @Async
    @TransactionalEventListener(condition = "@personPredicate.asyncEvent02(#personEvent)")
    void asyncEvent02(PersonEvent personEvent) {
        log.debug("----------begin asyncEvent02----------------");
        Person person = personEvent.getPerson();
        String actionType = personEvent.getActionType();
        log.debug("actionType -----------> {}", actionType);
        log.debug("----------success asyncEvent02----------------");
    }
}
