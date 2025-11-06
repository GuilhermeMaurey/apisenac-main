package com.example.apisenac.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

@Getter
@Entity
@Table(name="linguas")
public class Lingua {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long codigo;

    private String nome;

    private VarcharJdbcType descricao;


    public Lingua(Long codigo, String nome, VarcharJdbcType descricao){

        this.nome = nome;
        this.descricao = descricao;
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

    public VarcharJdbcType getDescricao() {
        return descricao;
    }

    public void setDescricao(VarcharJdbcType descricao) {
        this.descricao = descricao;
    }

    public Lingua() {

    }
}
