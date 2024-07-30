package com.viniciusantos2105.orderapi.domain.user.contract;

import com.viniciusantos2105.orderapi.domain.user.entity.User;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<User> getUser(String token);
    void verifyTypeUser(User user);
    void verifyOwnerUser(User user);
}
