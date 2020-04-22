package com.szb.oauth.permission.repo;

import com.szb.mongodb.SzbMongoRepository;
import com.szb.oauth.permission.domain.UserDoc;

public interface IUserRepo extends SzbMongoRepository<UserDoc, String> {
}

