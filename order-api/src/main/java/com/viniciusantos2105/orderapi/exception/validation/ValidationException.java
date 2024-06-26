package com.viniciusantos2105.orderapi.exception.validation;

import com.viniciusantos2105.orderapi.exception.CustomException;

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


}
