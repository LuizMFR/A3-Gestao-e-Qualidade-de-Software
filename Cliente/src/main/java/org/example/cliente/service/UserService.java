package org.example.cliente.service;

import org.example.cliente.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import org.example.cliente.entities.User;


@Service
public class UserService {
    private UserRepository userRepository = new UserRepository(); 

    private User loggedInUser;
    
    public boolean validarCredenciais(String gmail, String password) {

        User user  = userRepository.validarCredenciais(gmail, password);
       
        if (user != null) {
            loggedInUser = user;
            return true;
            
        }
        System.out.println("Usuário não encontrado ou credenciais inválidas.");
        return false;
    }

    public User getLoggedInUser(){
        return loggedInUser;
    }

    public boolean criarConta(String nome, String sobrenome, String email, String senha, String profissao, LocalDate nascimento) {
        
        System.out.println("Nome: " + nome + " " + sobrenome);
        System.out.println("Email: " + email);
        System.out.println("Profissão: " + profissao);
        System.out.println("Nascimento: " + nascimento);
        User user = new User(nome, sobrenome, email, senha, profissao, nascimento);
        if(userRepository.cadastrarUsuario(user)){
            System.out.println("Usuário cadastrado com sucesso.");
            return true;
        } else {
            System.out.println("Falha ao cadastrar o usuário.");
            return false;
        }
    }

    public User updateUser(Integer id,User user) {
        return userRepository.updateUser(id,user);
    }

   
}
