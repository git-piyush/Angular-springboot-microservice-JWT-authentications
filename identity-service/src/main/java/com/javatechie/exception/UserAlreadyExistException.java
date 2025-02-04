package com.javatechie.exception;

import com.javatechie.dto.ErrorMsg;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String s) {
        super(s);
    }
}
