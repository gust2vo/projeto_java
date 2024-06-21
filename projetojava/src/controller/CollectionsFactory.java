package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Pedido;
import model.Funcionario;
import model.Produto;

public abstract class CollectionsFactory implements Serializable{

    public static List<Funcionario> criarListaFuncionario(){
        return new ArrayList<>();
    }

    public static List<Produto> criarListaProduto(){
        return new ArrayList<>();
    }

    public static List<Pedido> criarListaPedidos(){
        return new ArrayList<>();
    }
}
