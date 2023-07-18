package com.pragma.user_microservice.infrastructure.out.jpa.adapter;

import com.pragma.user_microservice.domain.model.RoleModel;
import com.pragma.user_microservice.domain.spi.IRolePersistencePort;
import com.pragma.user_microservice.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.user_microservice.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository iRoleRepository;
    private final IRoleEntityMapper iRoleEntityMapper;

    @Override
    public void saveRole(RoleModel roleModel) {

    }

    @Override
    public List<RoleModel> getAllRoles() {
        return null;
    }

    @Override
    public RoleModel getRole(Long roleId) {
        return iRoleEntityMapper.toRoleModel(iRoleRepository.getReferenceById(roleId));
    }

    @Override
    public RoleModel getRoleByName(String name) {
        return iRoleEntityMapper.toRoleModel(iRoleRepository.findByName(name));
    }

    @Override
    public void updateRole(RoleModel roleModel) {

    }

    @Override
    public void deleteRole(Long roleId) {

    }
}