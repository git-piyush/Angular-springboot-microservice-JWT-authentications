package com.javatechie.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordResetRequest {

    private String email;

    private String password;

    private String otp;
}
