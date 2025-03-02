package com.ecommerce.auth.Exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("A user with the provided details already exists. Please try a different email.");
    }
}
