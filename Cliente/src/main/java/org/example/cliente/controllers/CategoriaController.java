package org.example.cliente.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.cliente.entities.Categoria;

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

    @FXML
    public void initialize() {
        tablecolId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablecolTitulo.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tablecolDescricao.setCellValueFactory(new PropertyValueFactory<>("tipo") ); // usando tipo como descrição

        tableCategorias.setItems(listaCategorias);
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
