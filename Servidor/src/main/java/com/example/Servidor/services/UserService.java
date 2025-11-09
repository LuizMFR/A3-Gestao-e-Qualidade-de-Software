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

    public User getUserById(Integer id){
        return userRepository.findById(id).get();
    }

    public boolean existsById(Integer id) {
        return userRepository.existsById(id);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean authenticated(String email, String senha) {
        return userRepository.existsByEmailAndSenha(email, senha);
    }

    public User createUser(User user){

        if (this.existsByEmail(user.getEmail())){
            return null;
        }
        
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User newUserData){

        if (this.existsByEmail(newUserData.getEmail())){
            return null;
        }

        User newUser = newUserData;
        newUser.setId(id);

        return userRepository.save(newUser);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

}