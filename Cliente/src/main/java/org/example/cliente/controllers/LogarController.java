package org.example.cliente.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LogarController {
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtSenha;
    @FXML private CheckBox chkLembrar;     // opcional
    @FXML private Label lblMensagem;       // feedback

    @FXML
    private void initialize() {
        // Foco inicial no usuário
        txtUsuario.requestFocus();
        // Limpa mensagem ao digitar
        txtUsuario.textProperty().addListener((obs, o, n) -> lblMensagem.setText(""));
        txtSenha.textProperty().addListener((obs, o, n) -> lblMensagem.setText(""));
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

        // Validação simples (substitua pela sua lógica)
        if (usuario.equals("admin") && senha.equals("1234")) {
            lblMensagem.setText("");
            // TODO: trocar de tela, fechar janela, etc.
            System.out.println("Login OK | Lembrar: " + (chkLembrar != null && chkLembrar.isSelected()));
        } else {
            lblMensagem.setText("Usuário ou senha inválidos.");
        }
    }
}
