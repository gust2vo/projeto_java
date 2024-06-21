package controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import model.Cliente;
import model.Funcionario;
import model.Pedido;
import model.Produto;

public class PedidoController implements Serializable{
    private List<Pedido> pedidos;

    
    public PedidoController() {
        this.pedidos = CollectionsFactory.criarListaPedidos();
    }

    public static PedidoController iniciarPedidoController(){
        return new PedidoController();
    }

    public int gerarCupom() {
        int cupom = 0;
        for (Pedido f : pedidos) {
            if (f.getNumeroCupom() > cupom) {
                cupom = f.getNumeroCupom();
            }
        }
        return ++cupom;
    }

    public Pedido verificarPedido(int numeroPedido) {
        return pedidos.stream().filter(f -> f.getNumeroCupom() == numeroPedido).findFirst().orElse(null);
    }

    public String buscarPedido(int numeroPedido) throws Exception {
        Pedido pedido = verificarPedido(numeroPedido);
        if (pedido != null) {
            return "O número do pedido é " + pedido.getNumeroCupom() +
                   ", Cliente: " + pedido.getCliente().getNome() +
                   ", Produto: " + pedido.getProduto().getNomeProduto() +
                   ", Quantidade: " + pedido.getQuantidade();
        }
        return "Não foi encontrado nenhum pedido com este número";
    }

    public String excluirPedido(int numeroPedido) throws Exception {
        if (verificarPedido(numeroPedido) != null) {
            pedidos.remove(verificarPedido(numeroPedido));
            return "Pedido excluido com sucesso";
        }
        return "Este pedido não existe";
    }

    public String fazerPedido(Cliente cliente, Produto produto, int quantidade) throws Exception {
        if (produto.getQuantidadeProdutoExistente() < quantidade) {
            return "Quantidade do produto insuficiente";
        }
        int novoNumeroCupom = gerarCupom();
        Pedido novoPedido = Pedido.iniciarPedido(cliente, produto, quantidade, novoNumeroCupom);
        novoPedido.setNumeroCupom(novoNumeroCupom);
        novoPedido.setQuantidade(quantidade); 
        produto.setQuantidadeProdutoExistente(produto.getQuantidadeProdutoExistente() - quantidade);
        pedidos.add(novoPedido);
        return "Pedido salvo com sucesso: " + novoPedido.getNumeroCupom();
    }

    public String alterarQuantidadePedido(int numeroPedido, int novaQuantidade) throws Exception {
        Pedido pedido = verificarPedido(numeroPedido);
        if (pedido != null) {
            Produto produto = pedido.getProduto();
            int quantidadeAtual = pedido.getQuantidade();
            int quantidadeDisponivel = produto.getQuantidadeProdutoExistente(); 
    
            if (novaQuantidade <= 0) {
                return "A quantidade informada deve ser maior que 0";
            }
            
            int diferencaQuantidade = novaQuantidade - quantidadeAtual;

            if (quantidadeDisponivel >= diferencaQuantidade) {
                produto.setQuantidadeProdutoExistente(quantidadeDisponivel - diferencaQuantidade);
                pedido.setQuantidade(novaQuantidade);
                return "Quantidade do pedido alterada com sucesso";
            } else {
                return "Quantidade solicitada maior do que a disponível em estoque";
            }
        }
        return "Este pedido não existe";
    }

    @Override
    public String toString() {
        return "PedidoController [pedidos=" + pedidos + "]";
    }
    
}
