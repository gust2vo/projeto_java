package model;

import java.io.Serializable;

public class Cliente extends Pessoa implements Serializable{
   

    public Cliente(String nome, String dataNascimento, String endereco, int idade) {
        super(nome, dataNascimento, endereco, idade);
       
    }

    public static Cliente criarCliente(String nome, String dataNascimento, String endereco, int idade){
        return new Cliente(nome, dataNascimento, endereco, idade);
    }

    @Override
    public String mostrarDetalhes() {
        return "Cliente: " + getNome();
    }

    @Override
    public String toString() {
        return "Cliente []" + mostrarDetalhes();
    }
    

}
