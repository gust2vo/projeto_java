package controller;

import java.io.Serializable;
import java.util.Optional;

import model.Cliente;
import model.Pedido;
import model.Produto;
import model.Serializacao;
import util.Salvar;
import util.util;

public class CafeteriaController implements Serializacao {
    private FuncionarioController funcionarioController;
    private ProdutoController produtoController;
    private PedidoController pedidoController;

    private CafeteriaController() throws Exception {
        this.funcionarioController = FuncionarioController.iniciaFuncionarioController();
        this.produtoController = ProdutoController.iniciarProdutoController();
        this.pedidoController = PedidoController.iniciarPedidoController();
        try {
            lerDados();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static CafeteriaController iniciarCafeteria() throws Exception {
        return new CafeteriaController();
    }

    public boolean verificarFuncionario(int numeroMatricula) throws Exception {
        try {
            funcionarioController.verificarFuncionario(numeroMatricula);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String cadastrarFuncionario(String nome, String dataNascimento, String endereco, String cargo)
            throws Exception {
        if (!util.verificarIdade(dataNascimento)) {
            return "Para cadastrar um funcionario ele dever tem idade igual ou maior do que 18 anos";
        }
        int idadeCliente = util.calcularIdade(dataNascimento);
        funcionarioController.cadastrarFuncionario(nome, dataNascimento, endereco, idadeCliente, cargo);
        salvarDados();
        return "Funcionario cadastrado com sucesso";
    }

    public String listarFuncionarios() throws Exception {
        return funcionarioController.listarFuncionarios();
    }

    public String alterarEnderecoFuncionario(int idFuncionario, String endereco) throws Exception {
        salvarDados();
        return funcionarioController.alterarEnderecoFuncionario(idFuncionario, endereco);
    }

    public String excluirFuncionario(int idFuncionario) throws Exception {
        salvarDados();
        return funcionarioController.excluirFuncionario(idFuncionario);
    }

    public String buscarFuncionario(int numeroFuncionario) throws Exception {
        return funcionarioController.buscarFuncionario(numeroFuncionario);
    }

    public void verificarNomeProduto(String nomeProduto) throws Exception {
        produtoController.verificarNomeProduto(nomeProduto);
    }

    public void verificarIdProduto(int idProduto) throws Exception {
        produtoController.verificarIdProduto(idProduto);
    }

    public String cadastrarProduto(int numeroFuncionario, String nomeProduto, int quantidadeProdutoExistente,
            float valorProduto) throws Exception {
        if (verificarFuncionario(numeroFuncionario) && quantidadeProdutoExistente >= 0 && valorProduto >= 0.0) {
            produtoController.cadastrarProduto(nomeProduto, quantidadeProdutoExistente, valorProduto);
            salvarDados();
            return "Produto cadastrado com sucesso";

        }
        return "Somente funcionarios podem cadastrar o produto";
    }

    public String listarProdutos() throws Exception {
        return produtoController.listarProdutos();
    }

    public String listarProdutosParaClientes() throws Exception {
        return produtoController.listarProdutosParaClientes();
    }

    public String alterarProduto(int numeroFuncionario, int idProduto, int quantidadeProdutoExistente)
            throws Exception {
        if (verificarFuncionario(numeroFuncionario)) {
            produtoController.alterarQuantidadeProduto(quantidadeProdutoExistente, idProduto);
            salvarDados();
            return "Produto alterado com sucesso";
        } else {
            return "Somente funcionarios podem alterar o produto";
        }

    }

    public String excluirProduto(int numeroFuncionario, int idProduto) throws Exception {
        if (verificarFuncionario(numeroFuncionario)) {
            salvarDados();
            return produtoController.excluirProduto(idProduto);
        } else {
            return "Somente funcionarios podem excluir o produto";
        }
    }

    public String buscarProdutoPorId(int idProduto) throws Exception {
        return produtoController.buscarProduto(idProduto);
    }

    public String buscarProdutoPorNome(String nomeProduto) throws Exception {
        return produtoController.buscarProduto(nomeProduto);
    }

    public String buscarProdutoPorQuantidadeOptional(int quantidadeDesejada) throws Exception {
        return produtoController.buscarProdutoOptionalPorQuantidade(quantidadeDesejada)
                .map(produto -> "O nome do produto é " + produto.getNomeProduto() +
                                ", o id do produto é " + produto.getIdProduto() +
                                " e sua quantidade em estoque é " + produto.getQuantidadeProdutoExistente())
                .orElseThrow(() -> new Exception("Produto com quantidade desejada não encontrado"));
    }

    public Pedido verificarPedido(int numeroPedido) throws Exception {
        return pedidoController.verificarPedido(numeroPedido);
    }

    public String buscarPedido(int numeroPedido) throws Exception {
        return pedidoController.buscarPedido(numeroPedido);
    }

    public String excluirPedido(int numeroPedido) throws Exception {
        if (verificarPedido(numeroPedido) != null) {
            pedidoController.excluirPedido(numeroPedido);
            salvarDados();
            return "Pedido excluido com sucesso";
        }
        return "Este pedido não existe";
    }

    public String fazerPedido(String nomeCliente, String dataNascimento, String endereco, String nomeProduto,
            int quantidade) throws Exception {
        if (!util.verificarIdade(dataNascimento)) {
            return "É necessario ter mais do que 14 anos para realizar um pedido";
        }
        if (quantidade <= 0) {
            return "A quantidade de produtos informada deve ser maior que 0";
        }

        int idadeCliente = util.calcularIdade(dataNascimento);

        Cliente cliente = Cliente.criarCliente(nomeCliente, dataNascimento, endereco, idadeCliente);
        Produto produto = produtoController.verificarNomeProduto(nomeProduto);

        if (produto.getQuantidadeProdutoExistente() < quantidade) {
            return "Quantidade insuficiente do produto";
        }

        pedidoController.fazerPedido(cliente, produto, quantidade);
        salvarDados();
        return "Pedido realizado com sucesso";
    }

    public String alterarQuantidadePedido(int numeroPedido, int novaQuantidade) throws Exception {
        if (novaQuantidade <= 0) {
            return "A quantidade informada deve ser maior que 0";
        }

        Pedido pedido = pedidoController.verificarPedido(numeroPedido);
        if (pedido != null) {
            Produto produto = pedido.getProduto();
            int quantidadeAtual = pedido.getQuantidade();
            int quantidadeDisponivel = produto.getQuantidadeProdutoExistente() + quantidadeAtual;

            if (novaQuantidade > quantidadeDisponivel) {
                return "Quantidade solicitada maior do que a disponível em estoque";
            }

            int diferencaQuantidade = novaQuantidade - quantidadeAtual;

            pedido.setQuantidade(novaQuantidade);
            produto.setQuantidadeProdutoExistente(produto.getQuantidadeProdutoExistente() - diferencaQuantidade);
            salvarDados();

            return "Quantidade do pedido alterada com sucesso";
        }

        return "Este pedido não existe";
    }

    @Override
    public void salvarDados() throws Exception {
        Salvar.salvarDados(funcionarioController);
        Salvar.salvarDados(pedidoController);
        Salvar.salvarDados(produtoController);
    }

    @Override
    public void lerDados() throws Exception {
        funcionarioController = Salvar.lerDadosFuncionarios();
        pedidoController = Salvar.lerDadosPedido();
        produtoController = Salvar.lerDadosProdutos();
    }

    @Override
    public String toString() {
        return "CafeteriaController [funcionarioController=" + funcionarioController + ", produtoController="
                + produtoController + ", pedidoController=" + pedidoController + "]";
    }

}
