package com.pragma.user_microservice.domain.usecase;

import com.pragma.user_microservice.application.dto.response.CommonResponseDto;
import com.pragma.user_microservice.domain.api.IUserServicePort;
import com.pragma.user_microservice.domain.exception.DomainException;
import com.pragma.user_microservice.domain.model.CommonResponseModel;
import com.pragma.user_microservice.domain.model.UserModel;
import com.pragma.user_microservice.domain.spi.IUserPersistencePort;
import com.pragma.user_microservice.domain.exception.AgeNotAllowedException;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort iUserPersistencePort;

    public UserUseCase(IUserPersistencePort iUserPersistencePort) {
        this.iUserPersistencePort = iUserPersistencePort;
    }

    @Override
    public UserModel saveUser(UserModel userModel) {

        validateAge(userModel);

        return iUserPersistencePort.saveUser(userModel);
    }

    public void validateAge(UserModel userModel) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        LocalDate birthDate = LocalDate.parse(userModel.getBirthDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate().toString(), fmt);
        LocalDate dateNow = LocalDate.now();

        Period period = Period.between(birthDate, dateNow);

        if(period.getYears() < 18){
            throw new AgeNotAllowedException();
        }
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
    public UserModel getUserByUsername(String username) {
        return iUserPersistencePort.getUserByUsername(username);
    }

    @Override
    public CommonResponseModel findRole(Long userId) {
        UserModel userModel = getUser(userId);

        if (userModel == null)
            throw new DomainException(new CommonResponseDto("404","User not found.", false).toString());

        return new CommonResponseModel("200", userModel.getRoleEntity().getName(), true);
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        if(getUser(userModel.getId()) == null)
            throw new DomainException(new CommonResponseDto("404","User not found.", false).toString());

        return iUserPersistencePort.updateUser(userModel);
    }

    @Override
    public CommonResponseModel deleteUser(Long userId) {
        if(getUser(userId) == null)
            throw new DomainException(new CommonResponseDto("404","User not found.", false).toString());

        iUserPersistencePort.deleteUser(userId);

        return new CommonResponseModel("200", "OK.", true);
    }

}