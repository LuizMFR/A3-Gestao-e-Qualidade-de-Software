package org.example.cliente.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

// imports novos:
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PerfilController {

    // Campos para os dados de perfil
    @FXML private TextField textfieldNome;
    @FXML private TextField textfieldSobrenome;
    @FXML private TextField textfieldEmail;
    @FXML private TextField textfieldProfissao;
    @FXML private PasswordField passwordfieldSenha;
    @FXML private DatePicker datepickerNascimento;

    // Botão para salvar as alterações
    @FXML private Button btnSalvar;

    // Método chamado quando o botão "Salvar Alterações" é pressionado
    @FXML
    public void salvarPerfil(ActionEvent event) {
        String nome = textfieldNome.getText();
        String sobrenome = textfieldSobrenome.getText();
        String email = textfieldEmail.getText();
        String profissao = textfieldProfissao.getText();
        String senha = passwordfieldSenha.getText();
        String nascimento = datepickerNascimento.getValue() != null
                ? datepickerNascimento.getValue().toString()
                : "Não informado";

        if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty()) {
            mostrarAlerta("Campos obrigatórios", "Nome, Sobrenome e Email são obrigatórios.");
            return;
        }

        // Aqui entraria a lógica de salvar esses dados em banco

        mostrarAlerta("Perfil Atualizado", "As alterações foram salvas com sucesso!");
    }

    // Botão Sair-> volta para Home (home.fxml)
    @FXML
    private void sair(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/cliente/view/home.fxml")
            );
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("PerFin - Dashboard");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Não foi possível voltar para a tela inicial (Home).");
        }
    }

    // Método para exibir alertas
    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}

