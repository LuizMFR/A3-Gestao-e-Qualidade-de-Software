package org.example.cliente.controllers;

import java.io.IOException;

import org.example.cliente.repository.UserRepository;
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
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtSenha;
    @FXML private CheckBox chkLembrar;     // opcional
    @FXML private Label lblMensagem;       // feedback
    @FXML private Button btnEntrar;
    @Autowired
    UserService userService;


    @FXML
    private void initialize() {
        // Foco inicial no usuário
        txtUsuario.requestFocus();
        // Limpa mensagem ao digitar
        txtUsuario.textProperty().addListener((obs, o, n) -> lblMensagem.setText(""));
        txtSenha.textProperty().addListener((obs, o, n) -> lblMensagem.setText(""));
        btnEntrar.setOnAction(e -> entrar());
    }

    @FXML
    private void entrar() {
        String usuario = txtUsuario.getText() != null ? txtUsuario.getText().trim() : "";
        String senha = txtSenha.getText() != null ? txtSenha.getText() : "";

        if (usuario.isEmpty()) {
            lblMensagem.setText("Informe o usuário.");
            txtUsuario.requestFocus();
            return;
        }
        if (senha.isEmpty()) {
            lblMensagem.setText("Informe a senha.");
            txtSenha.requestFocus();
            return;
        }
        try {
            validarCredenciais(txtUsuario.getText().trim(), txtSenha.getText().trim());
        } catch (IOException e) {
            lblMensagem.setText("Erro ao carregar a tela principal.");
            e.printStackTrace();
        }
    }

    private void validarCredenciais(String usuario, String senha) throws IOException {
        // Fecha a aplicação ou limpa os campos
        if (userService.validarCredenciais(usuario, senha)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cliente/view/home.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Home");
            stage.setScene(new Scene(root));
            stage.show();
            Stage loginStage = (Stage) txtSenha.getScene().getWindow();
            loginStage.close();

            
        } else {
            lblMensagem.setText("Usuário ou senha inválidos.");
            txtSenha.clear();
            txtSenha.requestFocus();
        }
    }
}
