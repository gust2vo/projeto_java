package model;

public class Funcionario extends Pessoa{
    private int matricula;

    public Funcionario(String nome, String dataNascimento, String endereco, int idade, int matricula) {
        super(nome, dataNascimento, endereco, idade);
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Funcionario [matricula=" + matricula + "]";
    }

    
}
