package com.szb.redis.service;
import com.szb.redis.Employee;
import com.szb.redis.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void save() {
        Employee employee = Employee.builder()
                .code("004")
                .name("szb")
                .address("广东茂名")
                .department("开发部")
                .telephone("12345678911")
                .build();
        employeeService.save(employee);
    }
}