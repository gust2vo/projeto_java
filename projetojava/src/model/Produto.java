package model;

public class Produto {
    private String nomeProduto;
    private int quantidadeProdutoExistente, idProduto;
    private float valorProduto;
    
    public Produto(String nomeProduto, int quantidadeProdutoExistente, int idProduto, float valorProduto) {
        this.nomeProduto = nomeProduto;
        this.quantidadeProdutoExistente = quantidadeProdutoExistente;
        this.idProduto = idProduto;
        this.valorProduto = valorProduto;
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

    @Override
    public String toString() {
        return "Produto [nomeProduto=" + nomeProduto + ", quantidadeProdutoExistente=" + quantidadeProdutoExistente
                + ", idProduto=" + idProduto + ", valorProduto=" + valorProduto + "]";
    }

    
    
}
 