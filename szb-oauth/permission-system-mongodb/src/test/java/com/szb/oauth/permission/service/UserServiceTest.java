package com.szb.oauth.permission.service;

import com.szb.oauth.permission.config.AppConfig;
import com.szb.oauth.permission.domain.UserDoc;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void save() {

        Map<String, String> SignKeys1 = new HashMap<>();
        SignKeys1.put(generateSignKey(), "template manager");
        UserDoc u1 = UserDoc.builder()
                .userId("u1")
                .name("u1")
                .signKeyMap(SignKeys1)
                .build();
        userService.save(u1);

        Map<String, String> SignKeys2 = new HashMap<>();
        SignKeys2.put(generateSignKey(), "template manager");
        UserDoc u2 = UserDoc.builder()
                .userId("u2")
                .name("u2")
                .signKeyMap(SignKeys2)
                .build();
        userService.save(u2);

        Map<String, String> SignKeys3 = new HashMap<>();
        SignKeys3.put(generateSignKey(), "cep manager");
        UserDoc u3 = UserDoc.builder()
                .userId("u3")
                .name("u3")
                .signKeyMap(SignKeys3)
                .build();
        userService.save(u3);

    }

    private static String generateSignKey() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}