package com.pragma.user_microservice.domain.usecase;

import com.pragma.user_microservice.domain.api.IRoleServicePort;
import com.pragma.user_microservice.domain.model.RoleModel;

import java.util.List;

public class RoleUseCase implements IRoleServicePort {


    @Override
    public void saveRole(RoleModel RoleModel) {

    }

    @Override
    public List<RoleModel> getAllRoles() {
        return null;
    }

    @Override
    public RoleModel getRole(Long RoleId) {
        return null;
    }

    @Override
    public void updateRole(RoleModel RoleModel) {

    }

    @Override
    public void deleteRole(Long RoleId) {

    }

}