package org.example.cliente.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.time.LocalDate;

import org.example.cliente.entities.Transacao;

public class HomeController {

    // Campos de interface
    @FXML
    private TextField txtBusca;

    @FXML
    private Label lblSaldo;

    @FXML
    private Label lblReceitasMes;

    @FXML
    private Label lblGastosMes;

    @FXML
    private TableView<Transacao> tblTransacoes;

    @FXML
    private TableColumn<Transacao, LocalDate> colData;

    @FXML
    private TableColumn<Transacao, String> colDescricao;

    @FXML
    private TableColumn<Transacao, String> colCategoria;

    @FXML
    private TableColumn<Transacao, String> colTipo;

    @FXML
    private TableColumn<Transacao, Double> colValor;

    private final ObservableList<Transacao> listaTransacoes = FXCollections.observableArrayList();

    // Método chamado automaticamente após carregar o FXML
    @FXML
    public void initialize() {
        configurarTabela();
        carregarTransacoes();
        atualizarResumo();
    }
    public void setStageSize(double width, double height) {
        Stage stage = (Stage) lblSaldo.getScene().getWindow();
        if (stage != null) {
            stage.setWidth(width);
            stage.setHeight(height);
        }
    }

    private void configurarTabela() {
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        tblTransacoes.setItems(listaTransacoes);
    }

    private void carregarTransacoes() {
        // Exemplo de dados fictícios
        listaTransacoes.addAll(
            new Transacao(LocalDate.now(), "Salário", "Receita", "Entrada", 3500.00),
            new Transacao(LocalDate.now().minusDays(2), "Supermercado", "Alimentação", "Saída", 280.50),
            new Transacao(LocalDate.now().minusDays(5), "Internet", "Serviços", "Saída", 120.00)
        );
    }

    private void atualizarResumo() {
        double totalReceitas = listaTransacoes.stream()
                .filter(t -> t.getTipo().equalsIgnoreCase("Entrada"))
                .mapToDouble(Transacao::getValor)
                .sum();

        double totalGastos = listaTransacoes.stream()
                .filter(t -> t.getTipo().equalsIgnoreCase("Saída"))
                .mapToDouble(Transacao::getValor)
                .sum();

        double saldo = totalReceitas - totalGastos;

        lblReceitasMes.setText(String.format("R$ %.2f", totalReceitas));
        lblGastosMes.setText(String.format("R$ %.2f", totalGastos));
        lblSaldo.setText(String.format("R$ %.2f", saldo));
    }

    // Métodos de navegação
    @FXML
    private void navDashboard(ActionEvent event) {
        System.out.println("Ir para Dashboard...");
    }

    @FXML
    private void navTransacoes(ActionEvent event) {
        System.out.println("Ir para Transações...");
    }

    @FXML
    private void navCategorias(ActionEvent event) {
        System.out.println("Ir para Categorias...");
    }

    @FXML
    private void navRelatorios(ActionEvent event) {
        System.out.println("Ir para Relatórios...");
    }

    @FXML
    private void navConfiguracoes(ActionEvent event) {
        System.out.println("Ir para Configurações...");
    }

    // Botões principais
    @FXML
    private void novaTransacao(ActionEvent event) {
        
        // Cria um diálogo modal simples
        Dialog<Transacao> dialog = new Dialog<>();
        dialog.setTitle("Nova Transação");
        dialog.setHeaderText("Preencha os dados da nova transação");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Campos da tela
        DatePicker dpData = new DatePicker(LocalDate.now());
        TextField txtDescricao = new TextField();
        ComboBox<String> cbCategoria = new ComboBox<>(FXCollections.observableArrayList(
                "Salário", "Alimentação", "Serviços", "Transporte", "Lazer", "Outros"
        ));
        ComboBox<String> cbTipo = new ComboBox<>(FXCollections.observableArrayList("Entrada", "Saída"));
        TextField txtValor = new TextField();

        // Layout (organização dos campos)
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(0, new Label("Data:"), dpData);
        grid.addRow(1, new Label("Descrição:"), txtDescricao);
        grid.addRow(2, new Label("Categoria:"), cbCategoria);
        grid.addRow(3, new Label("Tipo:"), cbTipo);
        grid.addRow(4, new Label("Valor (R$):"), txtValor);

        dialog.getDialogPane().setContent(grid);

        // Define como o resultado será criado
        dialog.setResultConverter(botao -> {
            if (botao == ButtonType.OK) {
                try {
                    double valor = Double.parseDouble(txtValor.getText().replace(",", "."));
                    return new Transacao(
                            dpData.getValue(),
                            txtDescricao.getText().trim(),
                            cbCategoria.getValue(),
                            cbTipo.getValue(),
                            valor
                    );
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Verifique os campos preenchidos.", ButtonType.OK);
                    alert.showAndWait();
                    return null;
                }
            }
            return null;
        });

        // Exibe a janela e trata o resultado
        dialog.showAndWait().ifPresent(nova -> {
            listaTransacoes.add(0, nova);
            tblTransacoes.refresh();
            atualizarResumo();
        });
    }

    @FXML
    private void exportarTransacoes(ActionEvent event) {
        System.out.println("Exportando transações...");
    }

    @FXML
    private void abrirNotificacoes(ActionEvent event) {
        System.out.println("Abrindo notificações...");
    }

    @FXML
    private void sair(ActionEvent event) {
        System.out.println("Encerrando sessão...");
        System.exit(0);
    }
}
