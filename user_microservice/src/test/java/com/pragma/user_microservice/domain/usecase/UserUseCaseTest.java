package com.pragma.user_microservice.domain.usecase;

import com.pragma.user_microservice.domain.exception.DomainException;
import com.pragma.user_microservice.domain.model.CommonResponseModel;
import com.pragma.user_microservice.domain.model.EmployeeModel;
import com.pragma.user_microservice.domain.model.RoleModel;
import com.pragma.user_microservice.domain.model.UserModel;
import com.pragma.user_microservice.domain.spi.IRolePersistencePort;
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
    private IRolePersistencePort iRolePersistencePort;
    private UserUseCase iUserServicePort;

    private UserModel userModel;
    private RoleModel roleModel;

    @BeforeEach
    void setUp(){

        iUserPersistencePort = mock(IUserPersistencePort.class);
        iRolePersistencePort = mock(IRolePersistencePort.class);
        iUserServicePort = new UserUseCase(iUserPersistencePort, iRolePersistencePort);
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
        when(iUserPersistencePort.saveUser(any(UserModel.class))).thenReturn(userModel);
        assertNotNull(iUserServicePort.saveUser(userModel));
    }

    @Test
    void saveEmployeeTest() {
        roleModel = new RoleModel();
        roleModel.setId(2L);
        roleModel.setDescription("Propietario");
        roleModel.setName("Owner");

        EmployeeModel userEmployeeModel = new EmployeeModel();

        userEmployeeModel.setName("Deimer");
        userEmployeeModel.setLastName("Cegarra");
        userEmployeeModel.setDocumentNumber("1004911853");
        userEmployeeModel.setPhone("+573022859058");
        userEmployeeModel.setBirthDate(new Date("21/05/2020")); //dd-mm-yyyy
        userEmployeeModel.setEmail("joel@gmail.com");
        userEmployeeModel.setPassword("fdgcbhh");
        userEmployeeModel.setIdRol(2L);

        when(iRolePersistencePort.getRole(userEmployeeModel.getIdRol())).thenReturn(roleModel);
        when(iUserPersistencePort.saveEmployee(userEmployeeModel)).thenReturn(userModel);
        assertNotNull(iUserServicePort.saveEmployee(userEmployeeModel));
    }

    @Test
    void saveEmployeeDomainExceptionTest() {
        roleModel = new RoleModel();
        roleModel.setId(2L);
        roleModel.setDescription("Propietario");
        roleModel.setName("Owner");

        EmployeeModel userEmployeeModel = new EmployeeModel();

        userEmployeeModel.setName("Deimer");
        userEmployeeModel.setLastName("Cegarra");
        userEmployeeModel.setDocumentNumber("1004911853");
        userEmployeeModel.setPhone("+573022859058");
        userEmployeeModel.setBirthDate(new Date("21/05/2020")); //dd-mm-yyyy
        userEmployeeModel.setEmail("joel@gmail.com");
        userEmployeeModel.setPassword("fdgcbhh");
        userEmployeeModel.setIdRol(2L);

        when(iRolePersistencePort.getRole(userEmployeeModel.getIdRol())).thenReturn(roleModel);
        when(iUserServicePort.saveEmployee(userEmployeeModel)).thenThrow(DomainException.class);
        assertThrows(DomainException.class, () -> iUserServicePort.saveEmployee(userEmployeeModel));
    }

    @Test
    void saveCustomerTest() {
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
        userModel.setIdRol(4L);

        when(iRolePersistencePort.getRole(userModel.getIdRol())).thenReturn(roleModel);
        when(iUserPersistencePort.saveCustomer(userModel)).thenReturn(userModel);
        assertNotNull(iUserServicePort.saveCustomer(userModel));
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
    }

    @Test
    void deleteUserDomainExceptionTest() {
        doThrow(DomainException.class).when(iUserPersistencePort).deleteUser(1L);
        assertThrows(DomainException.class, () -> iUserServicePort.deleteUser(null));
    }

}