package com.viniciusantos2105.orderapi.service;

import com.viniciusantos2105.orderapi.domain.restaurant.Food;
import com.viniciusantos2105.orderapi.dto.FoodsListRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class RestaurantService {

    private final WebClient webClient;
    @Value("${microservice.restaurant-api.url}")
    private String urlRestaurantApi;

    public RestaurantService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Food> getFoods(FoodsListRequestDto request) {
        return webClient.post()
                .uri(urlRestaurantApi + "/food")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Food>>() {}).block();
    }
}
