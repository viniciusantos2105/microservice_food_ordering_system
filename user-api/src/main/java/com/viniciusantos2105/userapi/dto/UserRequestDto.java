package com.viniciusantos2105.userapi.dto;

import com.viniciusantos2105.userapi.domain.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
@Getter
@Setter
public class UserRequestDto implements Serializable {
    @NotEmpty(message = "Nome não pode ser vazio") String userFullName;
    @Email(message = "Email invalido")
    @NotEmpty(message = "Email não pode ser vazio") String userEmail;
    @NotEmpty(message = "Senha invalida") String userPassword;
    @NotEmpty(message = "Tipo de usuario não pode ser vazio") String userType;
}