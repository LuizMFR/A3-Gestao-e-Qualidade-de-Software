package com.example.Servidor.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Categorias")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String descricao;
    private String tipo;
    private Integer usuarioID;

    public Category() {
    }

    public Category(String descricao, String tipo, Integer usuarioID) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.usuarioID = usuarioID;
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

    public Integer getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Integer usuarioID) {
        this.usuarioID = usuarioID;
    }
}
