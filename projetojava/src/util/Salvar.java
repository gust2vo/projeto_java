package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import controller.FuncionarioController;
import controller.PedidoController;
import controller.ProdutoController;
import model.Serializacao;

public abstract class Salvar{
    private static final File ARQUIVOPEDIDO = new File("log/obj/pedido.ser");
    private static final File ARQUIVOPRODUTO = new File("log/obj/produto.ser");
    private static final File ARQUIVOFUNCIONARIO = new File("log/obj/funcionario.ser");

    public Salvar(){}

    public static void salvarDados(PedidoController pedidoController) throws Exception {
        try {
            ARQUIVOPEDIDO.getParentFile().mkdirs();

            ObjectOutputStream objtOutput = new ObjectOutputStream(new FileOutputStream(ARQUIVOPEDIDO));
            objtOutput.writeObject(pedidoController);
            objtOutput.close();
        } catch (IOException e) {
            throw new Exception("Não foi possivel salvar");
        }
    }

    public static void salvarDados(FuncionarioController funcionarioController) throws Exception {
        try {
            ARQUIVOFUNCIONARIO.getParentFile().mkdirs();

            ObjectOutputStream objtOutput = new ObjectOutputStream(new FileOutputStream(ARQUIVOFUNCIONARIO));
            objtOutput.writeObject(funcionarioController);
            objtOutput.close();
        } catch (IOException e) {
            throw new Exception("Não foi possivel salvar");
        }
    }

    public static void salvarDados(ProdutoController produtoController) throws Exception {
        try {
            ARQUIVOPRODUTO.getParentFile().mkdirs();

            ObjectOutputStream objtOutput = new ObjectOutputStream(new FileOutputStream(ARQUIVOPRODUTO));
            objtOutput.writeObject(produtoController);
            objtOutput.close();
        } catch (IOException e) {
            throw new Exception("Não foi possivel salvar");
        }
    }

    public static PedidoController lerDadosPedido() throws Exception{
        try {
            if (ARQUIVOPEDIDO.exists() && ARQUIVOPEDIDO.isFile()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(ARQUIVOPEDIDO));

                return (PedidoController)objInput.readObject();
            }
            throw new Exception("O arquivo não foi encontrado");
        } catch (Exception e) {
            throw new Exception("Erro ao ler o arquivo");
        }
    }

    public static ProdutoController lerDadosProdutos() throws Exception{
        try {
            if (ARQUIVOPRODUTO.exists() && ARQUIVOPRODUTO.isFile()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(ARQUIVOPRODUTO));

                return (ProdutoController)objInput.readObject();
            }
            throw new Exception("O arquivo não foi encontrado");
        } catch (Exception e) {
            throw new Exception("Erro ao ler o arquivo");
        }
    }

    public static FuncionarioController lerDadosFuncionarios() throws Exception{
        try {
            if (ARQUIVOFUNCIONARIO.exists() && ARQUIVOFUNCIONARIO.isFile()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(ARQUIVOFUNCIONARIO));

                return (FuncionarioController)objInput.readObject();
            }
            throw new Exception("O arquivo não foi encontrado");
        } catch (Exception e) {
            throw new Exception("Erro ao ler o arquivo");
        }
    }
}
