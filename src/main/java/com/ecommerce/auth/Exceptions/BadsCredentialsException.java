package com.ecommerce.auth.Exceptions;

public class BadsCredentialsException extends RuntimeException{
  public BadsCredentialsException(){
   super("Invalid credentials. Please check your password.");
  }
}
