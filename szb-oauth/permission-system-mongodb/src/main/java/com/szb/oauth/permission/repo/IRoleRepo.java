package com.szb.oauth.permission.repo;

import com.szb.mongodb.SzbMongoRepository;
import com.szb.oauth.permission.domain.RoleDoc;

/**
 * @ClassName IRoleRepo
 * @Description TODO
 * @Author szb
 * @Date 2020/3/28 9:19
 * @Version 1.0
 **/
public interface IRoleRepo extends SzbMongoRepository<RoleDoc, String> {
}
