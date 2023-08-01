package br.senai.sc.trunfo.security.model;

import lombok.Data;

@Data
public class Login {
    private String username;
    private String password;
}