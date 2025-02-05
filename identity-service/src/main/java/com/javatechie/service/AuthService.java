package com.javatechie.service;

import com.javatechie.config.NotificationRestClient;
import com.javatechie.dto.RegisterNotification;
import com.javatechie.entity.UserCredential;
import com.javatechie.exception.UserAlreadyExistException;
import com.javatechie.exception.UserDoNotExistException;
import com.javatechie.repository.UserCredentialRepository;
import jakarta.transaction.Transactional;
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
    private NotificationRestClient notificationRestClient;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public UserCredential saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        UserCredential userCredential = repository.save(credential);
        if(userCredential != null){
            RegisterNotification registerNotificationDTO = new RegisterNotification();
            registerNotificationDTO.setEmail(userCredential.getEmail());
            registerNotificationDTO.setName(userCredential.getName());
            registerNotificationDTO.setStudentId(String.valueOf(userCredential.getId()));
            notificationRestClient.sendEmail(registerNotificationDTO);
        }
        return userCredential;
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
