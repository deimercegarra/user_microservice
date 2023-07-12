package com.pragma.user_microservice.infrastructure.out.jpa.mapper;


import com.pragma.user_microservice.domain.model.UserModel;
import com.pragma.user_microservice.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {

    UserEntity toEntity(UserModel user);
    UserModel toUserModel(UserEntity UserEntity);
    List<UserModel> toUserModelList(List<UserEntity> userEntityList);

}