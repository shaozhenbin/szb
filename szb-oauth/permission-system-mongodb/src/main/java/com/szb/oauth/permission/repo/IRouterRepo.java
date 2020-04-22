package com.szb.oauth.permission.repo;

import com.szb.mongodb.SzbMongoRepository;
import com.szb.oauth.permission.domain.RouterDoc;

/**
 * @ClassName IRouterRepo
 * @Description TODO
 * @Author szb
 * @Date 2020/3/28 9:23
 * @Version 1.0
 **/
public interface IRouterRepo extends SzbMongoRepository<RouterDoc, String> {
}
