package com.pragma.user_microservice.domain.api;

import com.pragma.user_microservice.domain.model.RoleModel;

import java.util.List;

public interface IRoleServicePort {

    void saveRole(RoleModel roleModel);

    List<RoleModel> getAllRoles();

    RoleModel getRole(Long roleId);

    void updateRole(RoleModel roleModel);

    void deleteRole(Long roleId);

}