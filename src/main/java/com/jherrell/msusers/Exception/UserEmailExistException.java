package com.jherrell.msusers.Exception;

public class UserEmailExistException extends RuntimeException{

    public UserEmailExistException(String message) {
        super(message);
    }
}
