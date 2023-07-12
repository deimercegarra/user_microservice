package com.pragma.user_microservice.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel {

    private Long id;
    private String name;
    private String lastName;
    private String documentNumber;
    private String phone;
    private Date birthDate;
    private String email;
    private String password;
    private RoleModel roleEntity;

}
