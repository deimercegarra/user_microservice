package com.pragma.user_microservice.domain.spi;

import com.pragma.user_microservice.domain.model.UserModel;

import java.util.List;

public interface IUserPersistencePort {

    void saveUser(UserModel userModel);

    List<UserModel> getAllUsers();

    UserModel getUser(Long userId);

    void updateUser(UserModel userModel);

    void deleteUser(Long userId);

}