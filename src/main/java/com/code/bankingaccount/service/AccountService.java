package com.code.bankingaccount.service;

import com.code.bankingaccount.entity.User;
import com.code.bankingaccount.entity.UserRepository;
import com.code.bankingaccount.helper.ValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by marinatoledo on 02/07/17.
 */
@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ValidatorHelper validatorHelper;


    public void login(String user, String password){
        User userEmail = userRepository.findByEmailAndSenha(user, password);

        if ( userEmail == null ){
            throw new RuntimeException("Login Falhou. Usuário ou senha não válido(s).");
        }
    }

    /**
     * If the email is valid and it is registered, it sends the password to the email.
     * If not a RunTimeException is thrown (if it is not validated or is not registered)
     *
     * @param emailReceiver
     */
    public void sendPasswordEmail(String emailReceiver) {
        validatorHelper.emailIsValid(emailReceiver);

        User userEmail = userRepository.findByEmail(emailReceiver);

        if ( userEmail == null ){
            throw new RuntimeException("Email não registrado na plataforma.");
        }

        emailService.sendSimpleMailMessage(emailReceiver, "Your password","This is your password: " + userEmail.getSenha() );
    }

    /**
     * If all parameters are valid, it generates a new password and a new account.
     * And then send a welcome email with the created password for the new user.
     *
     * @param email
     * @param rg
     * @param cpf
     * @param nomeCompleto
     * @param telefone
     */
    public void registerUser( String email, String rg, String cpf,String nomeCompleto, String telefone){
        validatorHelper.emailIsValid(email);
        validatorHelper.rgIsValid(rg);
        validatorHelper.cpfIsValid(cpf);
        validatorHelper.fullNameIsValid(nomeCompleto);
        validatorHelper.phoneNumberIsValid(telefone);

        String senha = gerarSenha();
        User newUser = new User( email, senha, rg, cpf, nomeCompleto, telefone);
        userRepository.save(newUser);

        emailService.sendSimpleMailMessage(email, "Welcome!","Use this password to access your new account: " + senha );
    }

    private static String gerarSenha() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

}