package com.viniciusantos2105.restaurantapi.domain.user.service;

import com.viniciusantos2105.restaurantapi.domain.user.contract.IUserService;
import com.viniciusantos2105.restaurantapi.domain.user.entity.User;
import com.viniciusantos2105.restaurantapi.domain.user.entity.UserType;
import com.viniciusantos2105.restaurantapi.exception.validation.InvalidArgumentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService implements IUserService {

    private final WebClient webClient;
    @Value("${microservice.url}")
    private String urlUserApi;

    @Autowired
    public UserService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<User> getUser(String token) {
        return webClient.get()
                .uri(urlUserApi)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(User.class);
    }

    public void validateOwnerUser(User user) {
        if (user.getUserType() != UserType.OWNER) {
            throw InvalidArgumentsException.create("Tipo de usuario invalido, crie um usuario do tipo OWNER");
        }
    }
}