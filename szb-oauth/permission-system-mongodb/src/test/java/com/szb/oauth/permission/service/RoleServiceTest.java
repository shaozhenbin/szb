package com.szb.oauth.permission.service;

import com.szb.oauth.permission.config.AppConfig;
import com.szb.oauth.permission.domain.PathMethod;
import com.szb.oauth.permission.domain.RoleDoc;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Slf4j
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void save() {

        Map<String, Boolean> pathsDataVerify = new HashMap<>();
        pathsDataVerify.put("1_/template", true);
        pathsDataVerify.put("2_/template", true);
        pathsDataVerify.put("4_/template", true);
        pathsDataVerify.put("8_/template", true);

        List<PathMethod> pathMethods = new ArrayList<>();
        PathMethod pathMethod = PathMethod.builder()
                .path("/template")
                .methodInt(15)
                .build();
        pathMethods.add(pathMethod);

        RoleDoc roleDoc = RoleDoc.builder()
                .roleName("template manager")
                .isDefault(false)
                .desc("template manager")
                .type(1)
                .pathsDataVerify(pathsDataVerify)
                .pathMethods(pathMethods)
                .build();
        roleService.save(roleDoc);

    }

    @Test
    public void save2() {

        Map<String, Boolean> pathsDataVerify = new HashMap<>();
        pathsDataVerify.put("1_/template", true);

        List<PathMethod> pathMethods = new ArrayList<>();
        PathMethod pathMethod = PathMethod.builder()
                .path("/template")
                .methodInt(1)
                .build();
        pathMethods.add(pathMethod);

        RoleDoc roleDoc = RoleDoc.builder()
                .roleName("template readOnly")
                .isDefault(false)
                .desc("template readOnly")
                .type(1)
                .pathsDataVerify(pathsDataVerify)
                .pathMethods(pathMethods)
                .build();
        roleService.save(roleDoc);

    }

    @Test
    public void save3() {

        Map<String, Boolean> pathsDataVerify = new HashMap<>();
        pathsDataVerify.put("1_/cep", true);
        pathsDataVerify.put("2_/cep", true);
        pathsDataVerify.put("4_/cep", true);


        List<PathMethod> pathMethods = new ArrayList<>();
        PathMethod pathMethod = PathMethod.builder()
                .path("/cep")
                .methodInt(7)
                .build();
        pathMethods.add(pathMethod);

        RoleDoc roleDoc = RoleDoc.builder()
                .roleName("cep manager")
                .isDefault(false)
                .desc("cep manager")
                .type(1)
                .pathsDataVerify(pathsDataVerify)
                .pathMethods(pathMethods)
                .build();
        roleService.save(roleDoc);

    }

    @Test
    public void save4() {

        Map<String, Boolean> pathsDataVerify = new HashMap<>();
        pathsDataVerify.put("1_/template", true);
        pathsDataVerify.put("2_/template", true);
        pathsDataVerify.put("4_/template", true);
        pathsDataVerify.put("8_/template", true);

        pathsDataVerify.put("1_/cep", true);
        pathsDataVerify.put("2_/cep", true);
        pathsDataVerify.put("4_/cep", true);
        pathsDataVerify.put("8_/cep", true);

        List<PathMethod> pathMethods = new ArrayList<>();
        PathMethod pathMethod = PathMethod.builder()
                .path("/template")
                .methodInt(15)
                .build();
        pathMethods.add(pathMethod);

        PathMethod pathMethod2 = PathMethod.builder()
                .path("/cep")
                .methodInt(15)
                .build();
        pathMethods.add(pathMethod2);

        RoleDoc roleDoc = RoleDoc.builder()
                .roleName("template&cep manager")
                .isDefault(false)
                .desc("template&cep manager")
                .type(1)
                .pathsDataVerify(pathsDataVerify)
                .pathMethods(pathMethods)
                .build();



        roleService.save(roleDoc);

    }
}