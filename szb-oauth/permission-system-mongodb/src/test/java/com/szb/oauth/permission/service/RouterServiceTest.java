package com.szb.oauth.permission.service;

import com.szb.oauth.permission.config.AppConfig;
import com.szb.oauth.permission.domain.MethodDetail;
import com.szb.oauth.permission.domain.RouterDoc;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Slf4j
public class RouterServiceTest {

    @Autowired
    private RouterService routerService;

    @Test
    public void save() {

        Map<String, MethodDetail> methodDetailMap = new TreeMap<>();
        methodDetailMap.put("1", MethodDetail.builder().dataVerify(true).desc("query template value").build());
        methodDetailMap.put("2",MethodDetail.builder().dataVerify(true).desc("create template value").build());
        methodDetailMap.put("4",MethodDetail.builder().dataVerify(true).desc("update template value").build());
        methodDetailMap.put("8",MethodDetail.builder().dataVerify(true).desc("delete template value").build());

        RouterDoc routerDoc = RouterDoc.builder()
                .path("/template")
                .desc("template crud")
                .methodMap(methodDetailMap)
                .build();

        routerService.save(routerDoc);



    }

    @Test
    public void save2() {

        Map<String, MethodDetail> methodDetailMap = new TreeMap<>();
        methodDetailMap.put("1", MethodDetail.builder().dataVerify(true).desc("query cep value").build());
        methodDetailMap.put("2",MethodDetail.builder().dataVerify(true).desc("create cep value").build());
        methodDetailMap.put("4",MethodDetail.builder().dataVerify(true).desc("update cep value").build());
        methodDetailMap.put("8",MethodDetail.builder().dataVerify(true).desc("delete cep value").build());

        RouterDoc routerDoc = RouterDoc.builder()
                .path("/cep")
                .desc("cep crud")
                .methodMap(methodDetailMap)
                .build();

        routerService.save(routerDoc);



    }
}