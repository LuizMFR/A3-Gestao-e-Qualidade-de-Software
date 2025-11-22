package org.example.cliente.entities;

import java.time.LocalDate;


public class Transacao {
    
    private Integer id;//ID do usuario
    private int usuarioId;
    private int categoriaId;
    private String descricao;
    private LocalDate dataTransacao;
    private Double valor;
    private String tipo;

    //Construtor vazio (necessário para frameworks e JavaFX)
    public Transacao(){
    }

    //Iniciando o contrutor completo
    public Transacao(int usuarioId, int categoriaID, String descricao, LocalDate dataTransacao, Double valor, String tipo){

        this.usuarioId = usuarioId;
        this.categoriaId = categoriaID;
        this.descricao = descricao;
        this.dataTransacao= dataTransacao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Transacao(LocalDate now, String descricao, int categoriaId, String tipo, Double valor) {
        this.dataTransacao = now;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }


    public int getUsuarioId(){
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId){
        this.usuarioId = usuarioId;
    }

     public int getCategoriaId() {
        return categoriaId;
    }
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
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





