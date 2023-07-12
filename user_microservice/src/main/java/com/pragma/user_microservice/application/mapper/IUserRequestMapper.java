package com.pragma.user_microservice.application.mapper;

import com.pragma.user_microservice.application.dto.request.UserRequestDto;
import com.pragma.user_microservice.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    UserModel toUSer(UserRequestDto userRequestDto);

}
