package com.szb.jpa.repository;

import com.szb.jpa.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AddressRepository extends JpaRepository<Address, String>,
        QuerydslPredicateExecutor<Address> {

}
