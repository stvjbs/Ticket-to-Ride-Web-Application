package com.andersen.ticket_to_ride.config.exception_handler;

import com.andersen.ticket_to_ride.exception.NoSuchEntityException;
import com.andersen.ticket_to_ride.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<String> handleNoSuchEntityException(NoSuchEntityException ex) {
        String message = ex.getMessage();
        log.info(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        String message = ex.getMessage();
        log.info(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}