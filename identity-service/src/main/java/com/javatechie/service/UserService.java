package com.javatechie.service;

import com.javatechie.entity.UserCredential;
import com.javatechie.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserCredentialRepository userRepository;

    public UserCredential findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updatePassword(UserCredential user, String newPassword) {
        user.setPassword(newPassword); // Ensure to hash the password before saving
        userRepository.save(user);
    }
}
