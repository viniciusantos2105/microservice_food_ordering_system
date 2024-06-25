package com.viniciusantos2105.restaurantapi.exception.handler;


import com.viniciusantos2105.restaurantapi.adapter.ExceptionAdapter;
import com.viniciusantos2105.restaurantapi.exception.resource.ResourceException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<?> validationException(ResourceException exception) {
        return new ResponseEntity<>(ExceptionAdapter.toJson(exception), HttpStatusCode.valueOf(exception.status));
    }
}
