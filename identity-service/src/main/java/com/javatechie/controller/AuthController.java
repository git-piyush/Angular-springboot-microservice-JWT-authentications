package com.javatechie.controller;

import com.javatechie.dto.AuthRequest;
import com.javatechie.dto.AuthResponse;
import com.javatechie.dto.PasswordResetRequest;
import com.javatechie.dto.Response;
import com.javatechie.entity.PasswordResetDetails;
import com.javatechie.entity.UserCredential;
import com.javatechie.exception.UserDoNotExistException;
import com.javatechie.repository.UserCredentialRepository;
import com.javatechie.service.AuthService;
import com.javatechie.service.PasswordResetTokenService;
import com.javatechie.service.PasswordService;
import com.javatechie.service.UserService;
import com.javatechie.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private PasswordResetTokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/register")
    public ResponseEntity addNewUser(@RequestBody UserCredential user) {
        try {
            UserCredential userCredential = service.findByEmail(user.getEmail());
        } catch (UserDoNotExistException e) {
            //Register the user
            user.setUserType(null);
            user.setActive(AppConstants.NO);
            UserCredential userCredential = service.saveUser(user);
            Response res = new Response("User with email: "+user.getEmail()+" Created.");
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
        Response res = new Response("User with email: "+user.getEmail()+" already exist.");
        return new ResponseEntity<>(res, HttpStatus.FOUND);
    }

    @PostMapping("/token")
    public AuthResponse getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            String token = service.generateToken(authRequest.getEmail());
            UserCredential user = service.findByEmail(authRequest.getEmail());
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(token);
            authResponse.setEmail(authRequest.getEmail());
            authResponse.setActive(user.getActive());
            authResponse.setUserType(user.getUserType());
            authResponse.setUserName(user.getName());
            return authResponse;
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }


    @PostMapping("/sendotp")
    public ResponseEntity sendOtp (@RequestBody AuthRequest authRequest) {
        System.out.println(authRequest.getEmail());
        UserCredential userCredential = service.findByEmail(authRequest.getEmail());
        passwordService.sendOtp(authRequest.getEmail(),userCredential);
        Response res = new Response("Otp has been sent to the registered email...");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        PasswordResetDetails resetDetails = tokenService.validateToken(passwordResetRequest.getOtp());
        UserCredential user = resetDetails.getUserCredential();
        userService.updatePassword(user, passwordEncoder.encode(passwordResetRequest.getPassword()));
        Response res = new Response("Password reset successfully.");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
