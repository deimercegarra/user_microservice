package com.pragma.user_microservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


public class EmployeeModel {

    private String name;
    private String lastName;
    private String documentNumber;
    private String phone;
    private Date birthDate;
    private String email;
    private String password;
    private Long idRol;
    private RoleModel roleEntity;

    public EmployeeModel(String name, String lastName, String documentNumber, String phone, Date birthDate, String email, String password, Long idRol, RoleModel roleEntity) {
        this.name = name;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.idRol = idRol;
        this.roleEntity = roleEntity;
    }

    public EmployeeModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public RoleModel getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleModel roleEntity) {
        this.roleEntity = roleEntity;
    }
}
