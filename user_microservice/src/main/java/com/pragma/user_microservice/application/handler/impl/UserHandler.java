package com.pragma.user_microservice.application.handler.impl;

import com.pragma.user_microservice.application.dto.request.UserRequestDto;
import com.pragma.user_microservice.application.dto.response.CommonResponseDto;
import com.pragma.user_microservice.application.dto.response.UserResponseDto;
import com.pragma.user_microservice.application.handler.IUserHandler;
import com.pragma.user_microservice.application.mapper.ICommonResponseMapper;
import com.pragma.user_microservice.application.mapper.IUserRequestMapper;
import com.pragma.user_microservice.application.mapper.IUserResponseMapper;
import com.pragma.user_microservice.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort iUserServicePort;
    private final IUserRequestMapper iUserRequestMapper;
    private final IUserResponseMapper iUserResponseMapper;
    private final ICommonResponseMapper iCommonResponseMapper;


    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        iUserServicePort.saveUser(iUserRequestMapper.toUSer(userRequestDto));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return iUserResponseMapper.toResponseList(iUserServicePort.getAllUsers());
    }

    @Override
    public UserResponseDto getUser(Long userId) {
        return iUserResponseMapper.toResponse(iUserServicePort.getUser(userId));
    }

    @Override
    public CommonResponseDto findRole(Long userId) {
        return iCommonResponseMapper.toResponse(iUserServicePort.findRole(userId));
    }

    @Override
    public void updateUser(UserRequestDto userRequestDto) {
        iUserServicePort.saveUser(iUserRequestMapper.toUSer(userRequestDto));
    }

    @Override
    public void deleteUser(Long userId) {
        iUserServicePort.deleteUser(userId);
    }

}