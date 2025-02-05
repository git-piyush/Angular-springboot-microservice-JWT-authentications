package com.javatechie.service;

import com.javatechie.entity.PasswordResetDetails;
import com.javatechie.entity.UserCredential;
import com.javatechie.repository.PasswordResetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PasswordResetTokenService {
    @Autowired
    private PasswordResetRepository tokenRepository;

    public PasswordResetDetails createToken(UserCredential user) {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        String token = String.format("%06d", number);
        PasswordResetDetails resetToken = new PasswordResetDetails();
        resetToken.setOtp(token);
        resetToken.setUserCredential(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // Token expires in 1 hour
        return tokenRepository.save(resetToken);
    }

    public PasswordResetDetails validateToken(String token) {
        return tokenRepository.findByOtp(token)
                .filter(t -> t.getExpiryDate().isAfter(LocalDateTime.now()))
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));
    }
}
