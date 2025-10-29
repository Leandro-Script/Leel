package com.ifsp.Leel.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "imagem")
    private String imagem;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "valor")
    private double valor;

    @Column(name = "quantidade")
    private int quantidade;

    public Produto() {
    }

    public Produto(String nome, String descricao, String imagem, String categoria, double valor, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.categoria = categoria;
        this.valor = valor;
        this.quantidade = quantidade;
    }

}
