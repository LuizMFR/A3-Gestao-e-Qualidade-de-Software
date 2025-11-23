package org.example.cliente.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.*;

    public class TransacaoController {

        @FXML private ComboBox<String> cbTipo;
        @FXML private TextField txtDescricao;
        @FXML private TextField txtValor;
        @FXML private ComboBox<String> cbCategoria;
        @FXML private DatePicker dpData;
        @FXML private Button btnSalvar;
        @FXML private Button btnCancelar;

        @FXML
        private void initialize() {
            cbTipo.getItems().addAll("Receita", "Despesa");
            cbCategoria.getItems().addAll("Alimentação", "Transporte", "Salário", "Outros");
        }

        @FXML
        private void salvarTransacao() {
            System.out.println("Tipo: " + cbTipo.getValue());
            System.out.println("Descrição: " + txtDescricao.getText());
            System.out.println("Valor: " + txtValor.getText());
            System.out.println("Categoria: " + cbCategoria.getValue());
            System.out.println("Data: " + dpData.getValue());
        }

        @FXML
        private void cancelar() {
            txtDescricao.clear();
            txtValor.clear();
            cbCategoria.setValue(null);
            cbTipo.setValue(null);
            dpData.setValue(null);
        }
    }


