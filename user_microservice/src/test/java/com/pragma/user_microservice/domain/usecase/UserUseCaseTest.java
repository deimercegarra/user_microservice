package com.pragma.user_microservice.domain.usecase;

import com.pragma.user_microservice.domain.exception.DomainException;
import com.pragma.user_microservice.domain.model.CommonResponseModel;
import com.pragma.user_microservice.domain.model.RoleModel;
import com.pragma.user_microservice.domain.model.UserModel;
import com.pragma.user_microservice.domain.spi.IUserPersistencePort;
import com.pragma.user_microservice.domain.exception.AgeNotAllowedException;
import com.pragma.user_microservice.infrastructure.out.jpa.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserUseCaseTest {
    private IUserPersistencePort iUserPersistencePort;
    private UserUseCase iUserServicePort;

    private UserModel userModel;
    private RoleModel roleModel;

    @BeforeEach
    void setUp(){

        iUserPersistencePort = mock(IUserPersistencePort.class);
        iUserServicePort = new UserUseCase(iUserPersistencePort);
        MockitoAnnotations.initMocks(this);

        roleModel = new RoleModel();
        roleModel.setId(1L);

        userModel = new UserModel();
        //userModel.setId(5L);
        userModel.setName("Deimer");
        userModel.setLastName("Cegarra");
        userModel.setDocumentNumber("1004911853");
        userModel.setPhone("+573022859058");
        userModel.setBirthDate(new Date("21/05/2000")); //dd-mm-yyyy
        userModel.setEmail("joel@gmail.com");
        userModel.setPassword("fdgcbhh");
        userModel.setRoleEntity(roleModel);
    }

    @Test
    void AgeNotAllowedExceptionTest() {
        UserModel userModel;
        RoleModel roleModel;
        roleModel = new RoleModel();
        roleModel.setId(1L);

        userModel = new UserModel();
        //userModel.setId(5L);
        userModel.setName("Deimer");
        userModel.setLastName("Cegarra");
        userModel.setDocumentNumber("1004911853");
        userModel.setPhone("+573022859058");
        userModel.setBirthDate(new Date("21/05/2020")); //dd-mm-yyyy
        userModel.setEmail("joel@gmail.com");
        userModel.setPassword("fdgcbhh");
        userModel.setRoleEntity(roleModel);

        doThrow(AgeNotAllowedException.class).when(iUserPersistencePort).saveUser(userModel);
        assertThrows(AgeNotAllowedException.class, () -> iUserServicePort.saveUser(userModel));
    }

    @Test
    void saveUserTest() {
        //doNothing().when(iUserPersistencePort).saveUser(userModel);

        //iUserServicePort.saveUser(userModel);

        //assertTrue(iUserServicePort.validateAge(userModel));

        when(iUserPersistencePort.saveUser(any(UserModel.class))).thenReturn(userModel);
        assertNotNull(iUserServicePort.saveUser(userModel));

        //verify(iUserPersistencePort).saveUser(userModel);
    }

    @Test
    void getAllUsersTest() {
        List<UserModel> list = new ArrayList<>();

        when(iUserPersistencePort.getAllUsers()).thenReturn(list);
        assertNotNull(iUserServicePort.getAllUsers());
    }

    @Test
    void getUserTest() {
        when(iUserPersistencePort.getUser(1L)).thenReturn(userModel);
        assertNotNull(iUserServicePort.getUser(1L));
    }

    @Test
    void findRoleTest() {
        when(iUserPersistencePort.getUser(1L)).thenReturn(userModel);
        assertNotNull(iUserServicePort.findRole(1L));
    }

    @Test
    void findRoleDomainExceptionTest() {
        doThrow(DomainException.class).when(iUserPersistencePort).getUser(900L);
        assertThrows(DomainException.class, () -> iUserServicePort.findRole(null));
    }

    @Test
    void updateUserTest() {
        /*iUserPersistencePort.updateUser(userModel);
        doNothing().when(iUserPersistencePort).updateUser(userModel);
        verify(iUserPersistencePort).updateUser(userModel);*/

        //userModel.setId(1L);

        //when(iUserPersistencePort.updateUser(any(UserModel.class))).thenReturn(userModel);
        //assertNotNull(iUserServicePort.updateUser(userModel));
        //verify(iUserPersistencePort, times(1)).updateUser(userModel);

        /*when(iUserPersistencePort.saveUser(any(UserModel.class))).thenReturn(userModel);
        assertNotNull(iUserServicePort.saveUser(userModel));*/
    }

    @Test
    void updateUserDomainExceptionTest() {
        doThrow(DomainException.class).when(iUserPersistencePort).updateUser(userModel);
        assertThrows(DomainException.class, () -> iUserServicePort.updateUser(userModel));
    }

    @Test
    void deleteUserTest() {
        iUserPersistencePort.deleteUser(1L);
        verify(iUserPersistencePort, times(1)).deleteUser(1L);

        //assertNotNull(iUserServicePort.deleteUser(1L));
    }

    @Test
    void deleteUserDomainExceptionTest() {
        doThrow(DomainException.class).when(iUserPersistencePort).deleteUser(1L);
        assertThrows(DomainException.class, () -> iUserServicePort.deleteUser(1L));
    }

}