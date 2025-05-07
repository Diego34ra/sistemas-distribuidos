package br.edu.ifgoiano.pubSub.model;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {
    private Integer id;
    private BigDecimal valorTotal;
    private PedidoStatus status;
    private Cliente cliente;
    private List<Item> items;

    public Pedido() {
        this.status = PedidoStatus.EM_ABERTO;
    }

    public Pedido(Integer id, BigDecimal valorTotal, Cliente cliente, List<Item> items) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.status = PedidoStatus.EM_ABERTO;
        this.cliente = cliente;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        if (items == null || items.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return items.stream()
                .map(Item::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", valorTotal=" + valorTotal +
                ", status=" + status +
                ", cliente=" + cliente +
                ", items=" + items +
                '}';
    }
}
