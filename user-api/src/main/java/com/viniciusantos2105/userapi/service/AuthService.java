package com.viniciusantos2105.userapi.service;

import com.viniciusantos2105.userapi.config.security.TokenService;
import com.viniciusantos2105.userapi.domain.user.UserRepository;
import com.viniciusantos2105.userapi.domain.user.UserRepositoryImpl;
import com.viniciusantos2105.userapi.dto.LoginRequestDto;
import com.viniciusantos2105.userapi.dto.LoginResponseDto;
import com.viniciusantos2105.userapi.exception.resource.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder encoder;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        var user = userRepository.findUserByEmail(loginRequestDto.userEmail());
        if (encoder.matches(loginRequestDto.userPassword(), user.getPassword())) {
            var token = tokenService.gerandoToken(user);
            return LoginResponseDto.create(token);
        }
        throw ResourceNotFoundException.create("Usuário ou senha inválidos", "User", 404);
    }
}
