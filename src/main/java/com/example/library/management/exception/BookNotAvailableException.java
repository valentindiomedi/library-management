package com.example.library.management.exception;

public class BookNotAvailableException extends BadRequestException {

    public BookNotAvailableException() {
        super("Book is not available for loan");
    }
}
