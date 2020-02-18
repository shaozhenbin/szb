package com.szb.jpa.service.impl;

import com.szb.jpa.domain.Address;
import com.szb.jpa.domain.QAddress;
import com.szb.jpa.repository.AddressRepository;
import com.szb.jpa.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AddressServiceImpl
 * @Description TODO
 * @Author szb
 * @Date 2020/2/18 15:03
 * @Version 1.0
 **/
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findOne(String id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public List<Address> findByPerson(String personId) {

        QAddress address = QAddress.address;
        return IterableUtils.toList(addressRepository.findAll(address.person.id.eq(personId)));
    }
}
