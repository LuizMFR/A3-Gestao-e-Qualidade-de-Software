package com.example.Servidor.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.Servidor.entities.User;
import com.example.Servidor.repositories.UserRepository;

/* SERVICE É A CAMADA INTERMEDIÁRIA QUE FAZ AS VERIFICAÇÕES DOS INPUTS
 * DO CONTROLER E ACIONA OS COMANDOS DO REPOSITÓRIO
 * 
 * A LÓGICA ESTÁ AQUI
 */

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean existsById(Integer id) {
        return userRepository.existsById(id);
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean authenticated(String email, String senha) {
        return userRepository.findByEmailAndSenha(email, senha);
    }

    public User createUser(User user) {
        if (user == null) {
            return null;

        }
        
        return userRepository.save(user);
    }


}
