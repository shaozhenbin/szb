package com.szb.oauth.permission.service.impl;

import com.szb.oauth.permission.domain.SignDoc;
import com.szb.oauth.permission.repo.ISignRepo;
import com.szb.oauth.permission.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName SignServiceImpl
 * @Description TODO
 * @Author szb
 * @Date 2020/3/28 9:46
 * @Version 1.0
 **/
@Service
@Slf4j
public class SignServiceImpl implements SignService {

    private final ISignRepo signRepo;

    public SignServiceImpl(ISignRepo signRepo) {
        this.signRepo = signRepo;
    }


    @Override
    public void save(SignDoc signDoc) {
        log.debug("------save signDoc------");
        signRepo.save(signDoc);
    }
}
