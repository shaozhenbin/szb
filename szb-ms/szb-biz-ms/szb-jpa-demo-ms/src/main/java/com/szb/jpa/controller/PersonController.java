package com.szb.jpa.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.szb.aop.weblog.WebLog;
import com.szb.jpa.domain.Person;
import com.szb.jpa.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    void save(@RequestBody Person person) {
        personService.save(person);
    }

    @GetMapping("/{code}")
    @WebLog(description = "获取单个用户信息")
    Person findByCode(@PathVariable("code") String code) {
        log.debug("--------获取单个用户信息--------");
        return personService.findByCode(code);
    }

    @GetMapping
    List<Person> getPerson(@RequestParam List<String> codes) {
        if(CollectionUtils.isEmpty(codes)) {
            return Collections.emptyList();
        }
        return personService.getPerson(codes.stream().collect(Collectors.joining(",")));
    }

    @PostMapping("/async")
    void savePerson(@RequestBody Person person) {
        personService.savePerson(person);
    }
}
