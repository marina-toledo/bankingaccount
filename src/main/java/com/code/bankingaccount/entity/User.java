package com.code.bankingaccount.entity;

import javax.persistence.*;
import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by marinatoledo on 19/02/17.
 */
@Entity
@Setter
@Getter
public class User {

    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "email",unique=true)
    private String email;

    @Column(name = "password")
    private String senha;

    @Column(name = "rg",unique=true)
    private String rg;

    @Column(name = "cpf",unique=true)
    private String cpf;

    @Column(name = "fullname")
    private String nomeCompleto;

    @Column(name = "phonenumber")
    private String telefone;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataFinalizacao;

    private User() {
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
