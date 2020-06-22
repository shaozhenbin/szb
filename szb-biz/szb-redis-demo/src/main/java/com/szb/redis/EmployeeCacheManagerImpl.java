package com.szb.redis;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName EmployeeCacheManagerImpl
 * @Description TODO
 * @Author szb
 * @Date 2020/1/17 10:20
 * @Version 1.0
 **/
@Component
public class EmployeeCacheManagerImpl implements EmployeeCacheManager {

    public static final String TABLE_EMPLOYEE = "TABLE_EMPLOYEE";
    public static final String EMPLOYEE = "EMPLOYEE_";
    private final SzbRedisUtil<Employee> employeeSzbRedisUtil;

    public EmployeeCacheManagerImpl(SzbRedisUtil<Employee> employeeSzbRedisUtil) {
        this.employeeSzbRedisUtil = employeeSzbRedisUtil;
    }

    @Override
    public void cachePersonDetails(Employee employee) {
        employeeSzbRedisUtil.putMap(TABLE_EMPLOYEE, EMPLOYEE + employee.getCode(), employee);
        employeeSzbRedisUtil.setExpire(TABLE_EMPLOYEE, 1, TimeUnit.DAYS);
    }
}
