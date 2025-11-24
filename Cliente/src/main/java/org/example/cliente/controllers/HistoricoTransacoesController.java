package org.example.cliente.controllers;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class HistoricoTransacoesController {

    @FXML private GridPane gridTransacoes;
    @FXML private Button btnSalvar;
    @FXML private Button btnCancelar;

    private ObservableList<Transacao> listaTransacoes;

    @FXML
    private void initialize() {
        // Inicializando a lista de transações
        listaTransacoes = FXCollections.observableArrayList(
                new Transacao("22/11/2025", "Receita", "R$ 100,00", "Pagamento Sala"),
                new Transacao("21/11/2025", "Despesa", "R$ 150,00", "Pagamento Material")
        );

        // Populando o GridPane com as transações
        for (int i = 0; i < listaTransacoes.size(); i++) {
            Transacao transacao = listaTransacoes.get(i);

            // Adiciona a data na primeira coluna
            TextField dataField = new TextField(transacao.getData());
            gridTransacoes.add(dataField, 0, i);

            // Adiciona o tipo na segunda coluna (ComboBox)
            ComboBox<String> tipoComboBox = new ComboBox<>(FXCollections.observableArrayList("Receita", "Despesa"));
            tipoComboBox.setValue(transacao.getTipo());
            gridTransacoes.add(tipoComboBox, 1, i);

            // Adiciona o valor na terceira coluna
            TextField valorField = new TextField(transacao.getValor());
            gridTransacoes.add(valorField, 2, i);

            // Adiciona a descrição na quarta coluna
            TextField descricaoField = new TextField(transacao.getDescricao());
            gridTransacoes.add(descricaoField, 3, i);

            // Adiciona os botões de ação na última coluna
            HBox acaoBox = new HBox(10);
            Button btnEditar = new Button("Editar");
            Button btnExcluir = new Button("Excluir");
            int finalI = i;
            btnEditar.setOnAction(e -> editarTransacao(finalI));
            int finalI1 = i;
            btnExcluir.setOnAction(e -> excluirTransacao(finalI1));

            acaoBox.getChildren().addAll(btnEditar, btnExcluir);
            gridTransacoes.add(acaoBox, 4, i);
        }
    }

    // Método de edição de transação (apenas exemplo)
    private void editarTransacao(int index) {
        Transacao transacao = listaTransacoes.get(index);
        System.out.println("Editando transação: " + transacao);
        // Lógica de edição aqui, talvez com um novo formulário ou modal
    }

    // Método de exclusão de transação
    private void excluirTransacao(int index) {
        Transacao transacao = listaTransacoes.get(index);
        listaTransacoes.remove(transacao);
        System.out.println("Excluindo transação: " + transacao);
        // Atualizar a visualização no GridPane após exclusão
    }

    // Botão de salvar
    @FXML
    private void salvar() {
        System.out.println("Salvando as alterações...");
    }

    // Botão de cancelar
    @FXML
    private void cancelar() {
        System.out.println("Cancelando...");
    }

    // Classe interna para representar uma transação
    public static class Transacao {
        private String data;
        private String tipo;
        private String valor;
        private String descricao;

        public Transacao(String data, String tipo, String valor, String descricao) {
            this.data = data;
            this.tipo = tipo;
            this.valor = valor;
            this.descricao = descricao;
        }

        public String getData() { return data; }
        public String getTipo() { return tipo; }
        public String getValor() { return valor; }
        public String getDescricao() { return descricao; }

        @Override
        public String toString() {
            return data + " | " + tipo + " | " + valor + " | " + descricao;
        }
    }
}
