package com.duke.estoque;

public class Usuario {
    private String nome;
    private String email;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Email: " + email;
    }
}