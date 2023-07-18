package com.pragma.user_microservice.infrastructure.out.jpa.adapter;

import com.pragma.user_microservice.domain.model.EmployeeModel;
import com.pragma.user_microservice.domain.model.RoleModel;
import com.pragma.user_microservice.domain.model.UserModel;
import com.pragma.user_microservice.domain.spi.IRolePersistencePort;
import com.pragma.user_microservice.domain.spi.IUserPersistencePort;
import com.pragma.user_microservice.infrastructure.configuration.Constants;
import com.pragma.user_microservice.infrastructure.exception.NoDataFoundException;
import com.pragma.user_microservice.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.user_microservice.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.user_microservice.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository iUserRepository;
    private final IUserEntityMapper iUserEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final IRolePersistencePort iRolePersistencePort;

    @Override
    public UserModel saveUser(UserModel userModel) {

        userModel.setPassword(passwordEncoder.encode((userModel.getPassword())));

        RoleModel roleModel = iRolePersistencePort.getRoleByName(Constants.ROLE_OWNER);
        userModel.setRoleEntity(roleModel);
        UserEntity userEntity = iUserEntityMapper.toEntity(userModel);

        return iUserEntityMapper.toUserModel(iUserRepository.save(userEntity));
    }

    @Override
    public UserModel saveEmployee(EmployeeModel employeeModel) {

        employeeModel.setPassword(passwordEncoder.encode((employeeModel.getPassword())));

        RoleModel roleModel = iRolePersistencePort.getRoleByName(Constants.ROLE_EMPLOYEE);
        employeeModel.setRoleEntity(roleModel);
        UserEntity userEntity = iUserEntityMapper.toEntity(employeeModel);

        return iUserEntityMapper.toUserModel(iUserRepository.save(userEntity));
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserEntity> entityList = iUserRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return iUserEntityMapper.toUserModelList(entityList);
    }

    @Override
    public UserModel getUser(Long userId) {
        return iUserEntityMapper.toUserModel(iUserRepository.findById(userId)
                .orElseThrow(NoDataFoundException::new));
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return iUserEntityMapper.toUserModel(iUserRepository.findByEmail(username));
    }


    @Override
    public UserModel updateUser(UserModel userModel) {
        return iUserEntityMapper.toUserModel(iUserRepository.save(iUserEntityMapper.toEntity(userModel)));
    }

    @Override
    public void deleteUser(Long userId) {
        iUserRepository.deleteById(userId);
    }

}