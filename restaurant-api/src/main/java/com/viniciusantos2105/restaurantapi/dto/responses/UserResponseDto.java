package com.viniciusantos2105.restaurantapi.dto.responses;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserResponseDto {
    private String userFullName;
    private String userEmail;
}