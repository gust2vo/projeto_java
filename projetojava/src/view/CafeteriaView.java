package view;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.CafeteriaController;
import model.Funcionario;

public class CafeteriaView {
    public static void main(String[] args, Scanner scan) throws Exception {
        int opcao = 0, numeroMatricula, quantidadeProduto, idProduto, numeroPedido;
        float valorProduto;
        String nomeFuncionario, dataNascimentoFuncionario, endereco, nomeProduto, nomeCliente, dataNascimento;
        try {
            PrintStream ps = new PrintStream(new FileOutputStream("erro.log"));
            System.setErr(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CafeteriaController cafeteria = CafeteriaController.iniciarCafeteria();
        do {
            System.out.println("Escolha as opções:\n" +
                    "Digite 1 para cadastrar funcionario\n" +
                    "Digite 2 para buscar por numero da matricula o funcionario\n" +
                    "Digite 3 para excluir um funcionario\n" +
                    "Digite 4 para alterar um funcionario\n" +
                    "Digite 5 para mostrar todos os funcionarios cadastrados\n" +
                    "Digite 6 para cadastrar um produto\n" +
                    "Digite 7 para buscar por id um produto\n" +
                    "Digite 8 para buscar por nome um produto\n" +
                    "Digite 9 para buscar um produto por quantidade\n" +
                    "Digite 10 para excluir um produto\n" +
                    "Digite 11 para alterar um produto\n" +
                    "Digite 12 para mostrar todos os produtos\n" +
                    "Digite 13 para fazer seu pedido\n" +
                    "Digite 14 para mostrar seu pedido\n" +
                    "Digite 15 para excluir seu pedido\n" +
                    "Digite 16 para alterar seu pedido\n" +
                    "Digite 0 para sair do programa");
            System.out.println("Qual opção voce deseja?");
            try {
                opcao = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Opção inválida. Por favor, digite um número inteiro.");
                scan.nextLine();
            }
            switch (opcao) {
                case 1:
                    try {
                        System.out.println("Cadastro de funcionario:");
                        System.out.println("Qual é o nome do funcionario? ");
                        nomeFuncionario = scan.nextLine();
                        System.out.println("Qual é a data de nascimento do funcionario digite no modelo dd/MM/yyyy? ");
                        dataNascimentoFuncionario = scan.nextLine();
                        System.out.println("Qual é o endereço do funcionario? ");
                        endereco = scan.nextLine();
                        cafeteria.cadastrarFuncionario(nomeFuncionario, dataNascimentoFuncionario, endereco, endereco);
                        System.out.println("Funcionario cadastrado com sucesso");
                    } catch (DateTimeParseException e) {
                        System.err.println("É necessario passar a data em formato dd/MM/yyyy");
                        scan.nextLine();
                    }
                    break;
                case 2:
                    System.out.println("Buscar funcionario por numero da matricula:");
                    System.out.println("Qual o numero da matricula do funcionario?");
                    try {
                        numeroMatricula = scan.nextInt();
                        System.out.println(cafeteria.buscarFuncionario(numeroMatricula));
                    } catch (InputMismatchException e) {
                        System.err.println("É necessario passar um numero inteiro");
                        scan.nextLine();
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Excluir funcionario:");
                        System.out.println("Digite o numero da matricula para excluir funcionario");
                        numeroMatricula = scan.nextInt();
                        System.out.println(cafeteria.excluirFuncionario(numeroMatricula));
                    } catch (InputMismatchException e) {
                        System.err.println("É necessario passar um número inteiro");
                        scan.nextLine();
                    } catch (Exception e) {
                        System.err.println("Erro ao excluir funcionário: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Alterar funcionario");
                        System.out.println("Digite o numero da matricula para poder alterar o endereço do funcionario");
                        numeroMatricula = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Qual vai ser o novo endereço do funcionario?");
                        endereco = scan.nextLine();
                        System.out.println(cafeteria.alterarEnderecoFuncionario(numeroMatricula, endereco));
                    } catch (InputMismatchException e) {
                        System.err.println("É necessario passar um número inteiro");
                        scan.nextLine();
                    } catch (Exception e) {
                        System.err.println("Erro ao alterar funcionário: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println(cafeteria.listarFuncionarios());
                    break;
                case 6:
                    try {
                        System.out.println("Cadastrar Produto");
                        System.out.println("Qual é o numero da matricula do funcionario? ");
                        numeroMatricula = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Qual é o nome do produto? ");
                        nomeProduto = scan.nextLine();
                        System.out.println("Qual é a quantidade do produto? ");
                        quantidadeProduto = scan.nextInt();
                        System.out.println("Qual o valor do produto?");
                        valorProduto = scan.nextFloat();
                        System.out.println(cafeteria.cadastrarProduto(numeroMatricula, nomeProduto, quantidadeProduto,
                                valorProduto));
                    } catch (InputMismatchException e) {
                        System.err.println("Entrada inválida. Certifique-se de que os valores estão corretos.");
                        scan.nextLine();
                    }
                    break;
                case 7:
                    try {
                        System.out.println("Buscar Produto por id");
                        System.out.println("Qual o id do produto?");
                        idProduto = scan.nextInt();
                        System.out.println(cafeteria.buscarProdutoPorId(idProduto));
                    } catch (InputMismatchException e) {
                        System.err.println("É necessario passar um número inteiro");
                        scan.nextLine();
                    }
                    break;
                case 8:
                    System.out.println("Buscar produto por nome ");
                    System.out.println("Qual o nome do produto?");
                    nomeProduto = scan.nextLine();
                    System.out.println(cafeteria.buscarProdutoPorNome(nomeProduto));
                    break;
                case 9:
                    try {
                        System.out.println("Buscar produto por quantidade");
                        System.out.println("Qual a quantidade que voce deseja buscar?");
                        quantidadeProduto = scan.nextInt();
                        System.out.println(cafeteria.buscarProdutoPorQuantidadeOptional(quantidadeProduto));
                    } catch (InputMismatchException e) {
                        System.err.println("É necessario passar um número inteiro");
                        scan.nextLine();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 10:
                    try {
                        System.out.println("Excluir produto");
                        System.out.println("Qual o seu numero de matricula");
                        numeroMatricula = scan.nextInt();
                        System.out.println("Qual o id do produto");
                        idProduto = scan.nextInt();
                        System.out.println(cafeteria.excluirProduto(numeroMatricula, idProduto));
                    } catch (InputMismatchException e) {
                        System.err.println("É necessario passar um número inteiro");
                        scan.nextLine();
                    }
                    break;
                case 11:
                    try {
                        System.out.println("Alterar Produto");
                        System.out.println("Qual o seu numero de matricula? ");
                        numeroMatricula = scan.nextInt();
                        System.out.println("Qual o id do produto");
                        idProduto = scan.nextInt();
                        System.out.println("Qual vai ser a nova quantidade do produto?");
                        quantidadeProduto = scan.nextInt();
                        System.out.println(cafeteria.alterarProduto(numeroMatricula, idProduto, quantidadeProduto));
                    } catch (InputMismatchException e) {
                        System.err.println("É necessario passar um número inteiro");
                        scan.nextLine();
                    }
                    break;
                case 12:
                    System.out.println("Mostrando todos os produtos");
                    System.out.println(cafeteria.listarProdutosParaClientes());
                    break;
                case 13:
                    try {
                        System.out.println("Fazendo seu pedido");
                        System.out.println("Esses são todos os produtos que temos");
                        System.out.println(cafeteria.listarProdutosParaClientes());
                        System.out.println("Qual o seu nome?");
                        nomeCliente = scan.nextLine();
                        System.out.println("Qual a sua data de nascimento digite no modelo dd/MM/yyyy");
                        dataNascimento = scan.nextLine();
                        System.out.println("Qual o seu endereço?");
                        endereco = scan.nextLine();
                        System.out.println("Qual o nome do produto que voce deseja?");
                        nomeProduto = scan.nextLine();
                        System.out.println("Qual a quantidade que voce deseja?");
                        quantidadeProduto = scan.nextInt();
                        System.out.println(cafeteria.fazerPedido(nomeCliente, dataNascimento, endereco, nomeProduto,
                                quantidadeProduto));
                    } catch (InputMismatchException e) {
                        System.err.println("Entrada inválida. Certifique-se de que os valores estão corretos.");
                        scan.nextLine();
                    }
                    break;
                case 14:
                    try {
                        System.out.println("Mostrar seu pedido");
                        System.out.println("Qual o numero do seu pedido?");
                        idProduto = scan.nextInt();
                        System.out.println(cafeteria.buscarPedido(idProduto));
                    } catch (InputMismatchException e) {
                        System.err.println("É necessario passar um número inteiro");
                        scan.nextLine();
                    }
                    break;
                case 15:
                    try {
                        System.out.println("Excluindo seu pedido");
                        System.out.println("Qual o numero do seu pedido?");
                        numeroPedido = scan.nextInt();
                        System.out.println(cafeteria.excluirPedido(numeroPedido));
                    } catch (InputMismatchException e) {
                        System.err.println("É necessario passar um número inteiro");
                        scan.nextLine();
                    }
                    break;
                case 16:
                    try {
                        System.out.println("Alterando pedido");
                        System.out.println("Qual o numero do pedido para alterar?");
                        numeroPedido = scan.nextInt();
                        System.out.println("Qual a quantidade?");
                        quantidadeProduto = scan.nextInt();
                        System.out.println(cafeteria.alterarQuantidadePedido(numeroPedido, quantidadeProduto));
                    } catch (InputMismatchException e) {
                        System.err.println("É necessario passar um número inteiro");
                        scan.nextLine();
                    }
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.err.println("Opção inválida. Por favor, escolha uma das opções listadas.");
                    break;
            }
        } while (opcao != 0);
    }
}
