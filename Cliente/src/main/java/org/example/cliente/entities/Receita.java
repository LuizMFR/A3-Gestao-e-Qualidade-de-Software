package org.example.cliente.entities;

import java.util.Date;

public class Receita {
    
    private int id;
    private int usuarioId;
    private int categoriaId;
    private String descricao;
    private Date dataReceita;
    private float valor;

    //Construtor vazio (necessário para frameworks e JavaFX)
    public Receita(){
    }

    //Iniciando o contrutor completo
    public Receita (int id, int usuarioId, int categoriaId, String descricao, Date dataReceita, float valor){

        this.usuarioId = usuarioId;
        this.categoriaId = categoriaId;
        this.descricao = descricao;
        this.dataReceita = dataReceita;
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
     public int getCategoriaId() {
        return categoriaId;
    }
     ////////////////////////////
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
     ////////////////////////////
    public Date getDataReceita() {
        return dataReceita;
    }
    public void setDataReceita(Date dataReceita) {
        this.dataReceita = dataReceita;
    }
     ////////////////////////////
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    
}





