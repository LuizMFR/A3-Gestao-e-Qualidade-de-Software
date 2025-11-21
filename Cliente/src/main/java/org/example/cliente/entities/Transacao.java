package org.example.cliente.entities;

import java.time.LocalDate;


public class Transacao {
    
    private int id;//ID do usuario
    private int usuarioId;
    private String categoria;
    private String descricao;
    private LocalDate dataTransacao;
    private Double valor;
    private String tipo;

    //Construtor vazio (necessário para frameworks e JavaFX)
    public Transacao(){
    }

    //Iniciando o contrutor completo
    public Transacao(int usuarioId, String categoria, String descricao, LocalDate dataTransacao, Double valor, String tipo){

        this.usuarioId = usuarioId;
        this.categoria = categoria;
        this.descricao = descricao;
        this.dataTransacao= dataTransacao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Transacao(LocalDate now, String descricao, String categoria, String tipo, Double valor) {
        this.dataTransacao = now;
        this.descricao = descricao;
        this.categoria = categoria;
        this.tipo = tipo;
        this.valor = valor;
    }

    //Iniciando os Getters e Setters
    public int getId(){
        return id;
    }
     ////////////////////////////
    public int getUsuarioI(){
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId){
        this.usuarioId = usuarioId;
    }

     public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
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





