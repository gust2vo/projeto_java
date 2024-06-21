package controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Funcionario;
import model.Produto;

public class ProdutoController implements Serializable {
    List<Produto> produtos;

    public ProdutoController() {
        this.produtos = CollectionsFactory.criarListaProduto();
    }

    public static ProdutoController iniciarProdutoController() {
        return new ProdutoController();
    }

    public int gerarIdProduto() {
        int idProduto = 0;
        for (Produto p : produtos) {
            if (p.getIdProduto() > idProduto) {
                idProduto = p.getIdProduto();
            }
        }
        return ++idProduto;
    }

    public Produto verificarNomeProduto(String nomeProduto) throws Exception {
        return produtos.stream().filter(p -> p.getNomeProduto().equalsIgnoreCase(nomeProduto)).findFirst()
                .orElseThrow(() -> new Exception("Produto não encontrado"));
    }

    public Produto verificarIdProduto(int numeroId) throws Exception {
        return produtos.stream().filter(p -> p.getIdProduto() == numeroId).findFirst()
                .orElseThrow(() -> new Exception("Produto não encontrado"));
    }

    public String cadastrarProduto(String nomeProduto, int quantidadeProdutoExistente, float valorProduto)
            throws Exception {
        int novoIdProduto = gerarIdProduto();
        Produto novoProduto = new Produto(nomeProduto, quantidadeProdutoExistente, novoIdProduto, valorProduto);
        produtos.add(novoProduto);
        return "Produto cadastrado com sucesso: " + novoProduto.getNomeProduto();
    }

    public String listarProdutos() throws Exception {
        String listaDeProdutos = "Lista de Produtos:\n";
        for (Produto p : produtos) {
            listaDeProdutos += "Numero do id do produto: " + p.getIdProduto() +
                    ", Nome do produto: " + p.getNomeProduto() +
                    ", Quantidade em estoque: " + p.getQuantidadeProdutoExistente() +
                    ", Valor: " + p.getValorProduto() +
                    "\n";
        }
        return listaDeProdutos;
    }

    public String listarProdutosParaClientes() throws Exception {
        String listaDeProdutos = "Lista de Produtos:\n";
        for (Produto p : produtos) {
            listaDeProdutos += "Nome do produto: " + p.getNomeProduto() +
                    ", Quantidade disponivel: " + p.getQuantidadeProdutoExistente() +
                    ", Valor: " + p.getValorProduto() +
                    "\n";
        }
        return listaDeProdutos;
    }

    public String alterarQuantidadeProduto(int quantidadeProdutoExistente,
            int idProduto) throws Exception {
        if (verificarIdProduto(idProduto) != null) {
            for (Produto p : produtos) {
                if (p.getIdProduto() == idProduto) {
                    p.setQuantidadeProdutoExistente(quantidadeProdutoExistente);
                    return "Produto com id " + p.getIdProduto() + " foi alterado com sucesso";
                }

            }
        }
        throw new Exception();
    }

    public String excluirProduto(int idProduto) throws Exception {
        Produto produto = verificarIdProduto(idProduto);
        if (produto != null) {
            produtos.remove(produto);
            return "Produto excluído com sucesso";
        }
        throw new Exception();
    }

    public String buscarProduto(int idProduto) throws Exception {
        if (verificarIdProduto(idProduto) != null) {
            for (Produto p : produtos) {
                if (p.getIdProduto() == idProduto) {
                    return "O id do produto é " + p.getIdProduto() + " o nome do produto é "
                            + p.getNomeProduto()
                            + " e sua quantidade em estoque é " + p.getQuantidadeProdutoExistente();
                }
            }
        }
        throw new Exception();
    }

    public String buscarProduto(String nomeProduto) throws Exception {
        Produto produto = verificarNomeProduto(nomeProduto);
        if (produto != null) {
            return "O nome do produto é " + produto.getNomeProduto() + " o id do produto é "
                    + produto.getIdProduto()
                    + " e sua quantidade em estoque é " + produto.getQuantidadeProdutoExistente();
        }
        throw new Exception();
    }

    public Optional<Produto> buscarProdutoOptionalPorQuantidade(int quantidadeDesejada) {
        Optional<Produto> produtoOptional = produtos.stream()
                .filter(p -> p.getQuantidadeProdutoExistente() >= quantidadeDesejada)
                .findFirst();
        return produtoOptional;
    }

    @Override
    public String toString() {
        return "ProdutoController [produtos=" + produtos + "]";
    }

  

}
