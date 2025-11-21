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
import java.util.Date;
import java.util.Optional;

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
        carregarTransacoes();
        configurarTabela();
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
        colData.setCellValueFactory(new PropertyValueFactory<>("dataTransacao"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        // Formatar a data no padrão brasileiro
        colData.setCellFactory(column -> new TableCell<Transacao, LocalDate>() {
            @Override
            protected void updateItem(LocalDate data, boolean empty) {
                super.updateItem(data, empty);
            
                if (empty || data == null) {
                    setText(null);
                } else {
                    setText(data.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
            }
        });

        tblTransacoes.setItems(listaTransacoes);
    }

    private void carregarTransacoes() {
        // Exemplo de dados fictícios
        listaTransacoes.addAll(
            new Transacao(LocalDate.now(), "Salário", "Receita", "Entrada", 3500.00),
            new Transacao(LocalDate.now(), "Supermercado", "Alimentação", "Saída", 280.50),
            new Transacao(LocalDate.now(), "Internet", "Serviços", "Saída", 120.00 )
        );
    }

    private void atualizarResumo() {
        double totalReceitas = listaTransacoes.stream()
               .filter(t -> "entrada".equalsIgnoreCase(t.getTipo()))
                .mapToDouble(Transacao::getValor)
                .sum();

        double totalGastos = listaTransacoes.stream()
                .filter(t -> "saída".equalsIgnoreCase(t.getTipo()))
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
          try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("/org/example/cliente/view/categoria.fxml"  )
            );

            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Categorias");
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();

        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Não foi possível abrir a tela de Categorias.").showAndWait();//teste
        }//teste
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

    Dialog<Transacao> dialog = new Dialog<>();
    dialog.setTitle("Nova Transação");
    dialog.setHeaderText("Preencha os dados da nova transação");
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    DatePicker dpData = new DatePicker();
    TextField txtDescricao = new TextField();
    ComboBox<String> cbCategoria = new ComboBox<>(FXCollections.observableArrayList(
            "Salário", "Alimentação", "Serviços", "Transporte", "Lazer", "Outros"
    ));
    ComboBox<String> cbTipo = new ComboBox<>(FXCollections.observableArrayList("Entrada", "Saída"));
    TextField txtValor = new TextField();

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.addRow(0, new Label("Data:"), dpData);
    grid.addRow(1, new Label("Descrição:"), txtDescricao);
    grid.addRow(2, new Label("Categoria:"), cbCategoria);
    grid.addRow(3, new Label("Tipo:"), cbTipo);
    grid.addRow(4, new Label("Valor (R$):"), txtValor);

    dialog.getDialogPane().setContent(grid);

    dialog.setResultConverter(botao -> {
        if (botao == ButtonType.OK) {
            try {
                // validação básica
                String descricao = txtDescricao.getText();
                if (descricao == null || descricao.trim().isEmpty()) {
                    new Alert(Alert.AlertType.ERROR, "Descrição não pode ficar vazia.", ButtonType.OK).showAndWait();
                    return null;
                }

                float valor = Float.parseFloat(txtValor.getText().replace(",", "."));

                // Atenção: certifique-se de que a ordem abaixo bate com o construtor de Transacao
                return new Transacao(
                        dpData.getValue(),
                        descricao,
                        cbCategoria.getValue(),
                        cbTipo.getValue(),
                        (double) valor
                );
            } catch (NumberFormatException nfe) {
                new Alert(Alert.AlertType.ERROR, "Valor inválido.", ButtonType.OK).showAndWait();
                return null;
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Verifique os campos preenchidos.", ButtonType.OK).showAndWait();
                return null;
            }
        }
            atualizarResumo();
            return null;
        });

        // CHAME showAndWait() APENAS UMA VEZ e reutilize o resultado
        Optional<Transacao> resultado = dialog.showAndWait();

        resultado.ifPresent(transacao -> {
        // debug rápido: mostre todos os campos para conferência
        System.out.println("DEBUG Transacao -> descricao: '" + transacao.getDescricao() +
                "', data: " + transacao.getDataTransacao() +
                ", categoria: " + transacao.getCategoria() +
                ", tipo: " + transacao.getTipo() +
                ", valor: " + transacao.getValor());

        // adiciona na lista e atualiza UI
        listaTransacoes.add(0, transacao);
        tblTransacoes.refresh();
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
