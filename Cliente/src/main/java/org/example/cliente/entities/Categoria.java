package org.example.cliente.entities;

public class Categoria {

    private Integer id;
    private String descricao;
    private String tipo;
    private int usuarioID;

   
    public Categoria() {
        System.out.println("LOADING CATEGORIA -> " + this.getClass().getName());
    }

    public Categoria(String descricao, String tipo) {
        this.descricao = descricao;
        this.tipo = tipo;
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
    
    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioId(int usuarioID) {
        this.usuarioID = usuarioID;
    }
}
