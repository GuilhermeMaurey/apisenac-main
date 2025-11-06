package com.example.apisenac.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

@Entity
@Table(name="paises")
public class Pais {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long codigo;

    private String nome;

    private VarcharJdbcType descricao;


    public Pais(String nome, VarcharJdbcType descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public VarcharJdbcType getDescricao() {
        return descricao;
    }

    public void setDescricao(VarcharJdbcType descricao) {
        this.descricao = descricao;
    }

    public Pais() {

    }
}
