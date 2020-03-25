package com.szb.mongo.user.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.szb.mongo.user.domain.User;

public interface UserRepository extends MongoRepository<User, String>, UserRepositoryBase {

	@Query(value = "{ 'username' : ?0 }")
	User findByUsername(String username);
}
