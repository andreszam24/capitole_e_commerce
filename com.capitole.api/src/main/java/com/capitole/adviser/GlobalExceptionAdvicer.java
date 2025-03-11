package com.capitole.adviser;

import com.capitole.exception.DomainException;
import com.capitole.exception.NotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public final class GlobalExceptionAdvicer {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionAdvicer.class);

    @ResponseStatus
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleJsonParseException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        var message = "BAD_REQUEST";

        if (ex.getCause() instanceof InvalidFormatException) {
            message = "One or more fields have an incorrect format";
        }

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("message", message);
        errorResponse.put("timestamp", Instant.now());
        errorResponse.put("path", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ResponseStatus
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, Object>> handleDomainException(DomainException ex, HttpServletRequest request) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("timestamp", Instant.now());
        errorResponse.put("path", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ResponseStatus
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("timestamp", Instant.now());
        errorResponse.put("path", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ResponseStatus
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleDomainException(Exception ex, HttpServletRequest request){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put("message", "Estamos presentando inconvenientes, por favor intenta más tarde. Disculpa las molestias.");
        errorResponse.put("timestamp", Instant.now());
        errorResponse.put("path", request.getRequestURI());

        log.error("Error->", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
