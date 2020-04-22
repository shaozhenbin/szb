package com.szb.oauth.permission.service.impl;

import com.szb.oauth.permission.domain.UserDoc;
import com.szb.oauth.permission.repo.IUserRepo;
import com.szb.oauth.permission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author szb
 * @Date 2020/3/28 9:39
 * @Version 1.0
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final IUserRepo userRepo;

    public UserServiceImpl(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void save(UserDoc userDoc) {
        log.debug("--------save userDoc----------");
        userRepo.save(userDoc);
    }
}
