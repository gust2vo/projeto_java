package model;

public class Pessoa {
    private String nome, dataNascimento, endereco;
    private int idade;

    public Pessoa(String nome, String dataNascimento, String endereco, int idade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.idade = idade;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    @Override
    public String toString() {
        return "Pessoa [nome=" + nome + ", dataNascimento=" + dataNascimento + ", endereco=" + endereco + ", idade="
                + idade + "]";
    }

    
}
