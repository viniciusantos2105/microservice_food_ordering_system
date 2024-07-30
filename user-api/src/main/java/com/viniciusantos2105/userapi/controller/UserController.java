package com.viniciusantos2105.userapi.controller;

import com.viniciusantos2105.userapi.adapter.Adapter;
import com.viniciusantos2105.userapi.domain.user.contract.IUserService;
import com.viniciusantos2105.userapi.domain.user.entity.User;
import com.viniciusantos2105.userapi.domain.user.service.UserService;
import com.viniciusantos2105.userapi.dto.UserRequestDto;
import com.viniciusantos2105.userapi.dto.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;
    private final Adapter adapter;


    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto request) {
        User user = adapter.mapSourceToTarget(request, User.class);
        UserResponseDto response = adapter.mapSourceToTarget(service.createUser(user), UserResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<User> findUserByToken(@RequestHeader("Authorization") String token) {
        User user = service.getUser(token);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
