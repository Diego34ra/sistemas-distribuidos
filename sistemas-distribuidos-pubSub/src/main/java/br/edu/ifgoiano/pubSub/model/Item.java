package br.edu.ifgoiano.pubSub.model;

import java.math.BigDecimal;

public class Item {
    private Integer id;
    private BigDecimal valor;
    private Integer quantidade;
    private Produto produto;

    public Item() {
    }

    public Item(Integer id, BigDecimal valor, Integer quantidade, Produto produto) {
        this.id = id;
        this.valor = valor;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", produto=" + produto +
                '}';
    }
}
