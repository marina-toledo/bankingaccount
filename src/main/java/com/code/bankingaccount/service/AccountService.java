package com.code.bankingaccount.service;

import com.code.bankingaccount.entity.User;
import com.code.bankingaccount.entity.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by marinatoledo on 02/07/17.
 */
@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

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

        User userEmail = userRepository.findByEmail(emailReceiver);

        if ( userEmail == null ){
            throw new RuntimeException("Email não registrado na plataforma.");
        }

        emailService.sendSimpleMailMessage(emailReceiver, "Your password","This is your password: " + userEmail.getSenha() );
    }



}