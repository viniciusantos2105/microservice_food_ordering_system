package com.viniciusantos2105.userapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginResponseDto {

    private String token;

    public LoginResponseDto(String token) {
        this.token = token;
    }

    public static LoginResponseDto create(String token) {
        return new LoginResponseDto(token);
    }
}
