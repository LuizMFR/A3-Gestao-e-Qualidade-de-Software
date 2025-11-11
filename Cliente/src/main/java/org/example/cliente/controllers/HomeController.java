package org.example.cliente.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
        System.out.println("Abrir tela de nova transação...");
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
