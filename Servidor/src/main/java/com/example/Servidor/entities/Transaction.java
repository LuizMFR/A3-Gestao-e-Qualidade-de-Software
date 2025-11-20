package com.example.servidor.entities;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "transacoes")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String tipo;
    private float valor;

    @Column(name = "DataTransacao")
    private Date dataTransacao;

    @Column(name = "UsuarioID")
    private Integer usuarioId;

    @Column(name = "CategoriaID")
    private Integer categoriaId;

    public Transaction() {
    }

    public Transaction(String descricao, String tipo, float valor, Date dataTransacao, Integer usuarioId, Integer categoriaId) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
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

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date data) {
        this.dataTransacao = data;
    }
}
