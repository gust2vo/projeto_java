package model;

public class Cliente extends Pessoa{
    private int quantidade;

    public Cliente(String nome, String dataNascimento, String endereco, int idade, int quantidade) {
        super(nome, dataNascimento, endereco, idade);
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int numeroCupom) {
        this.quantidade = numeroCupom;
    }

    @Override
    public String toString() {
        return "Cliente [quantidade=" + quantidade + "]";
    }

    

}
