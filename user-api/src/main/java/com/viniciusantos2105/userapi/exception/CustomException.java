package com.viniciusantos2105.userapi.exception;

import java.util.Map;

public abstract class CustomException extends RuntimeException {

    public String message;
    public String type;
    public int status;

    @Override
    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public Object toJsonObject() {
        return Map.of(
                "code", getStatus(),
                "message", getMessage()
        );
    }
}
