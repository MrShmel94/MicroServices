package com.example.ws.microservices.firstmicroservices.customError;

import org.springframework.http.HttpStatus;

public class TooManyRequestsException extends CustomException{

    public TooManyRequestsException(String errorMessage) {
        super(errorMessage, HttpStatus.TOO_MANY_REQUESTS);
    }

}
