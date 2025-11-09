package org.example.cliente.entities;

import java.util.Date;

public class Custo {

    private int id;
    private int usuarioId;
    private int categoriaId;
    private String descricao;
    private Date dataCusto;
    private float valor;

    //Construtor vazio (necessário para frameworks e JavaFX)
    public Custo() {
    }

    //Iniciando o contrutor completo
    public Custo(int usuarioId, int categoriaId, String descricao, Date dataCusto, float valor) {
        this.usuarioId = usuarioId;
        this.categoriaId = categoriaId;
        this.descricao = descricao;
        this.dataCusto = dataCusto;
        this.valor = valor;
    }

    // 🔹 Getters e Setters
    public int getId() {
        return id;
    }
     ////////////////////////////
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
     ////////////////////////////
    public int getCategoriaId() {
        return categoriaId;
    }
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
     ////////////////////////////
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
     ////////////////////////////
    public Date getDataCusto() {
        return dataCusto;
    }
    public void setDataCusto(Date dataCusto) {
        this.dataCusto = dataCusto;
    }
     ////////////////////////////
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
}
