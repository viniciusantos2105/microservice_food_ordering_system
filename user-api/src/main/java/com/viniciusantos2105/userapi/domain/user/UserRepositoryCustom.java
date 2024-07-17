package com.viniciusantos2105.userapi.domain.user;

public interface UserRepositoryCustom {

    void validateUserEmail(String userEmail);
    User findUserByEmail(String userEmail);
}
