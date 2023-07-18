package com.pragma.user_microservice.domain.spi;

import com.pragma.user_microservice.domain.model.UserModel;

import java.util.List;

public interface IUserPersistencePort {

    UserModel saveUser(UserModel userModel);

    List<UserModel> getAllUsers();

    UserModel getUser(Long userId);

    UserModel getUserByUsername(String username);

    UserModel updateUser(UserModel userModel);

    void deleteUser(Long userId);

}