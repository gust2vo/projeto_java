package controller;

import java.util.List;

import model.Cliente;
import model.FazerPedido;
import model.Funcionario;
import model.Produto;

public class CafeteriaController {
    private List<Funcionario> funcionarios;
    private List<Produto> produtos;
    private List<FazerPedido> fazerPedidos;

    public CafeteriaController(List<Funcionario> funcionarios, List<Produto> produtos, List<FazerPedido> fazerPedidos) {
        this.funcionarios = funcionarios;
        this.produtos = produtos;
        this.fazerPedidos = fazerPedidos;
    }

    public int gerarMatricula() {
        int matricula = 100;
        for (Funcionario f : funcionarios) {
            if (f.getMatricula() > matricula) {
                matricula = f.getMatricula();
            }
        }
        return ++matricula;
    }

    public int gerarCupom() {
        int cupom = 0;
        for (FazerPedido f : fazerPedidos) {
            if (f.getNumeroCupom() > cupom) {
                cupom = f.getNumeroCupom();
            }
        }
        return ++cupom;
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

    public FazerPedido verificarPedido(int numeroPedido){
        return fazerPedidos.stream().filter(f -> f.getNumeroCupom() == (numeroPedido)).findFirst().orElse(null);
    }

    public String buscarPedido(int numeroPedido){
        if (verificarPedido(numeroPedido) != null) {
            for (FazerPedido f : fazerPedidos) {
                return "O numero do pedido é " + f.getNumeroCupom();
            }
        }
        return "Pedido não existe";
    }

    public Funcionario verificarFuncionario(int numeroMatricula){
        return funcionarios.stream().filter(f -> f.getMatricula() == (numeroMatricula)).findFirst().orElse(null);
    }

    public String buscarFuncionario(int numeroMatricula){
        if (verificarFuncionario(numeroMatricula) != null) {
            for (Funcionario f : funcionarios) {
                return "O nome do funcionario é " + f.getNome() + " e sua matricula é " + f.getMatricula();
            }
        }
        return "O funcionario com esta matricula não existe";
    }

    public Produto verificarId(int numeroId){
        return produtos.stream().filter(p -> p.getIdProduto() == (numeroId)).findFirst().orElse(null);
    }

    public String buscarProduto(int idProduto){
        if (verificarId(idProduto) != null) {
            for (Produto p : produtos) {
                return "O id do produto é " + p.getIdProduto() + " o nome do produto é " + p.getNomeProduto() + " e sua quantidade em estoque é " + p.getQuantidadeProdutoExistente();
            }
        }
        return "O produto não foi encontrado";
    }

    public String excluirPedido(int numeroPedido){
        if (verificarPedido(numeroPedido) != null) {
            fazerPedidos.remove(verificarPedido(numeroPedido));
            return "Pedido excluido com sucesso";
        }
        return "Não existe esse pedido";
    }

    public Produto verificarNomeProduto(String nomeProduto){
        return produtos.stream().filter(p -> p.getNomeProduto().equals(nomeProduto)).findFirst().orElse(null);
    }

    public void alterarProduto(int numeroFuncionario, String nomeProduto, int quantidadeProdutoExistente, int idProduto){
        if (verificarId(idProduto) != null & verificarFuncionario(numeroFuncionario) != null) {
            for (Produto p : produtos) {
                p.setNomeProduto(nomeProduto);
                p.setQuantidadeProdutoExistente(quantidadeProdutoExistente);
                p.setIdProduto(idProduto);
            }
        }
    }

    @Override
    public String toString() {
        return "CafeteriaController [funcionarios=" + funcionarios + ", produtos=" + produtos + ", fazerPedidos="
                + fazerPedidos + "]";
    }

}
