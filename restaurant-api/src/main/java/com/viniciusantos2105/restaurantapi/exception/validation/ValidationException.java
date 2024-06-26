package com.viniciusantos2105.restaurantapi.exception.validation;

import com.viniciusantos2105.restaurantapi.exception.CustomException;

public class ValidationException extends CustomException {

    public ValidationException(String message, String details) {
        this.message = message;
        this.type = details;
        this.status = 400;
    }

    public ValidationException(String message) {
        this.message = message;
        this.status = 400;
    }

    public ValidationException() {
        this.message = "Email invalido";
        this.status = 400;
    }

    public static ValidationException create(String message, String details) {
        return new ValidationException(message, details);
    }

    public static ValidationException create(String message) {
        return new ValidationException(message);
    }

}
