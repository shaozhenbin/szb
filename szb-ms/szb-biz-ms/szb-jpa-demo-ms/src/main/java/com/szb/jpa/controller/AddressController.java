package com.szb.jpa.controller;

import com.szb.jpa.service.AddressService;
import com.szb.jpa.domain.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AddressController
 * @Description TODO
 * @Author szb
 * @Date 2020/2/18 15:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    List<Address> findAll() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    Address findOne(@PathVariable("id") String id) {
        return addressService.findOne(id);
    }

    @GetMapping("/person/{personId}")
    List<Address> findByPerson(@PathVariable("personId") String personId) {
        return addressService.findByPerson(personId);
    }
}
