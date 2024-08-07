package com.viniciusantos2105.userapi.domain.user.service;

import com.viniciusantos2105.userapi.config.security.TokenService;
import com.viniciusantos2105.userapi.domain.user.contract.IUserService;
import com.viniciusantos2105.userapi.domain.user.entity.User;
import com.viniciusantos2105.userapi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final PasswordEncoder encoder;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public User createUser(User user) {
        userRepository.validateUserEmail(user.getUserEmail());
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        return userRepository.save(user);
    }

    public User getUser(String token) {
        token = token.replace("Bearer ", "");
        var email = tokenService.validarToken(token);
        return userRepository.findUserByEmail(email);
    }

}
