package com.javatechie.controller;

import com.javatechie.config.NotificationRestClient;
import com.javatechie.dto.AuthRequest;
import com.javatechie.dto.AuthResponse;
import com.javatechie.dto.RegisterNotification;
import com.javatechie.dto.Response;
import com.javatechie.entity.UserCredential;
import com.javatechie.exception.UserAlreadyExistException;
import com.javatechie.exception.UserDoNotExistException;
import com.javatechie.service.AuthService;
import com.javatechie.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

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
}
