package com.example.library.management.exception;

public class UserNotFoundException extends ResourceNotFoundException {

    public UserNotFoundException() {
        super("User not found");
    }
}
