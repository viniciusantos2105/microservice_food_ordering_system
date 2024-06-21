package com.viniciusantos2105.userapi.exception;

public class ValidationException extends CustomException{

    public ValidationException(String message, String details) {
        this.message = message;
        this.type = details;
        this.status = 400;
    }

    public ValidationException(String message) {
        this.message = message;
        this.status = 400;
    }

    public ValidationException create(String message, String details) {
        return new ValidationException(message, details);
    }

    public ValidationException create(String message) {
        return new ValidationException(message);
    }
}
