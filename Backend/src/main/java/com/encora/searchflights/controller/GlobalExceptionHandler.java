package com.encora.searchflights.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors for @RequestParam, returning custom messages for each violated constraint.
     *
     * @param ex ConstraintViolationException thrown by @RequestParam validations
     * @return ResponseEntity with parameter error messages and a BAD_REQUEST status
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString().split("\\.")[1], // Extracts parameter name
                        ConstraintViolation::getMessage // Custom message
                ));
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles validation errors on @RequestBody, such as DTO validations, providing field-specific messages.
     *
     * @param ex MethodArgumentNotValidException thrown for @RequestBody validation failures
     * @return ResponseEntity with field-specific validation messages and a BAD_REQUEST status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles IllegalArgumentExceptions, typically for custom validation logic, with a message response.
     *
     * @param ex IllegalArgumentException indicating invalid arguments
     * @return ResponseEntity with the exception message and a BAD_REQUEST status
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}