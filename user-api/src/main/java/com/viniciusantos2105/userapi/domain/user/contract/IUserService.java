package com.viniciusantos2105.userapi.domain.user.contract;

import com.viniciusantos2105.userapi.domain.user.entity.User;

public interface IUserService {

    User createUser(User user);
    User getUser(String token);
}
