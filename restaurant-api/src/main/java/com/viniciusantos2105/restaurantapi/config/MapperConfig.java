package com.viniciusantos2105.restaurantapi.config;

import com.viniciusantos2105.restaurantapi.domain.restaurant.Food;
import com.viniciusantos2105.restaurantapi.domain.restaurant.Restaurant;
import com.viniciusantos2105.restaurantapi.dto.responses.FoodResponseOrderDto;
import com.viniciusantos2105.restaurantapi.dto.responses.RestaurantResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.getConfiguration().setAmbiguityIgnored(true);

        mapper.createTypeMap(Restaurant.class, RestaurantResponseDto.class)
                .addMappings(map -> map.map(Restaurant::getRestaurantName, RestaurantResponseDto::setRestaurantName));
        mapper.createTypeMap(Food.class, FoodResponseOrderDto.class)
                .addMappings(map -> map.map(src -> src.getRestaurant().getRestaurantId(), FoodResponseOrderDto::setRestaurantId));
        return mapper;
    }
}
