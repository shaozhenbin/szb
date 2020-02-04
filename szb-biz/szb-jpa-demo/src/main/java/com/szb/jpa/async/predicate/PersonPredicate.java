package com.szb.jpa.async.predicate;

import com.szb.jpa.async.event.PersonEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

/**
 * @ClassName PersonPredicate
 * @Description TODO
 * @Author szb
 * @Date 2020/1/28 18:06
 * @Version 1.0
 **/
@Component
@Slf4j
public class PersonPredicate implements Predicate<PersonEvent> {

    public static final String ASYNC_EVENT_01 = "asyncEvent01";

    public static final String ASYNC_EVENT_02 = "asyncEvent02";

    @Override
    public boolean test(PersonEvent personEvent) {
        return false;
    }

    public boolean asyncEvent01(PersonEvent personEvent) {
        log.debug("----------asyncEvent01-------------");
        if (ASYNC_EVENT_01.equals(personEvent.getActionType())) {
            log.debug("-----------pass ASYNC_EVENT_01-------------");
            return true;
        }
        return false;
    }

    public boolean asyncEvent02(PersonEvent personEvent) {
        log.debug("----------asyncEvent02-------------");
        if (ASYNC_EVENT_02.equals(personEvent.getActionType())) {
            log.debug("-----------pass ASYNC_EVENT_02-------------");
            return true;
        }
        return false;
    }
}
