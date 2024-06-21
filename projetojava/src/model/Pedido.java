package model;

import java.io.Serializable;


public class Pedido implements Serializable{
    private Cliente cliente;
    private Produto produto;
    private int numeroCupom, quantidade;
    
    public Pedido(Cliente cliente, Produto produto, int quantidade, int numeroCupom) {
        this.cliente = cliente;
        this.produto = produto;
        this.numeroCupom = numeroCupom;
    }

    public static Pedido iniciarPedido(Cliente cliente, Produto produto, int quantidade, int numeroCupom){
        return new Pedido(cliente, produto, quantidade, numeroCupom);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getNumeroCupom() {
        return numeroCupom;
    }

    public void setNumeroCupom(int numeroCupom) {
        this.numeroCupom = numeroCupom;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    //public String reduzirEstoque()
    @Override
    public String toString() {
        return "FazerPedido [cliente=" + cliente + ", produtos=" + produto + ", numeroCupom=" + numeroCupom + "]";
    }

    

    

}
