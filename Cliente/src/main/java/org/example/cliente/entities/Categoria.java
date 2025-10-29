package org.example.cliente.entities;

public class Categoria {

    private int id;
    private String nome;
    private String tipo;

    //Construtor vazio (necessário para frameworks e JavaFX)
    public Categoria() {
    }

    //Iniciando o contrutor completo
    public Categoria(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    //Getters e Setters
    public int getId() {
        return id;
    }
     ////////////////////////////
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
     ////////////////////////////
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
