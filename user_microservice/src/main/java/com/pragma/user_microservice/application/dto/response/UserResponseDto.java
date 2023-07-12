package com.pragma.user_microservice.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String name;
    private String lastName;
    private String documentNumber;
    private String phone;
    private Date birthDate;
    private String email;
    private String password;

}
