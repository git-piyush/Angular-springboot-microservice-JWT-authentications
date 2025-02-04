package com.javatechie.exception;

public class UserDoNotExistException extends RuntimeException {
    public UserDoNotExistException(String s) {
        super(s);
    }
}
