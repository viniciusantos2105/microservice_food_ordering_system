package com.viniciusantos2105.orderapi.domain.user.service;

import com.viniciusantos2105.orderapi.domain.user.entity.User;
import com.viniciusantos2105.orderapi.domain.user.entity.UserType;
import com.viniciusantos2105.orderapi.exception.unauthorized.UnauthorizedAcessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final WebClient webClient;
    @Value("${microservice.user-api.url}")
    private String urlUserApi;

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

    public void verifyTypeUser(User user) {
        if (user.getUserType().equals(UserType.OWNER)) {
            throw UnauthorizedAcessException.create("Entre em uma conta de cliente para executar essa ação", "userId");
        }
    }

    public void verifyOwnerUser(User user) {
        if (user.getUserType().equals(UserType.CLIENT)) {
            throw UnauthorizedAcessException.create("Entre em uma conta de proprietário para executar essa ação", "userId");
        }
    }
}