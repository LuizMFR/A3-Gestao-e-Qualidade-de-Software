package org.example.cliente.controllers;

import java.io.IOException;


import org.example.cliente.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
@Controller
public class LogarController {
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtSenha;
    @FXML private CheckBox chkLembrar;     // opcional
    @FXML private Label lblMensagem;       // feedback
    @FXML private Button btnEntrar;
    @FXML private Button btnCadastro;
    @Autowired
    UserService userService;


    @FXML
    private void initialize() {
        // Foco inicial no email
        txtEmail.requestFocus();
        // Limpa mensagem ao digitar
        txtEmail.textProperty().addListener((obs, o, n) -> lblMensagem.setText(""));
        txtSenha.textProperty().addListener((obs, o, n) -> lblMensagem.setText(""));
        btnEntrar.setOnAction(e -> entrar());   
        btnCadastro.setOnAction(e -> cadastro());
    }

    
    @FXML
    private void entrar() {
        String email = txtEmail.getText() != null ? txtEmail.getText().trim() : "";
        String senha = txtSenha.getText() != null ? txtSenha.getText() : "";

        if (email.isEmpty()) {
            lblMensagem.setText("Informe o email.");
            txtEmail.requestFocus();
            return;
        }
        if (senha.isEmpty()) {
            lblMensagem.setText("Informe a senha.");
            txtSenha.requestFocus();
            return;
        }
        if (!email.matches(".*@.*")) {
            lblMensagem.setText("E-mail inválido. Deve conter '@'.");
            txtEmail.requestFocus();
            return;
        }
        try {
            validarCredenciais(txtEmail.getText().trim(), txtSenha.getText().trim());
        } catch (IOException e) {
            lblMensagem.setText("Erro ao carregar a tela principal.");
            e.printStackTrace();
        }
    }

    private void validarCredenciais(String email, String senha) throws IOException {
        // Fecha a aplicação ou limpa os campos
        if (userService.validarCredenciais(email, senha)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cliente/view/home.fxml"));
            Parent root = loader.load();
            
            HomeController ctrl = loader.getController();
            ctrl.setUserLoggedIn(userService.getLoggedInUser());

            Stage stage = (Stage) txtSenha.getScene().getWindow();
            stage.setTitle("Home");
            stage.setScene(new Scene(root));
            stage.show();
            

            
        } else {
            lblMensagem.setText("email ou senha inválidos.");
            txtSenha.clear();
            txtSenha.requestFocus();
        }
    }

    public void cadastro(){
         try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cliente/view/cadastro.fxml"));
                Parent root = loader.load();
                
                Stage stage = (Stage) btnCadastro.getScene().getWindow();
                stage.setTitle("Cadastro");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                lblMensagem.setText("Erro ao carregar a tela de cadastro.");
                ex.printStackTrace();
            }
    }
}
