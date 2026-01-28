package com.example.library.management.exception;

public class AuthorNotFoundException extends ResourceNotFoundException {

    public AuthorNotFoundException() {
        super("Author not found");
    }
}
