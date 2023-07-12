package com.pragma.user_microservice.application.mapper;

import com.pragma.user_microservice.application.dto.response.UserResponseDto;
import com.pragma.user_microservice.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {
    UserResponseDto toResponse(UserModel userModel);

    List<UserResponseDto> toResponseList(List<UserModel> userModelList);
}
