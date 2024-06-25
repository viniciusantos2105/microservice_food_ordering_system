package com.viniciusantos2105.restaurantapi.service;

import com.viniciusantos2105.restaurantapi.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

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


}