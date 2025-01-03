package com.employeeCurd.demo.learn;

public class InvalidEmployeeDataException extends RuntimeException {

    // Constructor with message
    public InvalidEmployeeDataException(String message) {
        super(message);
    }
}

