package com.coffeeproj.music_service.exception;

public class PasswordIsTooShortException extends Exception{
    public PasswordIsTooShortException(String message) {
        super(message);
    }
}
