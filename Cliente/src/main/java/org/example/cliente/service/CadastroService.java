package org.example.cliente.service;

import java.time.LocalDate;

public class CadastroService {

    public void criarConta(String nome, String sobrenome, String email, String senha, String profissao, LocalDate nascimento) {
        System.out.println("Conta criada!");
        System.out.println("Nome: " + nome + " " + sobrenome);
        System.out.println("Email: " + email);
        System.out.println("Profissão: " + profissao);
        System.out.println("Nascimento: " + nascimento);
    }
}
