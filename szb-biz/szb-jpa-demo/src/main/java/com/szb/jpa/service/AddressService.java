package com.szb.jpa.service;

import com.szb.jpa.domain.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    Address findOne(String id);

    List<Address> findByPerson(String personId);
}
