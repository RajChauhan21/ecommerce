package com.monolith.ecommerce.E_Commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public ResponseEntity<?> sendPasswordRestLink(String to, String cc){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setCc(cc);
        mailMessage.setSubject("Password Reset Request");
        mailMessage.setText("Hello User, I hope you are doing we found you request for resetting your password");
        mailSender.send(mailMessage);

        return new ResponseEntity<>("E-mail sent on provided email id", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> sendEmailForSignUp(String to, String cc){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setCc(cc);
        mailMessage.setSubject("SignUp Successful");
        mailMessage.setText("Welcome to ShopRi, we wish you will have a great user experience through out our website");
        mailSender.send(mailMessage);

        return new ResponseEntity<>("E-mail sent on provided email id", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> sendEmailForSignIn(String to, String cc){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setCc(cc);
        mailMessage.setSubject("SignIn Successful");
        mailMessage.setText("Hey!! welcome back, we missed you!! Let's back to shopping.....");
        mailSender.send(mailMessage);

        return new ResponseEntity<>("E-mail sent on provided email id", HttpStatus.ACCEPTED);
    }
}
