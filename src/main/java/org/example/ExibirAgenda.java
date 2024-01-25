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

            if (!arquivo.exists() || arquivo.length() == 0) {
                System.out.println("A agenda está vazia.");
                return;
            }

            reader = new BufferedReader(new FileReader(arquivo));
            String linha;

            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
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
