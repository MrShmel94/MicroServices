package com.example.ws.microservices.firstmicroservices.customError;

import org.springframework.http.HttpStatus;

public class TokenGenerationException extends CustomException {
    public TokenGenerationException(String errorMessage) {
      super(errorMessage, HttpStatus.UNAUTHORIZED);
    }
}
