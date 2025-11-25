package org.example.cliente.entities;

import java.time.LocalDate;


public class TransacaoView {
    
    private int id;//ID do usuario
    private int usuarioId;
    private String categoriaDesc;
    private String descricao;
    private LocalDate dataTransacao;
    private Double valor;
    private String tipo;

    //Construtor vazio (necessário para frameworks e JavaFX)
    public TransacaoView(){
    }

    //Iniciando o contrutor completo
    public TransacaoView(int id, int usuarioId, String categoriaDesc, String descricao, LocalDate dataTransacao, Double valor, String tipo){
        this.id = id;
        this.usuarioId = usuarioId;
        this.descricao = descricao;
        this.categoriaDesc = categoriaDesc;
        this.tipo = tipo;
        this.valor = valor;
        this.dataTransacao= dataTransacao;
    }

    public TransacaoView(LocalDate now, String descricao, String categoriaDesc, String tipo, Double valor) {
        this.dataTransacao = now;
        this.descricao = descricao;
        this.categoriaDesc = categoriaDesc;
        this.tipo = tipo;
        this.valor = valor;
    }

    //Iniciando os Getters e Setters
    public int getId(){
        return id;
    }
     ////////////////////////////
    public int getUsuarioId(){
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId){
        this.usuarioId = usuarioId;
    }

     public String getCategoriaDesc() {
        return categoriaDesc;
    }
    public void setCategoriaDesc(String categoriaDesc) {
        this.categoriaDesc = categoriaDesc;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public LocalDate getDataTransacao() {
        return dataTransacao;
    }
    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
    
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipo(){
        return tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
}





