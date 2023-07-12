package com.pragma.user_microservice.infrastructure.out.jpa.adapter;

import com.pragma.user_microservice.domain.model.RoleModel;
import com.pragma.user_microservice.domain.model.UserModel;
import com.pragma.user_microservice.domain.spi.IRolePersistencePort;
import com.pragma.user_microservice.domain.spi.IUserPersistencePort;
import com.pragma.user_microservice.infrastructure.configuration.Constants;
import com.pragma.user_microservice.infrastructure.exception.AgeNotAllowedException;
import com.pragma.user_microservice.infrastructure.exception.NoDataFoundException;
import com.pragma.user_microservice.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.user_microservice.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.user_microservice.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.user_microservice.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.user_microservice.infrastructure.out.jpa.repository.IRoleRepository;
import com.pragma.user_microservice.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository iUserRepository;
    private final IUserEntityMapper iUserEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final IRolePersistencePort iRolePersistencePort;

    @Override
    public void saveUser(UserModel userModel) {

        userModel.setPassword(encodePassword(userModel.getPassword()));

        if (!validateAge(userModel))
            throw new AgeNotAllowedException();

        RoleModel roleModel = iRolePersistencePort.getRoleByName(Constants.ROLE_OWNER);
        userModel.setRoleEntity(roleModel);
        UserEntity userEntity = iUserEntityMapper.toEntity(userModel);

        iUserRepository.save(userEntity);
    }

    public String encodePassword(String password) {
        String passwordEncode;
        passwordEncode = passwordEncoder.encode(password);
        return passwordEncode;
    }

    public boolean validateAge(UserModel userModel) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        LocalDate birthDate = LocalDate.parse(userModel.getBirthDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate().toString(), fmt);
        LocalDate dateNow = LocalDate.now();

        Period period = Period.between(birthDate, dateNow);

        if(period.getYears() >= 18){
            return true;
        }
        return false;
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
    public void updateUser(UserModel userModel) {
        iUserRepository.save(iUserEntityMapper.toEntity(userModel));
    }

    @Override
    public void deleteUser(Long userId) {
        iUserRepository.deleteById(userId);
    }
}