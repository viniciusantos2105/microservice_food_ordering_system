package com.viniciusantos2105.orderapi.config;

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

        // Apply the NotEqualCondition to all mappings
        mapper.getConfiguration().setPropertyCondition(new NotEqualCondition());
//
//        mapper.createTypeMap(OrderHistory.class, OrderHistoryResponseDto.class)
//                .addMappings(map -> map.map(OrderHistory::getOrderStatusDate, OrderHistoryResponseDto::setOrderStatusDate))
//                .addMappings(map -> map.map(OrderHistory::getOrderStatus, OrderHistoryResponseDto::setOrderStatus));
        return mapper;
    }
}