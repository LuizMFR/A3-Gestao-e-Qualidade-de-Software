package org.example.cliente.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.example.cliente.entities.Categoria;
import org.example.cliente.entities.User;
import org.example.cliente.service.CategoriaService;
import org.example.cliente.service.HomeService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CategoriaController {

    User userLoggedIn;
    private final CategoriaService categoriaService = new CategoriaService();
    private final HomeService homeService = new HomeService();
    @FXML
    private TextField textfieldDesc;          // campo Título

    @FXML
    private TextField textfieldType;       // "tipo" ou descrição

    @FXML
    private TableView<Categoria> tableCategorias;

    @FXML
    private TableColumn<Categoria, Integer> tablecolId;

    @FXML
    private TableColumn<Categoria, String> tablecolTitulo;

    @FXML
    private TableColumn<Categoria, String> tablecolDescricao;

    @FXML
    private TableColumn<Categoria, Void> tablecolAcoes;

    @FXML
    private Button botaoSair;

    @FXML
    private ComboBox<String> comboTipo;

    private final ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
         comboTipo.getItems().addAll("Receita", "Custo");
    }

    public void setUserLoggedIn(User user) {
        this.userLoggedIn = user;
        carregarCategorias();
        configurarTabela();
    }

    public void configurarTabela(){
        tablecolId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablecolTitulo.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tablecolDescricao.setCellValueFactory(new PropertyValueFactory<>("tipo")); // usando tipo como descrição

        tableCategorias.setItems(listaCategorias);

        configurarColunaAcoes();
    }

    public void carregarCategorias(){
        List<Categoria> categorias = homeService.getAllCategorias(userLoggedIn.getId());

        listaCategorias.clear();

        for (Categoria c : categorias) {
            c.setDescricao(c.getDescricao().substring(0,1).toUpperCase() + c.getDescricao().substring(1).toLowerCase());
            c.setTipo(c.getTipo().substring(0,1).toUpperCase()+ c.getTipo().substring(1).toLowerCase());
            System.out.println("DEBUG Categoria -> ID: " + c.getId() + ", Descrição: '" + c.getDescricao() + "'");
            listaCategorias.add(c);
        }
    }

    @FXML
    private void cadastrarCategoria() {
        

        String titulo = textfieldDesc.getText().trim();
        String tipo = comboTipo.getValue().toLowerCase();

        if (titulo.isEmpty()) {
            mostrarAlerta("Validação", "Informe o título da categoria.");
            return;
        }

        Categoria categoria = new Categoria(titulo, tipo);
        categoria.setUsuarioId(userLoggedIn.getId());
        categoriaService.salvarCategoria(categoria);
        carregarCategorias();
        tableCategorias.refresh();

        textfieldDesc.clear();
    }

    //Botão "Sair" -> voltar para Home
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

    //Configura a coluna Ações com botão "Excluir"
    private void configurarColunaAcoes() {
        Callback<TableColumn<Categoria, Void>, TableCell<Categoria, Void>> cellFactory =
                new Callback<>() {
                    @Override
                    public TableCell<Categoria, Void> call(TableColumn<Categoria, Void> param) {
                        return new TableCell<>() {

                            private final Button btnExcluir = new Button("Excluir");

                            {
                                btnExcluir.getStyleClass().add("btn-danger");
                                btnExcluir.setOnAction(event -> {
                                    Categoria categoria = getTableView().getItems().get(getIndex());
                                    removerCategoria(categoria);
                                });
                            }

                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(btnExcluir);
                                }
                            }
                        };
                    }
                };

        tablecolAcoes.setCellFactory(cellFactory);
    }

    private void removerCategoria(Categoria categoria) {
        Alert confirm = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Deseja realmente excluir a categoria \"" + categoria.getDescricao() + "\"?",
                ButtonType.YES,
                ButtonType.NO
        );
        confirm.setTitle("Confirmar exclusão");
        confirm.setHeaderText(null);
        System.out.println(categoria.getId());
        categoriaService.deleteCategoria(categoria.getId());
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {

            listaCategorias.remove(categoria);
            tableCategorias.refresh();
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING, mensagem, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}

