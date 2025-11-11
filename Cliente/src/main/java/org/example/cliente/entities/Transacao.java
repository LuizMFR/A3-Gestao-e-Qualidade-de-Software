package org.example.cliente.entities;

import java.time.LocalDate;
import java.util.Date;

public class Transacao {
    
    private int id;//ID do usuario
    private int usuarioId;
    private int categoriaId;
    private String descricao;
    private Date dataTransacao;
    private float valor;
    private String tipo;

    //Construtor vazio (necessário para frameworks e JavaFX)
    public Transacao(){
    }

    //Iniciando o contrutor completo
    public Transacao(int usuarioId, int categoriaId, String descricao, Date dataTransacao, float valor, String tipo){

        this.usuarioId = usuarioId;
        this.categoriaId = categoriaId;
        this.descricao = descricao;
        this.dataTransacao= dataTransacao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Transacao(LocalDate now, String string, String string2, String string3, double d) {
        //TODO Auto-generated constructor stub
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
    
    public Date getDataTransacao() {
        return dataTransacao;
    }
    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
    
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getTipo(){
        return tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
}





