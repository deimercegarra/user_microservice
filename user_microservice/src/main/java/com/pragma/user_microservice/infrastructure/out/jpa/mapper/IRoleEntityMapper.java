package com.pragma.user_microservice.infrastructure.out.jpa.mapper;


import com.pragma.user_microservice.domain.model.RoleModel;
import com.pragma.user_microservice.infrastructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRoleEntityMapper {

    RoleEntity toEntity(RoleModel Role);
    RoleModel toRoleModel(RoleEntity RoleEntity);
    List<RoleModel> toRoleModelList(List<RoleEntity> RoleEntityList);

}