package com.code.bankingaccount.email;

import com.code.bankingaccount.entity.User;
import com.code.bankingaccount.entity.UserDAO;
import org.apache.commons.validator.routines.EmailValidator;
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
    private UserDAO dao;

    @Autowired
    private JavaMailSender emailSender;

    private final EmailValidator emailvalidator = EmailValidator.getInstance();


    /**
     * If the email is valid and it is registered, it sends the password to the email.
     * If not a RunTimeException is thrown
     *
     * @param emailReceiver
     */
    public void sendPasswordEmail(String emailReceiver) {

        if( ! emailvalidator.isValid(emailReceiver) ){
            throw new RuntimeException("Email inválido.");
        }

        User userEmail = dao.findByEmail(emailReceiver);

        if ( userEmail == null ){
            throw new RuntimeException("Email não registrado na plataforma.");
        }

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(emailReceiver);
        message.setSubject("Your password");
        message.setText("This is your password: " + userEmail.getSenha());

        emailSender.send(message);
        //todo: fazer um email mais bem elaborado e bonito, em vez de apenas mandar a senha como texto
    }



}