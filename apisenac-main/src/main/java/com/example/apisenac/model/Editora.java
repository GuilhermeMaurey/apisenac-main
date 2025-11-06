package com.example.apisenac.model;

import jakarta.persistence.*;

@Entity
@Table(name="editoras")
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long codigo;
    private String nome;
    private String endereco;
    private char telefone;

    public Editora(Long codigo, String nome, String endereco, char telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public char getTelefone() {
        return telefone;
    }

    public void setTelefone(char telefone) {
        this.telefone = telefone;
    }

    public Editora() {
    }
}
