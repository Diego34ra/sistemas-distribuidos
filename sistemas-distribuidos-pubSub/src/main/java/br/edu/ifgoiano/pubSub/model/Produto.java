package br.edu.ifgoiano.pubSub.model;

import java.math.BigDecimal;

public class Produto {
    private Integer id;
    private String nome;
    private String sku;
    private BigDecimal preco;

    public Produto() {
    }

    public Produto(Integer id, String nome, String sku, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.sku = sku;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sku='" + sku + '\'' +
                ", preco=" + preco +
                '}';
    }
}
