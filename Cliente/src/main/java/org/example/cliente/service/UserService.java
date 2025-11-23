package org.example.cliente.service;

import org.example.cliente.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.example.cliente.entities.User;


@Service
public class UserService {
    private UserRepository userRepository = new UserRepository(); 

    private User loggedInUser;
    
    public boolean validarCredenciais(String gmail, String password) {

        User user  = userRepository .validarCredenciais(gmail, password);
       
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

}
