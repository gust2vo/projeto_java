package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Log {
    private static final String CAMINHO = "log/";

    public static void gravar(List<String> textos, String nome) throws Exception{
        File pasta = new File(CAMINHO);

        try {
            if (!pasta.exists()) {
                pasta.mkdirs();
            }
            File arquivo = new File(CAMINHO + nome + ".txt");
            
            if (arquivo.exists()) textos.addAll(ler(nome));
            PrintWriter gravar = new PrintWriter(new FileWriter(new File(CAMINHO + nome +".txt")));
            
            for (String txt : textos) {
                gravar.println(txt);
            }
            gravar.close();

        } catch (IOException e) {
            throw new Exception("Erro ao escrever o arquivo" + e.getMessage());
        }
    }
    public static List <String> ler(String nome) throws Exception{
        List<String> textos = new ArrayList<>();
        try {
            File arquivo = new File(CAMINHO + nome + ".txt");
            Scanner scanner = new Scanner(arquivo);

            while (scanner.hasNextLine()) {
                textos.add(scanner.nextLine());
            }
            return textos;
        } catch (Exception e) {
            throw new Exception("Arquivo não encontrado" + e.getMessage());
        }
        

    }
}

