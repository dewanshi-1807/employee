package com.employeeCurd.demo.learn;

import com.employeeCurd.demo.learn.EmployeeNotFoundException;
import com.employeeCurd.demo.learn.InvalidEmployeeDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeAdvice {

    private static final Logger log = LoggerFactory.getLogger(EmployeeAdvice.class);

    // Handle EmployeeNotFoundException
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        log.warn("Employee not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Handle InvalidEmployeeDataException
    @ExceptionHandler(InvalidEmployeeDataException.class)
    public ResponseEntity<String> handleInvalidEmployeeDataException(InvalidEmployeeDataException ex) {
        log.warn("Invalid employee data: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Handle any other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        log.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred. Please try again later.");
    }
}
