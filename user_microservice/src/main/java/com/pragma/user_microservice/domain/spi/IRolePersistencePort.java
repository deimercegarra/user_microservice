package com.pragma.user_microservice.domain.spi;

import com.pragma.user_microservice.domain.model.RoleModel;

import java.util.List;

public interface IRolePersistencePort {

    void saveRole(RoleModel roleModel);

    List<RoleModel> getAllRoles();

    RoleModel getRole(Long roleId);

    RoleModel getRoleByName(String name);

    void updateRole(RoleModel roleModel);

    void deleteRole(Long roleId);

}