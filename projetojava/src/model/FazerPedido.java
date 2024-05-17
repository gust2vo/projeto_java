package model;

import java.util.List;

public class FazerPedido {
    private Cliente cliente;
    private List<Produto> produtos;
    private int numeroCupom;
    
    public FazerPedido(Cliente cliente, List<Produto> produtos) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.numeroCupom = numeroCupom;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public int getNumeroCupom() {
        return numeroCupom;
    }

    public void setNumeroCupom(int numeroCupom) {
        this.numeroCupom = numeroCupom;
    }

    @Override
    public String toString() {
        return "FazerPedido [cliente=" + cliente + ", produtos=" + produtos + ", numeroCupom=" + numeroCupom + "]";
    }

    

}
