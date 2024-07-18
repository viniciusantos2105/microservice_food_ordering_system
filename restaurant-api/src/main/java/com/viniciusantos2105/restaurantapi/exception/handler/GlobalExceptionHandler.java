package com.viniciusantos2105.restaurantapi.exception.handler;

import com.viniciusantos2105.restaurantapi.adapter.ExceptionAdapter;
import com.viniciusantos2105.restaurantapi.exception.CustomException;
import com.viniciusantos2105.restaurantapi.exception.validation.InvalidArgumentsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> validationException(CustomException exception) {
        return new ResponseEntity<>(ExceptionAdapter.toJson(exception), HttpStatusCode.valueOf(exception.status));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationDataException(MethodArgumentNotValidException exception) throws InvalidArgumentsException {
        String variaveisErroConcatenadas = exception.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        String[] nomeClasse = exception.getBindingResult().getObjectName().split("(?=\\p{Lu})", 2);
        return new ResponseEntity<>(ExceptionAdapter.toJson(new InvalidArgumentsException(variaveisErroConcatenadas, nomeClasse[0])), HttpStatus.BAD_REQUEST);
    }
}
