package com.example.Servidor.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Transacao")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String descricao;
    private String tipo;
    private float valor;
    private Integer usuarioId;
    private Integer categoriaId;

    public Transaction() {
    }

    public Transaction(String descricao, String tipo, float valor, Integer usuarioId, Integer categoriaId) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.usuarioId = usuarioId;
        this.categoriaId = categoriaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }
}
