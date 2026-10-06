package com.store.exception;

public class DuplicateCustomerException extends RuntimeException {
    public DuplicateCustomerException(String email) {
        super("A customer already exists with email: " + email);
    }
}
