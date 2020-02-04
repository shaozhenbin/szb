package com.szb.jpa.service;

import com.szb.jpa.config.AppConfig;
import com.szb.jpa.domain.Person;
import com.szb.utils.JsonUtil;
import com.szb.utils.MDCUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ClassName PersonServiceTest
 * @Description TODO
 * @Author szb
 * @Date 2020/1/11 10:22
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Slf4j
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    public void save() {

        Person person = Person.builder()
                .code("611")
                .name("邵振斌")
                .build();

        personService.save(person);
    }
    @Test
    public void savePerson() throws InterruptedException {
        Person person = Person.builder()
                .code("506")
                .name("6666")
                .build();

        MDCUtil.put("requestId", "0002");
        personService.savePerson(person);
        MDCUtil.remove("requestId");

        Thread.sleep(1000);
    }

    @Test
    public void findByCode() {
        Person person = personService.findByCode("611");
        log.debug("person--------> {}", JsonUtil.objectToJson(person));
    }

    @Test
    public void getPerson() {
        List<Person> list = personService.getPerson("309", "209");
        for (Person person : list) {
            log.debug("person--------> {}", JsonUtil.objectToJson(person));
        }
    }

    @Test
    public void getAllCachePerson() {
        List<Person> list = personService.getAllCachePerson();
        for (Person person : list) {
            log.debug("person--------> {}", person.toString());
        }
    }

}
