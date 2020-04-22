package com.szb.oauth.permission.service.impl;

import com.szb.oauth.permission.domain.RouterDoc;
import com.szb.oauth.permission.repo.IRouterRepo;
import com.szb.oauth.permission.service.RouterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName RouterServiceImpl
 * @Description TODO
 * @Author szb
 * @Date 2020/3/28 9:45
 * @Version 1.0
 **/
@Service
@Slf4j
public class RouterServiceImpl implements RouterService {

    private final IRouterRepo routerRepo;

    public RouterServiceImpl(IRouterRepo routerRepo) {
        this.routerRepo = routerRepo;
    }


    @Override
    public void save(RouterDoc routerDoc) {
        log.debug("----save routerDoc-----");
        routerRepo.save(routerDoc);
    }
}
