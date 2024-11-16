package com.encora.searchflights.controller;

import com.encora.searchflights.exception.InvalidReturnDateException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerMapping;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MissingServletRequestParameterException for missing parameters in @RequestParam.
     * Provides a response with required parameters for the specific controller.
     *
     * @param ex MissingServletRequestParameterException
     * @param request WebRequest for determining the controller handling the request
     * @return ResponseEntity with a general message and the required parameters for the specific controller
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex, WebRequest request) {

        Map<String, Object> response = new HashMap<>();

        // Map controllers or routes to their required parameters with examples
        Map<String, Map<String, String>> requiredParametersByController = Map.of(
                "AirlineController", Map.of(
                        "airlineCode", "e.g., AA (IATA code)"
                ),
                "AirportController", Map.of(
                        "keyword", "e.g., Manchester",
                        "iataCode", "e.g., MAN"
                ),
                "FlightController", Map.of(
                        "departureAirportCode", "e.g., JFK",
                        "arrivalAirportCode", "e.g., LAX",
                        "departureDate", "e.g., 2024-12-01 (ISO format)",
                        "returnDate", "Optional, e.g., 2024-12-10 (ISO format)",
                        "numberOfAdults", "e.g., 1",
                        "currency", "e.g., USD"
                )
        );

        // Identify the controller handling the request
        HandlerMethod handlerMethod = (HandlerMethod) ((ServletWebRequest) request).getAttribute(
                HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);

        String controllerName = handlerMethod != null ? handlerMethod.getBeanType().getSimpleName() : "UnknownController";

        // Get required parameters for the controller
        Map<String, String> requiredParameters = requiredParametersByController.getOrDefault(controllerName, Map.of());

        // Build response structure
        response.put("requiredParameters", requiredParameters);
        response.put("message", "Missing required parameters. Ensure the following parameters are included in the request:");
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Handles ConstraintViolationException for parameter validation errors.
     * Builds a response with specific parameter violations and a descriptive message.
     *
     * @param ex ConstraintViolationException
     * @return ResponseEntity with parameter violations and a validation message
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, Object> response = new HashMap<>();

        Map<String, String> violations = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString().split("\\.")[1],
                        ConstraintViolation::getMessage
                ));

        response.put("violations", violations);
        response.put("message", "Validation failed for the provided parameters.");
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Handles InvalidReturnDateException for custom validation of return and departure dates.
     *
     * @param ex InvalidReturnDateException indicating return date issues
     * @return ResponseEntity with a structured error response for invalid return dates
     */
    @ExceptionHandler(InvalidReturnDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleInvalidReturnDateException(InvalidReturnDateException ex) {
        Map<String, Object> response = new HashMap<>();

        Map<String, String> violations = new HashMap<>();
        violations.put("returnDate", ex.getMessage());

        response.put("violations", violations);
        response.put("message", "Validation failed for the provided parameters.");
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Handles MethodArgumentTypeMismatchException for invalid arguments in parameters, such as enum mismatches.
     * Responds with the expected type and format for the parameter.
     *
     * @param ex MethodArgumentTypeMismatchException for invalid argument types
     * @return ResponseEntity with violations and descriptive messages
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, Object> response = new HashMap<>();

        String parameterName = ex.getName();
        String expectedTypeMessage = "Invalid value for parameter '%s'. %s".formatted(
                parameterName,
                ex.getRequiredType().equals(LocalDate.class) ? "Expected format: YYYY-MM-DD" : "Allowed values: USD, MXN, EUR"
        );

        Map<String, String> violations = new HashMap<>();
        violations.put(parameterName, expectedTypeMessage);

        response.put("violations", violations);
        response.put("message", "Validation failed for the provided parameters.");

        return ResponseEntity.badRequest().body(response);
    }
}
