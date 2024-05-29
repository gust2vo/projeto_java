package controller;

import java.util.List;
import model.Cliente;
import model.FazerPedido;
import model.Funcionario;
import model.MinhaExcecao;
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

    public FazerPedido verificarPedido(int numeroPedido) {
        return fazerPedidos.stream().filter(f -> f.getNumeroCupom() == numeroPedido).findFirst().orElse(null);
    }

    public String buscarPedido(int numeroPedido) throws MinhaExcecao {
        try {
            if (verificarPedido(numeroPedido) != null) {
                for (FazerPedido f : fazerPedidos) {
                    if (f.getNumeroCupom() == numeroPedido) {
                        return "O numero do pedido é " + f.getNumeroCupom() + " e o cliente foi " + f.getCliente();
                    }
                }
                throw new MinhaExcecao("Pedido não encontrado");
            } else {
                throw new MinhaExcecao("Pedido não existe");
            }
        } catch (Exception e) {
            throw new MinhaExcecao(e.getMessage());
        }
    }

    public String cadastrarFuncionario(String nome, String dataNascimento, String endereco, int idade, String cargo) throws MinhaExcecao {
        try {
            int novaMatricula = gerarMatricula();
            Funcionario novoFuncionario = new Funcionario(nome, dataNascimento, endereco, idade, novaMatricula);
            funcionarios.add(novoFuncionario);
            return "Funcionario cadastrado com sucesso: " + novoFuncionario.getNome();
        } catch (Exception e) {
            throw new MinhaExcecao("Erro ao cadastrar funcionario: " + e.getMessage());
        }
    }

    public Funcionario verificarFuncionario(int numeroMatricula) throws Exception {
        return funcionarios.stream().filter(f -> f.getMatricula() == numeroMatricula).findFirst()
                .orElseThrow(() -> new Exception("Funcionario não encontrado"));
    }

    public String buscarFuncionario(int numeroMatricula) throws MinhaExcecao {
        try {
            if (verificarFuncionario(numeroMatricula) != null) {
                for (Funcionario f : funcionarios) {
                    if (f.getMatricula() == numeroMatricula) {
                        return "O nome do funcionario é " + f.getNome() + " e sua matricula é " + f.getMatricula();
                    }
                }
                throw new MinhaExcecao("Funcionario não encontrado");
            } else {
                throw new MinhaExcecao("Funcionario não existe");
            }
        } catch (Exception e) {
            throw new MinhaExcecao(e.getMessage());
        }
    }

    public Produto verificarId(int numeroId) throws Exception {
        return produtos.stream().filter(p -> p.getIdProduto() == numeroId).findFirst()
                .orElseThrow(() -> new Exception("Produto não encontrado"));
    }

    public String listarProdutos() throws MinhaExcecao {
        try {
            if (produtos.size() == 0) {
                return "Nenhum produto cadastrado.";
            }

            String listaDeProdutos = "Lista de Produtos:\n";
            for (Produto p : produtos) {
                listaDeProdutos += "ID: " + p.getIdProduto() +
                                   ", Nome: " + p.getNomeProduto() +
                                   ", Quantidade em estoque: " + p.getQuantidadeProdutoExistente() +
                                   ", Valor: " + p.getValorProduto() +
                                   "\n";
            }

            return listaDeProdutos;
        } catch (Exception e) {
            throw new MinhaExcecao("Erro ao listar produtos: " + e.getMessage());
        }
    }

    public String buscarProduto(int idProduto) throws MinhaExcecao {
        try {
            if (verificarId(idProduto) != null) {
                for (Produto p : produtos) {
                    if (p.getIdProduto() == idProduto) {
                        return "O id do produto é " + p.getIdProduto() + " o nome do produto é " + p.getNomeProduto()
                                + " e sua quantidade em estoque é " + p.getQuantidadeProdutoExistente();
                    }
                }
                throw new MinhaExcecao("Produto não encontrado");
            } else {
                throw new MinhaExcecao("Produto não existe");
            }
        } catch (Exception e) {
            throw new MinhaExcecao(e.getMessage());
        }
    }

    public String excluirPedido(int numeroPedido) throws MinhaExcecao {
        try {
            if (verificarPedido(numeroPedido) != null) {
                fazerPedidos.remove(verificarPedido(numeroPedido));
                return "Pedido excluido com sucesso";
            }
            throw new MinhaExcecao("Pedido não encontrado");
        } catch (Exception e) {
            throw new MinhaExcecao(e.getMessage());
        }
    }

    public Produto verificarNomeProduto(String nomeProduto) throws Exception {
        return produtos.stream().filter(p -> p.getNomeProduto().equals(nomeProduto)).findFirst()
                .orElseThrow(() -> new Exception("Produto não encontrado"));
    }

    public void alterarProduto(int numeroFuncionario, String nomeProduto, int quantidadeProdutoExistente,
            int idProduto, float valorProduto) throws MinhaExcecao {
        try {
            if (verificarId(idProduto) != null && verificarFuncionario(numeroFuncionario) != null) {
                for (Produto p : produtos) {
                    p.setNomeProduto(nomeProduto);
                    p.setQuantidadeProdutoExistente(quantidadeProdutoExistente);
                    p.setIdProduto(idProduto);
                    p.setValorProduto(valorProduto);
                }
            } else {
                throw new MinhaExcecao("Produto não encontrado");
            }
        } catch (Exception e) {
            throw new MinhaExcecao(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "CafeteriaController [funcionarios=" + funcionarios + ", produtos=" + produtos + ", fazerPedidos="
                + fazerPedidos + "]";
    }
}
