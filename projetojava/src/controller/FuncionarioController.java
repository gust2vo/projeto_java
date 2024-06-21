package controller;

import java.io.Serializable;
import java.util.List;

import model.Funcionario;

public class FuncionarioController implements Serializable{
    private List<Funcionario> funcionarios;

    public FuncionarioController() {
        this.funcionarios = CollectionsFactory.criarListaFuncionario();
    }

    public static FuncionarioController iniciaFuncionarioController() {
        return new FuncionarioController();
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

    public Funcionario verificarFuncionario(int numeroMatricula) throws Exception {
        return funcionarios.stream().filter(f -> f.getMatricula() == numeroMatricula).findFirst()
                .orElseThrow(() -> new Exception("Funcionario não encontrado"));
    }

    public void cadastrarFuncionario(String nome, String dataNascimento, String endereco, int idade, String cargo)throws Exception {
        int novaMatricula = gerarMatricula();
        Funcionario novoFuncionario = Funcionario.criarFuncionario(nome, dataNascimento, endereco, idade, novaMatricula);
        funcionarios.add(novoFuncionario);
    }

    public String listarFuncionarios() throws Exception {
        String listaFuncionarios = "Lista de funcionarios:\n";
        for (Funcionario f : funcionarios) {
            listaFuncionarios += "Nome do funcionario: " + f.getNome() +
                    ", Data de nascimento do funcionario: " + f.getDataNascimento() +
                    ", Endereço do funcionario: " + f.getEndereco() +
                    ", Idade do funcionario: " + f.getIdade() +
                    "\n";
        }
        return listaFuncionarios;
    }

    public String alterarEnderecoFuncionario(int matricula, String endereco) throws Exception {
        if (verificarFuncionario(matricula) != null) {
            for (Funcionario f : funcionarios) {
                if (f.getMatricula() == matricula) {
                    f.setEndereco(endereco);
                    return "Funcionario com a matricula " + f.getMatricula() + " alterado com sucesso";
                }
            }
        }
        return "Este funcionario não existe";
    }

    public String excluirFuncionario(int idFuncionario) throws Exception {
        Funcionario funcionario = verificarFuncionario(idFuncionario);
        if (funcionario != null) {
            funcionarios.remove(funcionario);
            return "Funcionário excluído com sucesso";
        }
        return "Este funcionario não existe";
    }

    public String buscarFuncionario(int numeroMatricula) throws Exception {
        if (verificarFuncionario(numeroMatricula) != null) {
            for (Funcionario f : funcionarios) {
                if (f.getMatricula() == numeroMatricula) {
                    return "O nome do funcionario é " + f.getNome() + " a sua data de nascimento é "
                            + f.getDataNascimento() + " o seu endereço é " + f.getEndereco() + " a sua idade é "
                            + f.getIdade() + " o numero é " + f.getMatricula();
                }

            }
        }
        return "Este funcionario não existe";
    }

    @Override
    public String toString() {
        return "FuncionarioController [funcionarios=" + funcionarios + "]";
    }

}
