package com.example.ws.microservices.firstmicroservices.customError;

import org.springframework.http.HttpStatus;

public class EmployeeNotFound extends CustomException {
    public EmployeeNotFound(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
