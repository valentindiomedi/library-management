package com.example.library.management.exception;

public class LoanNotFoundException extends ResourceNotFoundException {

    public LoanNotFoundException() {
        super("Loan not found");
    }
}
