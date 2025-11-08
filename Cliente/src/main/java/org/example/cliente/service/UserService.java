package org.example.cliente.service;

import org.example.cliente.repository.UserRepository;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UserService {
    private UserRepository userRepository = new UserRepository(); 
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField senhaTextField;

    @FXML
    public void validarCredenciais() {
        String email = emailTextField.getText();
        String senha = emailTextField.getText();
        userRepository.validarCredenciais(email, senha);
        

    }

}
