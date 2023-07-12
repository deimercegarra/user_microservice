package com.pragma.user_microservice.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.Date;


@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "document_number")
    private int documentNumber;

    private String phone;

    @Column(name = "birth_date")
    private Date birthDate;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private RoleEntity roleEntity;

}
