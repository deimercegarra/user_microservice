package com.pragma.user_microservice.application.handler;

import com.pragma.user_microservice.application.dto.request.UserRequestDto;
import com.pragma.user_microservice.application.dto.response.CommonResponseDto;
import com.pragma.user_microservice.application.dto.response.UserResponseDto;

import java.util.List;

public interface IUserHandler {

    public void saveUser(UserRequestDto userRequestDto);

    public List<UserResponseDto> getAllUsers();

    public CommonResponseDto getUser(Long userId);

    public CommonResponseDto getUserByUsername(String username);

    public CommonResponseDto findRole(Long userId);

    public void updateUser(UserRequestDto userRequestDto);

    public void deleteUser(Long userId);

}