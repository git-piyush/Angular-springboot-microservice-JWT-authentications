package com.javatechie.service;

import com.javatechie.entity.PasswordResetDetails;
import com.javatechie.entity.UserCredential;
import com.javatechie.repository.PasswordResetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PasswordService {

    @Autowired
    private PasswordResetRepository passwordResetRepository;

    @Autowired
    private EmailService emailService;


    public PasswordResetDetails sendOtp(String email, UserCredential user){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        // this will convert any number sequence into 6 character.
        String otp = String.format("%06d", number);

        emailService.sendEmail(email,"Password Reset Request.",otp );

        PasswordResetDetails resetToken = new PasswordResetDetails();
        resetToken.setOtp(otp);
        resetToken.setUserCredential(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // Token expires in 1 hour
        return passwordResetRepository.save(resetToken);
    }

}
