package com.viniciusantos2105.restaurantapi.exception.unauthorized;

public class UnauthorizedAcessException extends UnauthorizedException {

    public UnauthorizedAcessException(String message, String details, Integer status) {
        super(message, details, status);
    }

    public UnauthorizedAcessException(String message, String details) {
        super(message, details);
    }

    public UnauthorizedAcessException(String message) {
        super(message);
    }

    public static UnauthorizedAcessException create(String message, String details, Integer status) {
        return new UnauthorizedAcessException(message, details, status);
    }

    public static UnauthorizedAcessException create(String message, String details) {
        return new UnauthorizedAcessException(message, details);
    }

    public static UnauthorizedAcessException create(String message) {
        return new UnauthorizedAcessException(message);
    }
}
