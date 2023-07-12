package com.pragma.user_microservice.domain.usecase;

import com.pragma.user_microservice.application.dto.response.CommonResponseDto;
import com.pragma.user_microservice.domain.api.IUserServicePort;
import com.pragma.user_microservice.domain.exception.DomainException;
import com.pragma.user_microservice.domain.model.CommonResponseModel;
import com.pragma.user_microservice.domain.model.UserModel;
import com.pragma.user_microservice.domain.spi.IUserPersistencePort;
import com.pragma.user_microservice.infrastructure.configuration.Constants;
import com.pragma.user_microservice.infrastructure.exception.NoDataFoundException;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort iUserPersistencePort;

    public UserUseCase(IUserPersistencePort iUserPersistencePort) {
        this.iUserPersistencePort = iUserPersistencePort;
    }

    @Override
    public void saveUser(UserModel userModel) {
        iUserPersistencePort.saveUser(userModel);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return iUserPersistencePort.getAllUsers();
    }

    @Override
    public UserModel getUser(Long userId) {
        return iUserPersistencePort.getUser(userId);
    }

    @Override
    public CommonResponseModel findOwner(Long userId) {
        UserModel userModel = getUser(userId);

        if (userModel == null)
            throw new DomainException(new CommonResponseDto("404","User not found.", false).toString());

        if (!userModel.getRoleEntity().getName().equalsIgnoreCase(Constants.ROLE_ADMIN))
            return new CommonResponseModel("202","The role is not administrator.", false);

        return new CommonResponseModel("200","The role is administrator.", true);
    }

    @Override
    public void updateUser(UserModel userModel) {
        iUserPersistencePort.updateUser(userModel);
    }

    @Override
    public void deleteUser(Long userId) {
        iUserPersistencePort.deleteUser(userId);
    }

}