package com.pragma.user_microservice.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleModel {

    private Long id;
    private String name;
    private String description;
    private Set<UserModel> userEntities;

}
