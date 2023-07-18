package com.pragma.user_microservice.domain.api;

import com.pragma.user_microservice.application.dto.response.CommonResponseDto;
import com.pragma.user_microservice.domain.model.CommonResponseModel;
import com.pragma.user_microservice.domain.model.UserModel;

import java.util.List;

public interface IUserServicePort {

    UserModel saveUser(UserModel userModel);

    List<UserModel> getAllUsers();

    UserModel getUser(Long userId);

    UserModel getUserByUsername(String username);

    CommonResponseModel findRole(Long userId);

    UserModel updateUser(UserModel userModel);

    CommonResponseModel deleteUser(Long userId);

}