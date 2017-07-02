package com.code.bankingaccount.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

import lombok.Getter;

/**
 * Created by marinatoledo on 19/02/17.
 */
@Entity
@Getter
public class User {

    @GeneratedValue
    @Id
    private Long id;

    private String email;

    private String senha;

    private String rg;

    private String cpf;

    private String nomeCompleto;

    private String telefone;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataFinalizacao;

    public User(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public User(String email, String senha, String rg, String cpf, String nomeCompleto, String telefone) {
        this.email = email;
        this.senha = senha;
        this.rg = rg;
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
    }

}
