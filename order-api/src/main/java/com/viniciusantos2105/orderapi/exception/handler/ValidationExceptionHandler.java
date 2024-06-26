package com.viniciusantos2105.orderapi.exception.handler;

import com.viniciusantos2105.orderapi.adapter.ExceptionAdapter;
import com.viniciusantos2105.orderapi.exception.validation.InvalidArgumentsException;
import com.viniciusantos2105.orderapi.exception.validation.ValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> validationException(ValidationException exception) {
        return ResponseEntity.status(exception.status).body(exception.getMessage());
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
