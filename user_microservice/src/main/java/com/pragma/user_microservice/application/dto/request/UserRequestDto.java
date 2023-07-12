package com.pragma.user_microservice.application.dto.request;

import com.pragma.user_microservice.infrastructure.configuration.Constants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserRequestDto {

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    private String name;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    private String lastName;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @Digits(integer = 10, fraction = 0, message = "A 10-digit numeric value is expected.")
    private String documentNumber;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    @Pattern(regexp = "\\+?\\d{1,13}", message = "The phone number is invalid.")
    @Size(max = 13, message = "The phone number must have a maximum of 13 characters.")
    private String phone;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private Date birthDate;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    @Email(message = "Invalid email format.")
    private String email;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    private String password;

}
