package com.viniciusantos2105.userapi.domain.user.repository;

import com.viniciusantos2105.userapi.domain.user.entity.User;

public interface UserRepositoryCustom {

    void validateUserEmail(String userEmail);
    User findUserByEmail(String userEmail);
}
