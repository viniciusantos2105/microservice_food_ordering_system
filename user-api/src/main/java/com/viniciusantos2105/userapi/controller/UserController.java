package com.viniciusantos2105.userapi.controller;

import com.viniciusantos2105.userapi.adapter.Adapter;
import com.viniciusantos2105.userapi.domain.user.User;
import com.viniciusantos2105.userapi.dto.UserRequestDto;
import com.viniciusantos2105.userapi.dto.UserResponseDto;
import com.viniciusantos2105.userapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final Adapter adapter;


    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto request){
        User user = adapter.mapSourceToTarget(request, User.class);
        UserResponseDto response = adapter.mapSourceToTarget(service.createUser(user), UserResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
