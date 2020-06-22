package com.szb.mongo.user.repositories;

public interface UserRepositoryBase {

    boolean changePassword(String oldPassword, String newPassword, String username);

}
