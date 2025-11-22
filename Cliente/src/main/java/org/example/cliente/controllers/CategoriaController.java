package org.example.cliente.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import org.example.cliente.entities.Categoria;
import org.example.cliente.entities.User;
import org.example.cliente.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoriaController {

    @FXML
    private TextField textfieldTite;          // campo Título

    @FXML
    private TextArea textareadescrição;       // aqui podemos usar como "tipo" ou "descrição"

    @FXML
    private TableView<Categoria> tableCategorias; //teste

    @FXML
    private TableColumn<Categoria, Integer> tablecolId;

    @FXML
    private TableColumn<Categoria, String> tablecolTitulo;

    @FXML
    private TableColumn<Categoria, String> tablecolDescricao;

    @FXML
    private TableColumn<Categoria, Void> tablecolAcoes;

    private final ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList();

    private User userLoggedIn;

    @Autowired
    private HomeService homeService = new HomeService();

    @FXML
    public void initialize() {
        
    }

    public void setUserLoggedIn(User user) {
        this.userLoggedIn = user;
        carregarTransacoes();
        configurarTabela();
    }


    public void configurarTabela() {
        tablecolId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablecolTitulo.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tablecolDescricao.setCellValueFactory(new PropertyValueFactory<>("tipo") ); // usando tipo como descrição

        tableCategorias.setItems(listaCategorias);
    }

    public void carregarTransacoes() {
        List<Categoria> categorias = homeService.getAllCategorias(userLoggedIn.getId());

        listaCategorias.clear();

        for (Categoria c : categorias) {
            System.out.println("DEBUG Categoria -> ID: " + c.getId() + ", Descrição: '" + c.getDescricao() + "'");
            listaCategorias.add(c);
        }

    }

    @FXML
    private void cadastrarCategoria() {
        String titulo = textfieldTite.getText().trim();
        String descricaoOuTipo = textareadescrição.getText().trim();

        if (titulo.isEmpty()) {
            mostrarAlerta("Validação", "Informe o título da categoria.");
            return;
        }

        Categoria categoria = new Categoria(titulo, descricaoOuTipo);
        listaCategorias.add(categoria);
        tableCategorias.refresh();

        textfieldTite.clear();
        textareadescrição.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING, mensagem, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
