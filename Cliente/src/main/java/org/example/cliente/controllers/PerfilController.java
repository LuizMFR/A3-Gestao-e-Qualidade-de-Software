package org.example.cliente.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

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
        // Coleta os valores inseridos nos campos de texto
        String nome = textfieldNome.getText();
        String sobrenome = textfieldSobrenome.getText();
        String email = textfieldEmail.getText();
        String profissao = textfieldProfissao.getText();
        String senha = passwordfieldSenha.getText();
        String nascimento = datepickerNascimento.getValue() != null ? datepickerNascimento.getValue().toString() : "Não informado";

        // Valida se todos os campos obrigatórios foram preenchidos
        if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty()) {
            mostrarAlerta("Campos obrigatórios", "Nome, Sobrenome e Email são obrigatórios.");
            return;
        }

        //Normalmente aqui colocamos a logica de salvar esses dados em um banco de dados. Olhar com o Leandro

        // Exibindo uma mensagem que deu certo
        mostrarAlerta("Perfil Atualizado", "As alterações foram salvas com sucesso!");
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
