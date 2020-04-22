package com.szb.oauth.permission.repo;

import com.szb.mongodb.SzbMongoRepository;
import com.szb.oauth.permission.domain.SignDoc;

public interface ISignRepo extends SzbMongoRepository<SignDoc, String> {
}
