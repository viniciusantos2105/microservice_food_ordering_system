package com.viniciusantos2105.restaurantapi.exception.unauthorized;

import com.viniciusantos2105.restaurantapi.exception.CustomException;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException(String message, String details, Integer status) {
        this.message = message;
        this.type = details;
        this.status = status;
    }

    public UnauthorizedException(String message, String details) {
        this.message = message;
        this.type = details;
        this.status = 401;
    }

    public UnauthorizedException(String message) {
        this.message = message;
        this.status = 401;
    }

    public static UnauthorizedException create(String message, String details, Integer status) {
        return new UnauthorizedException(message, details, status);
    }

    public static UnauthorizedException create(String message, String details) {
        return new UnauthorizedException(message, details);
    }

    public static UnauthorizedException create(String message) {
        return new UnauthorizedException(message);
    }
}
