package com.ecommerce.auth.Exceptions;

public class EmailDontExistsException extends RuntimeException{
    public EmailDontExistsException(){
        super("The provided email does not exist in our records. Please check and try again.");
    }
}
