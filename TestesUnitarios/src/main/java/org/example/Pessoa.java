package org.example;

public class Pessoa {
    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }
    public Pessoa quemSouEu() {
        return this;
    }
}
