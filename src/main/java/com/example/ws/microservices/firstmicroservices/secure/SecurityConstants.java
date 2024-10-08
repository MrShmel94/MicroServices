package com.example.ws.microservices.firstmicroservices.secure;

public class SecurityConstants {

    public static final long EXPIRATION_TIME = 86400000L;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/v1/users";
}

