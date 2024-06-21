package com.viniciusantos2105.userapi.exception.resource;

import com.viniciusantos2105.userapi.exception.CustomException;

public class ResourceException  extends CustomException {

    public ResourceException(String message, String type, Integer status) {
        this.message = message;
        this.type = type;
        this.status = status;
    }

    public ResourceException(String message, String type) {
        this.message = message;
        this.type = type;
        this.status = 400;
    }

    public ResourceException(String message) {
        this.message = message;
        this.status = 400;
    }

    public static ResourceException create(String message, String details, Integer status) {
        return new ResourceException(message, details, status);
    }

    public static ResourceException create(String message, String details) {
        return new ResourceException(message, details);
    }
}
