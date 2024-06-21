package model;

import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable{
    private int matricula;

    public Funcionario(String nome, String dataNascimento, String endereco, int idade, int matricula) {
        super(nome, dataNascimento, endereco, idade);
        this.matricula = matricula;
    }

    public static Funcionario criarFuncionario(String nome, String dataNascimento, String endereco, int idade, int matricula){
        return new Funcionario(nome, dataNascimento, endereco, idade, matricula);
    }

    public int getMatricula() {
        return matricula;
    }

    @Override
    public String mostrarDetalhes() {
        return "Funcionário: " + getNome() + ", Matrícula: " + matricula;
    }

    @Override
    public String toString() {
        return "Funcionario [matricula=" + matricula + "]";
    }

    
}
