package org.example.cliente.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.util.regex.Pattern;


import org.example.cliente.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class CadastroController {

    @FXML private TextField txtNome;
    @FXML private TextField txtSobrenome;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtSenha;
    @FXML private PasswordField txtConfirmar;
    @FXML private TextField txtProfissao;
    @FXML private DatePicker dpNascimento;
    @FXML private Label lblMensagem;
    @FXML private Button btnCadastrar;
    @FXML private Button btnCancelar;
    @FXML private Label lblErro;

    @Autowired
    private UserService userService = new UserService();

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    @FXML
    private void initialize() {
        lblMensagem.setText("");
        // Limpa mensagens ao digitar
        txtNome.textProperty().addListener((o, a, b) -> clearMsg());
        txtSobrenome.textProperty().addListener((o, a, b) -> clearMsg());
        txtEmail.textProperty().addListener((o, a, b) -> clearMsg());
        txtSenha.textProperty().addListener((o, a, b) -> clearMsg());
        txtConfirmar.textProperty().addListener((o, a, b) -> clearMsg());
        txtProfissao.textProperty().addListener((o, a, b) -> clearMsg());
        txtProfissao.textProperty().addListener((o, a, b) -> clearMsg());
        dpNascimento.valueProperty().addListener((o, a, b) -> clearMsg());
        btnCadastrar.setOnAction(e -> CriarConta());
        btnCancelar.setOnAction(e -> Cancelar());
    }   

    private void clearMsg() { lblMensagem.setText(""); }

    @FXML
    private void CriarConta() {
        String nome = safe(txtNome.getText());
        String sobrenome = safe(txtSobrenome.getText());
        String email = safe(txtEmail.getText());
        String senha = safe(txtSenha.getText());
        String confirmar = safe(txtConfirmar.getText());
        String profissao = safe(txtProfissao.getText());
        LocalDate nascimento = dpNascimento.getValue();

        // Validações simples
        if (nome.isEmpty()) {
            erro("Informe seu nome");
            return;
        }
        if (sobrenome.isEmpty()) {
            erro("Informe seu sobrenome.");
            return;
        }
        if (email.isEmpty() || !EMAIL_REGEX.matcher(email).matches()) {
            erro("E-mail inválido.");
            return;
        }
        if (senha.length() < 6) {
            erro("A senha deve ter pelo menos 6 caracteres.");
            return;
        }
        if (!senha.equals(confirmar)) {
            erro("As senhas não conferem.");
            return;
        }
            if (profissao.isEmpty()) {
            erro("Você precisa informar sua profissão.");
            return;
        }
        if (nascimento == null) {
            erro("Informe a data de nascimento.");
            
            return;
        }


        // Mensagem de sucesso
        lblMensagem.getStyleClass().remove("error");
        if (!lblMensagem.getStyleClass().contains("success"))
            lblMensagem.getStyleClass().add("success");
        lblMensagem.setText("Conta criada com sucesso!");

        if(userService.criarConta(nome, sobrenome, email, senha, profissao, nascimento)){
            Cancelar();
        } else {
            erro("Não foi possível criar a conta.");
            Cancelar();
        }
    }



    @FXML
    private void Cancelar() {
        btnCancelar.getScene().getWindow().hide();
    }

    private void erro(String msg) {
        lblMensagem.getStyleClass().remove("success");
        if (!lblMensagem.getStyleClass().contains("error"))
            lblMensagem.getStyleClass().add("error");
        lblMensagem.setText(msg);
    }

    private String safe(String s) { return s == null ? "" : s.trim(); }
}
