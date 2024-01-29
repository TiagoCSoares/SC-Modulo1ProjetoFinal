package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ExibirAgenda {

    public static void exibirAgenda() {

       File arquivo = new File("src/agenda.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))){

            String cabecalho = reader.readLine();
            String linha = reader.readLine();
            if (!arquivo.exists() || arquivo.length() == 0 || linha == null) {
                System.out.println("\nA agenda está vazia.\n");
                return;
            }
            System.out.println(cabecalho);
            do{
                System.out.println(linha);
            } while ((linha = reader.readLine()) != null);
            System.out.println();
        } catch (IOException e) {
            System.out.println("\nErro ao ler a agenda: " + e.getMessage() + "\n");
        }
    }
}
