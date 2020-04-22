package com.szb.oauth.permission.service.impl;

import com.szb.oauth.permission.domain.RoleDoc;
import com.szb.oauth.permission.repo.IRoleRepo;
import com.szb.oauth.permission.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Author szb
 * @Date 2020/3/28 9:42
 * @Version 1.0
 **/
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final IRoleRepo roleRepo;

    public RoleServiceImpl(IRoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void save(RoleDoc roleDoc) {
        log.debug("-----save roleDoc------");
        roleRepo.save(roleDoc);
    }
}
