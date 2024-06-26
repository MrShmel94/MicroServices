package com.example.ws.microservices.firstmicroservices;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String generateUserId(int length){
        return generateRandomString(length);
    }

    private String generateRandomString(int length) {
        StringBuilder builder = new StringBuilder();

        for( int i = 0; i < length; i++ ){
            builder.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return builder.toString();
    }
}
