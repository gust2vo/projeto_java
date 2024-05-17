package model;

public class Produto {
    private String nomeProduto;
    private int quantidadeProdutoExistente, idProduto;
    
    public Produto(String nomeProduto, int quantidadeProdutoExistente, int idProduto) {
        this.nomeProduto = nomeProduto;
        this.quantidadeProdutoExistente = quantidadeProdutoExistente;
        this.idProduto = idProduto;
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

    @Override
    public String toString() {
        return "Produto [nomeProduto=" + nomeProduto + ", quantidadeProdutoExistente=" + quantidadeProdutoExistente
                + ", idProduto=" + idProduto + "]";
    }

    
    
}
 