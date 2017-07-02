package com.code.bankingaccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by marinatoledo on 01/07/17.
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;


    //todo: fazer um service mais bem elaborado e bonito, em vez de apenas mandar a senha como texto
    public void sendSimpleMailMessage(String emailReceiver, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(emailReceiver);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }

}