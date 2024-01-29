package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ExibirAgenda {


    /**
     * Função para exibir o arquivo .txt que contém a agenda.
     */
    public static void exibirAgenda() {

       File arquivo = new File("src/agenda.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))){

            // A string cabecalho consome a primeira linha do arquivo, que contém o cabeçalho do mesmo
            String cabecalho = reader.readLine();

            // A string linha consome a segunda linha, na qual os dados dos Contatos começam a ser inseridos
            String linha = reader.readLine();

            //Caso a segunda linha seja vazia a agenda está vazia ou só contém o cabeçalho
            if (!arquivo.exists() || arquivo.length() == 0 || linha == null) {
                System.out.println("\nA agenda está vazia.\n");
                return;
            }

            // Exibe a agenda
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
