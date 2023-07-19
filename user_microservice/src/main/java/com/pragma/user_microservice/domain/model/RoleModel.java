package com.pragma.user_microservice.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


public class RoleModel {

    private Long id;
    private String name;
    private String description;
    private Set<UserModel> userEntities;

    public RoleModel(Long id, String name, String description, Set<UserModel> userEntities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userEntities = userEntities;
    }

    public RoleModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserModel> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(Set<UserModel> userEntities) {
        this.userEntities = userEntities;
    }
}
