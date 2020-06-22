package com.szb.redis.service;

import com.szb.redis.Employee;
import com.szb.redis.EmployeeCacheManager;
import org.springframework.stereotype.Service;

/**
 * @ClassName EmployeeServiceImpl
 * @Description TODO
 * @Author szb
 * @Date 2020/1/17 10:47
 * @Version 1.0
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeCacheManager employeeCacheManager;

    public EmployeeServiceImpl(EmployeeCacheManager employeeCacheManager) {
        this.employeeCacheManager = employeeCacheManager;
    }

    @Override
    public void save(Employee employee) {
        employeeCacheManager.cachePersonDetails(employee);
    }
}
