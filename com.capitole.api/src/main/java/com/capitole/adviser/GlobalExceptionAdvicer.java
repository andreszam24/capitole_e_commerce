package com.capitole.adviser;

import com.capitole.exception.DomainException;
import com.capitole.exception.NotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public final class GlobalExceptionAdvicer {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionAdvicer.class);

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleJsonParseException(MethodArgumentTypeMismatchException ex) {
        var message = "Invalid request format";

        if (ex.getCause() instanceof InvalidFormatException) {
            message = "One or more fields have an incorrect format";
        }

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", "Invalid request body");
        errorResponse.put("message", message);
        errorResponse.put("timestamp", Instant.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, Object>> handleDomainException(DomainException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", "Invalid request body");
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("timestamp", Instant.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", "Not found");
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("timestamp", Instant.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleDomainException(Exception ex){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", "Internal server error");
        errorResponse.put("message", "Estamos presentando inconvenientes, por favor intenta más tarde. Disculpa las molestias.");
        errorResponse.put("timestamp", Instant.now());

        log.error("Error->", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
