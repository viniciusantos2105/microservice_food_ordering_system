package com.viniciusantos2105.userapi.service;

import com.viniciusantos2105.userapi.domain.user.User;
import com.viniciusantos2105.userapi.domain.user.UserRepository;
import com.viniciusantos2105.userapi.domain.user.UserRepositoryImpl;
import com.viniciusantos2105.userapi.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserRepositoryImpl userRepositoryImpl;


    public User createUser(User user) {
        userRepositoryImpl.validateUserEmail(user.getUserEmail());
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        return userRepository.save(user);
    }

}
