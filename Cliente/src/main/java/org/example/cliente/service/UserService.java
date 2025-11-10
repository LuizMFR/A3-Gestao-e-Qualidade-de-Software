package org.example.cliente.service;

import org.example.cliente.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private UserRepository userRepository = new UserRepository(); 
    
    public boolean validarCredenciais(String gmail, String password) {
        return userRepository.validarCredenciais(gmail, password);
    }


}
