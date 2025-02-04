package com.javatechie.service;

import com.javatechie.entity.UserCredential;
import com.javatechie.exception.UserAlreadyExistException;
import com.javatechie.exception.UserDoNotExistException;
import com.javatechie.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user added to the system";
    }

    public UserCredential findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(()-> new UserDoNotExistException(String.format("User with: %s do not exist.", email)));
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
