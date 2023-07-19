package com.pragma.user_microservice.application.handler.impl;

import com.pragma.user_microservice.application.dto.request.CustomerRequestDto;
import com.pragma.user_microservice.application.dto.request.EmployeeRequestDto;
import com.pragma.user_microservice.application.dto.request.UserRequestDto;
import com.pragma.user_microservice.application.dto.response.CommonResponseDto;
import com.pragma.user_microservice.application.dto.response.UserResponseDto;
import com.pragma.user_microservice.application.handler.IUserHandler;
import com.pragma.user_microservice.application.mapper.ICommonResponseMapper;
import com.pragma.user_microservice.application.mapper.IEmployeeRequestMapper;
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
    private final IEmployeeRequestMapper iEmployeeRequestMapper;


    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        iUserServicePort.saveUser(iUserRequestMapper.toUSer(userRequestDto));
    }

    @Override
    public void saveEmployee(EmployeeRequestDto employeeRequestDto) {
        iUserServicePort.saveEmployee(iEmployeeRequestMapper.toModel(employeeRequestDto));
    }

    @Override
    public void saveCustomer(CustomerRequestDto customerRequestDto) {
        iUserServicePort.saveCustomer(iUserRequestMapper.toModel(customerRequestDto));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return iUserResponseMapper.toResponseList(iUserServicePort.getAllUsers());
    }

    @Override
    public CommonResponseDto getUser(Long userId) {
        return new CommonResponseDto(true, "200", "OK", iUserResponseMapper.toResponse(iUserServicePort.getUser(userId)));
    }

    @Override
    public CommonResponseDto getUserByUsername(String username) {
        return new CommonResponseDto(true, "200", "OK", iUserResponseMapper.toResponse(iUserServicePort.getUserByUsername(username)));
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