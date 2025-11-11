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
    private User user;

    public Category() {
    }

    public Category(Integer id, String descricao, String tipo, User user) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
