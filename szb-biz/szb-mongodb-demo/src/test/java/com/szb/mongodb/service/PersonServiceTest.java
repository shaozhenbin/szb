package com.szb.mongodb.service;

import com.szb.mongodb.config.AppConfig;
import com.szb.mongodb.config.AppTestConfig;
import com.szb.mongodb.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
@Slf4j
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    public void save() {
        Person person = Person.builder()
                .code("001")
                .name("szb666")
                .email("123456@szb.com")
                .build();

        personService.save(person);
    }

    @Test
    public void query() {
        Person person = personService.getPerson("001");
        log.debug("person -----------> {}", person.toString());
    }

    @Test
    public void findAll() {
        List<Person> list = personService.findAll();
        list.stream().forEach(x -> {
            log.debug("person------------>{}", x.toString());
        });
    }

    @Test
    public void getAddress() {
        personService.getAddress("001");
    }

}
