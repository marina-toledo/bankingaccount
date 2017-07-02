package com.code.bankingaccount.helper;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.stereotype.Service;

/**
 * Created by marinatoledo on 02/07/17.
 */
@Service
public class ValidatorHelper {
    // todo checar qual eh o melhor jeito de validar todas as informacoes

    private final EmailValidator emailvalidator = EmailValidator.getInstance();

    private final RegexValidator fullNameValidator = new RegexValidator("^[\\p{L} .'-]+$"); // \\p{L} is a Unicode Character Property that matches any kind of letter from any language

    private final RegexValidator rgValidator = new RegexValidator("^[0-9]+$");

    private final RegexValidator cpfValidator = new RegexValidator("^[0-9]+$");

    private final RegexValidator phoneNumberValidator = new RegexValidator("^[0-9]+$");


    /**
     * @param email
     * @return true if email is valid and throw a RunTimeException if not
     */
    public Boolean emailIsValid(String email){
        if( emailvalidator.isValid(email) ){
            return true;
        }
        throw new RuntimeException("Email inválido.");
    }

    /**
     * @param rg
     * @return true if rg is valid and throw a RunTimeException if not
     */
    public Boolean rgIsValid(String rg){
        if( rgValidator.isValid(rg) ){
            return true;
        }
        throw new RuntimeException("Rg inválido.");
    }

    /**
     * @param cpf
     * @return true if cpf is valid and throw a RunTimeException if not
     */
    public Boolean cpfIsValid(String cpf){
        if( cpfValidator.isValid(cpf) ){
            return true;
        }
        throw new RuntimeException("CPF inválido.");
    }

    /**
     * @param fullName
     * @return true if fullName is valid and throw a RunTimeException if not
     */
    public Boolean fullNameIsValid(String fullName){
        if( fullNameValidator.isValid(fullName) ){
            return true;
        }
        throw new RuntimeException("Nome completo inválido.");
    }

    /**
     * @param phoneNumber
     * @return true if phoneNumber is valid and throw a RunTimeException if not
     */
    public Boolean phoneNumberIsValid(String phoneNumber){
        if( phoneNumberValidator.isValid(phoneNumber) ){
            return true;
        }
        throw new RuntimeException("Telefone inválido.");
    }

}
