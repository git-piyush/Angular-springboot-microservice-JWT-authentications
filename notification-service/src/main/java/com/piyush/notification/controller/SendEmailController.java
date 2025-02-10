package com.piyush.notification.controller;

import com.piyush.notification.dto.RegisterNotification;
import com.piyush.notification.dto.SimpleMailRequest;
import com.piyush.notification.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notification")
public class SendEmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/test")
    public ResponseEntity sendEmail(){
        emailService.sendEmail("piyush@yopmail.com","Test","Test Body");
        return ResponseEntity.status(HttpStatus.OK).body("Email Sent");
    }

    @PostMapping("/sendmail")
    public ResponseEntity sendEmail(@RequestBody SimpleMailRequest mailRequest){
        emailService.sendEmail(mailRequest.getRecipientEmail(),mailRequest.getSubject(),mailRequest.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body("Email Sent");
    }

    @PostMapping("/onregister")
    public ResponseEntity sendEmail(@RequestBody RegisterNotification registerNotification){
        //make the body
        String body = "Hi "+registerNotification.getName()+","+"\n\nWelcome to Reva University."+
                "\n\n\n\nName: "+registerNotification.getName()+
                "\n\nEmail: "+registerNotification.getEmail()+
                "\n\nStudentId: "+registerNotification.getStudentId()+
                "\n\n\n\nYou have registered successfully. Please Activate your account and proceed for login."+
                "\n\nThank You."+
                "\nReva University";
        emailService.sendEmail(registerNotification.getEmail(),"Registration Successful.",body);
        return ResponseEntity.status(HttpStatus.OK).body("Email Sent");
    }
}
