package com.viniciusantos2105.userapi.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viniciusantos2105.userapi.exception.CustomException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExceptionAdapter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(CustomException exception) {
        Map<String, Object> details = new HashMap<>();
        Optional.ofNullable(exception.getType()).ifPresent(type -> details.put("type", type));
        Optional.ofNullable(exception.getMessage()).ifPresent(message -> details.put("message", message));
        Optional.ofNullable(exception.getStatus()).ifPresent(status -> details.put("status", status));

        Map<String, Object> combinedJson = objectMapper.convertValue(exception.toJsonObject(), Map.class);
        if (!details.isEmpty()) {
            combinedJson.put("details", details);
        }

        return convertMapToJson(combinedJson);
    }

    private static String convertMapToJson(Map<String, Object> map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}