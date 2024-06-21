package com.viniciusantos2105.userapi.controller;

import com.viniciusantos2105.userapi.dto.LoginRequestDto;
import com.viniciusantos2105.userapi.dto.LoginResponseDto;
import com.viniciusantos2105.userapi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto request) {
        LoginResponseDto response = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
