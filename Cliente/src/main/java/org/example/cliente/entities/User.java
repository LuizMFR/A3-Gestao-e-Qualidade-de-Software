package org.example.cliente.entities;

import java.util.Date;

public class User {

    //Iniciando as variaveis 
    private int id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String profissao;
    private Date nascimento;
    
    //Iniciando o Construtor
    public User(String nome, String sobrenome, String email, String senha, String profissao, Date nascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.profissao = profissao;
        this.nascimento = nascimento;
    }   

    //Iniciando os Getters e Setters
    public int getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getSobrenome(){
        return sobrenome;
    }
    public void setSobrenome(String sobrenome){
        this.sobrenome = sobrenome;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getProfissao(){
        return profissao;
    }
    public void setProfissao(String profissao){
        this.profissao = profissao;
    }

    public Date getNascimento(){
        return nascimento;
    }
    public void setNascimento(Date nascimento){
        this.nascimento = nascimento;
    }
    
}





