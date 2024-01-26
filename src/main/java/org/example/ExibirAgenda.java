package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ExibirAgenda {

    public static void exibirAgenda() {
        BufferedReader reader = null;

        try {
            File arquivo = new File("src/agenda.txt");

            reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            String cabecalho;
            cabecalho = reader.readLine();
            linha = reader.readLine();
            if (!arquivo.exists() || arquivo.length() == 0 || linha == null) {
                System.out.println("A agenda está vazia.");
                return;
            }
            System.out.println(cabecalho);
            do{
                System.out.println(linha);
            } while ((linha = reader.readLine()) != null);
        } catch (IOException e) {
            System.out.println("Erro ao ler a agenda: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
