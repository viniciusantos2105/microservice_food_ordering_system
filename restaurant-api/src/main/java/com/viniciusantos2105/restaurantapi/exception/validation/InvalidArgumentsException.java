package com.viniciusantos2105.restaurantapi.exception.validation;

public class InvalidArgumentsException extends ValidationException {
    public InvalidArgumentsException(String message, String details) {
        super(message, details);
    }

    public InvalidArgumentsException(String message) {
        super(message);
    }

    public InvalidArgumentsException() {
        super("Email invalido");
    }
}
