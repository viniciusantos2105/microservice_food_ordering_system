package com.viniciusantos2105.orderapi.exception.resource;

public class ResourceAlreadyExists extends ResourceException {
    public ResourceAlreadyExists(String message, String type, Integer status) {
        super(message, type, status);
    }

    public ResourceAlreadyExists(String message, String type) {
        super(message, type);
    }

    public ResourceAlreadyExists(String message) {
        super(message);
    }
}
