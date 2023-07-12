package com.pragma.user_microservice.application.mapper;

import com.pragma.user_microservice.application.dto.response.CommonResponseDto;
import com.pragma.user_microservice.application.dto.response.UserResponseDto;
import com.pragma.user_microservice.domain.model.CommonResponseModel;
import com.pragma.user_microservice.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICommonResponseMapper {
    CommonResponseDto toResponse(CommonResponseModel commonResponseModel);
}
