package com.example.library.management.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {
        return buildResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            BadRequestException ex,
            HttpServletRequest request
    ) {
        return buildResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {
        return buildResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<ErrorResponse> buildResponse(
            Exception ex,
            HttpStatus status,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(status).body(
                ErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(status.value())
                        .error(status.name())
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build()
        );
    }
}
