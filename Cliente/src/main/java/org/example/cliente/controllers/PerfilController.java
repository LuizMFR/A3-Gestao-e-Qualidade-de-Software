package org.example.cliente.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.function.Consumer;

import org.example.cliente.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.cliente.entities.User;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class PerfilController {

    @Autowired
    private UserService userService = new UserService();
    
    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    public User userLoggedIn;

    @FXML
    private void initialize() {
        // Inicializações se necessárias
    }

    

    // Campos para os dados de perfil
    @FXML private TextField textfieldNome;
    @FXML private TextField textfieldSobrenome;
    @FXML private TextField textfieldEmail;
    @FXML private TextField textfieldProfissao;
    @FXML private PasswordField passwordfieldSenha;
    @FXML private DatePicker datepickerNascimento;

    @FXML
    private Button botaoSair;

    // Botão para salvar as alterações
    @FXML private Button btnSalvar;

    public void setUserLoggedIn(User user) {
        this.userLoggedIn = user;
        // Carregar os dados do usuário no formulário
        textfieldNome.setText(user.getNome());
        textfieldSobrenome.setText(user.getSobrenome());
        textfieldEmail.setText(user.getEmail());
        textfieldProfissao.setText(user.getProfissao());
        passwordfieldSenha.setText(user.getSenha());
        if (user.getNascimento() != null) {
            datepickerNascimento.setValue(user.getNascimento());
        }
    }

    private Consumer<User> onUserUpdated;

    public void setOnUserUpdated(Consumer<User> callback) {
        this.onUserUpdated = callback;
    }

    // Método chamado quando o botão "Salvar Alterações" é pressionado
    @FXML
    public void salvarPerfil(ActionEvent event) {
        String nome = textfieldNome.getText();
        String sobrenome = textfieldSobrenome.getText();
        String email = textfieldEmail.getText();
        String profissao = textfieldProfissao.getText();
        String senha = passwordfieldSenha.getText();
        LocalDate nascimento = datepickerNascimento.getValue();

        if (nome.isEmpty()) {
            mostrarAlerta("Erro de Validação", "O campo Nome não pode estar vazio.");
            return;
        }
        if (sobrenome.isEmpty()) {
            mostrarAlerta("Erro de Validação", "O campo Sobrenome não pode estar vazio.");
            return;
        }
        if (email.isEmpty()) {
            mostrarAlerta("Erro de Validação", "O campo Email não pode estar vazio.");
            return;
        }
        if (profissao.isEmpty()) {
            mostrarAlerta("Erro de Validação", "O campo Profissão não pode estar vazio.");
            return;
        }
        if (senha.isEmpty()) {
            mostrarAlerta("Erro de Validação", "O campo Senha não pode estar vazio.");
            return;
        }
        if (nascimento == null) {
            mostrarAlerta("Erro de Validação", "O campo Nascimento não pode estar vazio.");
            return;
        }

        if (!EMAIL_REGEX.matcher(email).matches()) {
            mostrarAlerta("Erro de Validação", "O formato do Email é inválido.");
            return;
        }

        if (senha.length() < 6) {
            mostrarAlerta("Erro de Validação", "A senha deve ter pelo menos 6 caracteres.");
            return;
        }

        if (nascimento.isAfter(LocalDate.now())) {
            mostrarAlerta("Erro de Validação", "A data de nascimento não pode ser no futuro.");
            return;
        }

        if (userLoggedIn == null) {
            mostrarAlerta("Erro", "Nenhum usuário está logado.");
            return;
        }

        if (nome.equals(userLoggedIn.getNome()) &&
            sobrenome.equals(userLoggedIn.getSobrenome()) &&
            email.equals(userLoggedIn.getEmail()) &&
            profissao.equals(userLoggedIn.getProfissao()) &&
            senha.equals(userLoggedIn.getSenha()) &&
            nascimento.equals(userLoggedIn.getNascimento())) {
            mostrarAlerta("Nenhuma Alteração", "Nenhum dado foi alterado.");
            return;
        }

        User updatedUser = new User(
            userLoggedIn.getId(),
            nome,
            sobrenome,
            email,
            senha,
            profissao,
            nascimento
        );

        try{
            updatedUser = userService.updateUser(updatedUser);
        }catch(Exception e) {
            mostrarAlerta("Erro", "Não foi possível atualizar o perfil: " 
            + e.getMessage());
            
        }

        if(updatedUser != null){
            userLoggedIn = updatedUser;
        } else {
            mostrarAlerta("Erro", "Não foi possível atualizar o perfil.");
            return;
        }

        if (onUserUpdated != null) {
            onUserUpdated.accept(updatedUser);
        }

        mostrarAlerta("Perfil Atualizado", "As alterações foram salvas com sucesso!");
    }


    @FXML
    private void sair(ActionEvent event) {
        try {
            Stage stage = (Stage) botaoSair.getScene().getWindow();
            stage.close();


        } catch (Exception e) {
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

