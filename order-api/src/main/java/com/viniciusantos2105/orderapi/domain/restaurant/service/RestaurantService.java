package com.viniciusantos2105.orderapi.domain.restaurant.service;

import com.viniciusantos2105.orderapi.domain.restaurant.contract.IRestaurantService;
import com.viniciusantos2105.orderapi.domain.restaurant.entity.Food;
import com.viniciusantos2105.orderapi.dto.request.FoodsListRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService implements IRestaurantService {

    private final WebClient webClient;
    @Value("${microservice.restaurant-api.url}")
    private String urlRestaurantApi;

    public RestaurantService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<Food> getFoods(FoodsListRequestDto request) {
        return webClient.post()
                .uri(urlRestaurantApi + "/food")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Food>>() {
                }).block();
    }

    @Override
    public void isUserRestaurantOwner(String token, UUID restaurantId) {
        webClient.get()
                .uri(urlRestaurantApi + "/" + restaurantId)
                .header("Authorization", "Bearer " + token)
                .retrieve();
    }

}
