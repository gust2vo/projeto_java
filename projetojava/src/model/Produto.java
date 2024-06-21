package model;

import java.io.Serializable;

public class Produto implements Serializable{
    private String nomeProduto;
    private int quantidadeProdutoExistente, idProduto;
    private float valorProduto;
    
    public Produto(String nomeProduto, int quantidadeProdutoExistente, int idProduto, float valorProduto) {
        this.nomeProduto = nomeProduto;
        this.quantidadeProdutoExistente = quantidadeProdutoExistente;
        this.idProduto = idProduto;
        this.valorProduto = valorProduto;
    }

    public static Produto criarProduto(String nomeProduto, int quantidadeProdutoExistente, int idProduto, float valorProduto){
        return new Produto(nomeProduto, quantidadeProdutoExistente, idProduto, valorProduto);
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidadeProdutoExistente() {
        return quantidadeProdutoExistente;
    }

    public void setQuantidadeProdutoExistente(int quantidadeProdutoExistente) {
        this.quantidadeProdutoExistente = quantidadeProdutoExistente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    
    public float getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(float valorProduto) {
        this.valorProduto = valorProduto;
    }

    public String mostrarProduto(){
        return "o nome do produto é " + nomeProduto + " e a quantidade é " + quantidadeProdutoExistente;
    }

    @Override
    public String toString() {
        return "" + mostrarProduto();
    }

    
    
}
 