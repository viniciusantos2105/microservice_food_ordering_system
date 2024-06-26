package com.viniciusantos2105.orderapi.adapter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Adapter {

    private final ModelMapper mapper;

    public <S, T> T mapSourceToTarget(S source, Class<T> targetClass) {
        return mapper.map(source, targetClass);
    }

    public <T> T updateTargetWithSource(T source, T target) {
        mapper.map(source, target);
        return target;
    }
}