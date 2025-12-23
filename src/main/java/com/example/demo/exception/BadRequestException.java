package com.example.demo.exception;

public class BadRequestAuthentication extends RuntimeException {

    public BadRequestAuthentication(String message) {
        super(message);
    }
}
