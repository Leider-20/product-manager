package com.product.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //Global handler that intercepts exceptions thrown by controllers
public class GlobalExceptionHandler {

    //Handles errors related to invalid requests such as duplicated products
    @ExceptionHandler(BadResourceRequestException.class)
    public ResponseEntity<Map<String, String>> handleBadResource(BadResourceRequestException ex) {

        //Create a response body containing error details
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        error.put("status", "400");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    //Handles cases where the requested resource does not exist
    @ExceptionHandler(NoSuchResourceFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFound(NoSuchResourceFoundException ex){

        //Create a response body containing error details
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        error.put("status", "404");

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    //Handles validation errors triggered by @Valid annotations in request DTOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {

        //Map that will contain the field and the validaton error message
        Map<String, String> errors = new HashMap<>();

        //Extract validation errors and store them in the map
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity
                .badRequest()
                .body(errors);
    }
}