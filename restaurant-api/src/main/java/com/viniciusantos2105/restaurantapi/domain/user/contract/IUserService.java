package com.viniciusantos2105.restaurantapi.domain.user.contract;


import com.viniciusantos2105.restaurantapi.domain.user.entity.User;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<User> getUser(String token);
    void validateOwnerUser(User user);
}
