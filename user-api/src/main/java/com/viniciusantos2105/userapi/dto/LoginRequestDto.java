package com.viniciusantos2105.userapi.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequestDto(
        @NotEmpty(message = "Email não pode ser vazio") String userEmail,
        @NotEmpty(message = "Senha não pode ser vazia") String userPassword) {
}
