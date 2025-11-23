package org.example.cliente.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class CadastroController {

    @FXML private TextField txtNome;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtSenha;
    @FXML private PasswordField txtConfirmar;
    @FXML private TextField txtProfissao;
    @FXML private DatePicker dpNascimento;
    @FXML private Label lblMensagem;
    @FXML private Button btnCadastro;
    @FXML private Button btnCancelar;

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    @FXML
    private void initialize() {
        lblMensagem.setText("");
        // Limpa mensagens ao digitar
        txtNome.textProperty().addListener((o, a, b) -> clearMsg());
        txtEmail.textProperty().addListener((o, a, b) -> clearMsg());
        txtSenha.textProperty().addListener((o, a, b) -> clearMsg());
        txtConfirmar.textProperty().addListener((o, a, b) -> clearMsg());
        txtProfissao.textProperty().addListener((o, a, b) -> clearMsg());
        dpNascimento.valueProperty().addListener((o, a, b) -> clearMsg());
        btnCadastro.setOnAction(e -> criarConta());
        btnCancelar.setOnAction(e -> cancelar());
    }   

    private void clearMsg() { lblMensagem.setText(""); }

    @FXML
    private void criarConta() {
        String nome = safe(txtNome.getText());
        String email = safe(txtEmail.getText());
        String senha = safe(txtSenha.getText());
        String confirmar = safe(txtConfirmar.getText());
        String profissao = safe(txtProfissao.getText());
        LocalDate nascimento = dpNascimento.getValue();

        // Validações simples
        if (nome.isEmpty()) {
            erro("Informe o nome.");
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
        if (nascimento == null) {
            erro("Informe a data de nascimento.");
            return;
        }

        // Simulação de “salvar”
        System.out.printf("Novo cadastro: %s | %s | %s | %s | %s%n",
                nome, email, senha, profissao, nascimento);

        // Mensagem de sucesso
        lblMensagem.getStyleClass().remove("error");
        if (!lblMensagem.getStyleClass().contains("success"))
            lblMensagem.getStyleClass().add("success");
        lblMensagem.setText("Conta criada com sucesso!");

        // TODO: trocar de cena para tela principal ou voltar ao login
        // ex.: SceneNavigator.go("login.fxml");
    }

    @FXML
    private void cancelar() {
        // Exemplo simples: limpar campos
        txtNome.clear();
        txtEmail.clear();
        txtSenha.clear();
        txtConfirmar.clear();
        txtProfissao.clear();
        dpNascimento.setValue(null);
        lblMensagem.setText("");
        // Ou voltar para a tela de login, se desejar.
    }

    private void erro(String msg) {
        lblMensagem.getStyleClass().remove("success");
        if (!lblMensagem.getStyleClass().contains("error"))
            lblMensagem.getStyleClass().add("error");
        lblMensagem.setText(msg);
    }

    private String safe(String s) { return s == null ? "" : s.trim(); }
}
