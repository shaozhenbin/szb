package com.szb.oauth.permission.service;

import com.szb.oauth.permission.domain.UserDoc;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author szb
 * @Date 2020/3/28 9:27
 * @Version 1.0
 **/
public interface UserService {

    //1.创建signkey
    void save(UserDoc userDoc);

}
