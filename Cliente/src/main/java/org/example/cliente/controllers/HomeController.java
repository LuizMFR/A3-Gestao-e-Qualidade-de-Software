package org.example.cliente.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.net.URL;
import java.io.IOException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.example.cliente.entities.Transacao;
import org.example.cliente.entities.TransacaoView;
import org.example.cliente.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.cliente.entities.User;
import org.example.cliente.entities.Categoria;
import org.example.cliente.controllers.CategoriaController;

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
    private TableView<TransacaoView> tblTransacoes;

    @FXML
    private TableColumn<TransacaoView, LocalDate> colData;

    @FXML
    private TableColumn<TransacaoView, String> colDescricao;

    @FXML
    private TableColumn<TransacaoView, String> colCategoria;

    @FXML
    private TableColumn<TransacaoView, String> colTipo;

    @FXML
    private TableColumn<TransacaoView, Double> colValor;

    @FXML
    private Button btnTransacao;

    private final ObservableList<TransacaoView> listaTransacoes = FXCollections.observableArrayList();
    private final ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList();
    
    private User userLoggedIn;

    @Autowired
    private HomeService homeService = new HomeService();
    // Método chamado automaticamente após carregar o FXML
    @FXML
    public void initialize() {
        
    }

    public void setUserLoggedIn(User user) {
        this.userLoggedIn = user;
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
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaDesc"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        // Formatar a data no padrão brasileiro
        colData.setCellFactory(column -> new TableCell<TransacaoView, LocalDate>() {
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
        List<Transacao> transacoes = homeService.getAllTransacoes(userLoggedIn.getId());
        List<Categoria> categorias = homeService.getAllCategorias(userLoggedIn.getId());

        // evita NPEs
        if (transacoes == null || transacoes.isEmpty()) {
            System.out.println("Nenhuma transação encontrada para o usuário ID: " + userLoggedIn.getId());
            listaTransacoes.clear();
            tabelaAtualizarUI();
            return;
        }

        if (categorias == null) {
            System.out.println("Lista de categorias veio nula — usando lista vazia.");
            categorias = List.of();
        }

        // Cria mapa categoriaId -> descricao evitando loops aninhados
        Map<Integer, String> mapaCategorias = categorias.stream()
                .collect(Collectors.toMap(
                        Categoria::getId,
                        c -> {
                            try {
                                String d = c.getDescricao();
                                if (d != null && !d.isBlank()) return d;
                            } catch (Throwable ex) {  }

                            try {
                                
                                String n = c.getDescricao();
                                if (n != null && !n.isBlank()) return n;
                            } catch (Throwable ex) { /* ignora */ }

                            return "Categoria desconhecida";
                        }
                ));

        listaTransacoes.clear();
        listaCategorias.clear();

        for (Categoria c : categorias) {
            System.out.println("DEBUG Categoria -> ID: " + c.getId() + ", Descrição: '" + c.getDescricao() + "'");
            listaCategorias.add(c);
        }

        for (Transacao t : transacoes) {
            String descCategoria = mapaCategorias.getOrDefault(t.getCategoriaId(), "Categoria desconhecida");

            // monta a TransacaoView na ordem: id, descricao, categoriaDescricao, tipo, valor, data
            TransacaoView view = new TransacaoView(
                    t.getId(),
                    descCategoria,
                    t.getDescricao(),
                    t.getDataTransacao(),
                    t.getValor(),
                    t.getTipo()
            );

            listaTransacoes.add(view);
        }

        tabelaAtualizarUI();
        atualizarResumo();
    }

// helper que força a atualização da UI (evitar problemas se a tabela não estiver configurada ainda)
private void tabelaAtualizarUI() {
    if (tblTransacoes != null) {
        tblTransacoes.setItems(listaTransacoes);
        tblTransacoes.refresh();
    }
}

    private void atualizarResumo() {
        double totalReceitas = listaTransacoes.stream()
               .filter(t -> "receita".equalsIgnoreCase(t.getTipo()))
                .mapToDouble(TransacaoView::getValor)
                .sum();

        double totalGastos = listaTransacoes.stream()
                .filter(t -> "custo".equalsIgnoreCase(t.getTipo()))
                .mapToDouble(TransacaoView::getValor)
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cliente/view/transacao.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Transações");
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Não foi possível abrir a tela de Transações.").showAndWait();
        }
    }

    @FXML
    private void navCategorias(ActionEvent event) {
          try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("/org/example/cliente/view/categoria.fxml"  )
            );
            URL fxmlURL = getClass().getResource("/org/example/cliente/view/categoria.fxml");
            System.out.println("Debug -> " + fxmlURL);
            Parent root = null;
            
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            CategoriaController categoriaController = loader.getController();
            categoriaController.setUserLoggedIn(userLoggedIn);
            
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Categorias");
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();

        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Não foi possível abrir a tela de Categorias.").showAndWait();//teste
        }
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
        
    carregarTransacoes(); // garante que categorias estão carregadas

    Dialog<Transacao> dialog = new Dialog<>();
    dialog.setTitle("Nova Transação");
    dialog.setHeaderText("Preencha os dados da nova transação");
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    
    
    DatePicker dpData = new DatePicker();
    TextField txtDescricao = new TextField();
    ComboBox<String> cbCategoria = new ComboBox<>(FXCollections.observableArrayList(
            listaCategorias.stream().map(Categoria::getDescricao).collect(Collectors.toList())));
    ComboBox<String> cbTipo = new ComboBox<>(FXCollections.observableArrayList("receita","custo"));
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

                Double valor = Double.parseDouble(txtValor.getText().replace(",", "."));


                    System.out.println("------------------------");
                    System.out.println(cbTipo.getValue());
                    System.out.println("------------------------------------");

                List<Categoria> userCategorias = homeService.getAllCategorias(userLoggedIn.getId());
                int cbCategoriaID = homeService.getCategoriaIdByDesc(userCategorias,cbCategoria.getValue().strip());
                System.out.println("DEBUG Categoria selecionada: " + cbCategoria.getValue() + " -> ID: " + cbCategoriaID);

                if (cbCategoriaID == -1) {
                    new Alert(Alert.AlertType.ERROR, "Categoria inválida. Selecione uma categoria existente.", ButtonType.OK).showAndWait();
                    return null;
                }
                return new Transacao(
                        userLoggedIn.getId(),
                        cbCategoriaID,
                        descricao,
                        dpData.getValue(),
                        (Double) valor,
                        cbTipo.getValue()
                );
            } catch (NumberFormatException nfe) {
                new Alert(Alert.AlertType.ERROR, "Valor inválido.", ButtonType.OK).showAndWait();
                return null;
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Verifique os campos preenchidos.", ButtonType.OK).showAndWait();
                return null;
            }
        }
            
            return null;
        });

        // CHAME showAndWait() APENAS UMA VEZ e reutilize o resultado
        Optional<Transacao> resultado = dialog.showAndWait();

        resultado.ifPresent(transacao -> {
        
        System.out.println("DEBUG Transacao -> "+
        "usuarioID: " + transacao.getUsuarioId() +
        "categoriaID: " + transacao.getCategoriaId() +
        "descricao: '" + transacao.getDescricao() +
                "', data: " + transacao.getDataTransacao() +
                ", categoria: " + transacao.getCategoriaId() +
                ", tipo: " + transacao.getTipo() +
                ", valor: " + transacao.getValor());

        // salvar no backend
        homeService.salvarTransacao(transacao);
        tblTransacoes.refresh();
        carregarTransacoes();
        
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
