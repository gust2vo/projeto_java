package util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class util {
    public static String formatarNome(String nome) {
        return nome.trim().toLowerCase().replace(" ", "");
    }

    public static int calcularIdade(String dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNasc = LocalDate.parse(dataNascimento, formatter);
        
        Period periodo = Period.between(dataNasc, LocalDate.now());
        return periodo.getYears();
    }

    public static boolean verificarIdade(String dataNascimento) {
        int idade = calcularIdade(dataNascimento);
        return idade >= 14;
    }

    
}
