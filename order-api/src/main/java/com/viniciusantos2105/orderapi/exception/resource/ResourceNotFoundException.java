package com.viniciusantos2105.orderapi.exception.resource;

public class ResourceNotFoundException extends ResourceException {

    public ResourceNotFoundException(String message, String type, Integer status) {
        super(message, type, status);
    }

    public ResourceNotFoundException(String message, String details) {
        super(message, details);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public static ResourceNotFoundException create(String message, String details, Integer status) {
        return new ResourceNotFoundException(message, details, status);
    }
}
