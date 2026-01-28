package com.example.library.management.exception;

public class BookNotFoundException extends ResourceNotFoundException {

    public BookNotFoundException() {
        super("Book not found");
    }
}
