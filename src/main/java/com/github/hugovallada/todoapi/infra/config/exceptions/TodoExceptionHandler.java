package com.github.hugovallada.todoapi.infra.config.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class TodoExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "JSON parse error, could not deserialize one or more objects", Collections.singletonList(exception.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = new ArrayList<>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    errors.add(String.format("Field %s : %s ", error.getField().toUpperCase(), error.getDefaultMessage()));
                });

        exception.getBindingResult()
                .getGlobalErrors()
                .forEach(error -> {
                    errors.add(String.format("Object %s : %s", error.getObjectName(), error.getDefaultMessage()));
                });

        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Arguments with validation error", errors);

    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message, List<String> errors) {
        ApiError apiError = ApiError.builder()
                .code(status.value())
                .message(status.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .message(message)
                .errors(errors)
                .build();

        return ResponseEntity.status(status).body(apiError);
    }


}
