package org.example.cliente.entities;

import java.time.LocalDate;

public class User {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String profissao;
    private LocalDate nascimento; 



    public User() {}

    public User(String nome, String sobrenome, String email, String senha,
                String profissao, LocalDate nascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.profissao = profissao;
        this.nascimento = nascimento;
    }

    public User(Integer id, String nome, String sobrenome, String email, String senha,
                String profissao, LocalDate nascimento) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.profissao = profissao;
        this.nascimento = nascimento;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getProfissao() { return profissao; }
    public void setProfissao(String profissao) { this.profissao = profissao; }

    public LocalDate getNascimento() { return nascimento; }
    public void setNascimento(LocalDate nascimento) { this.nascimento = nascimento; }

}   


