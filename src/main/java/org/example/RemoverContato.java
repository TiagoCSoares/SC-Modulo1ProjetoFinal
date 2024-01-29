package org.example;

import java.io.*;
import java.util.Scanner;

import static org.example.LerAgenda.verificaListaVazia;

public class RemoverContato {

    public static void removerContato(Scanner scanner) {
        boolean vazio = verificaListaVazia();

        if (vazio) {
            System.out.println("A agenda está vazia. Não há contatos a serem removidos.");
            return;
        }


        System.out.print("Informe o id do contato que você deseja remover:");
        long idRemove = scanner.nextLong();

        File arquivo = new File("src/agenda.txt");
        File arquivoTemp = new File("src/temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo));
             BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp))) {

            String contatoRemovido = "";

            boolean removido = false;

            String cabecalho = reader.readLine();
            writer.write(cabecalho);

            String linhaAtual;

            while ((linhaAtual = reader.readLine()) != null) {

                String[] elementos = linhaAtual.split("\\s*\\|\\s*");
                if (Long.parseLong(elementos[0].trim()) != idRemove) {
                    writer.newLine();
                    writer.write(linhaAtual);
                } else {
                    contatoRemovido = linhaAtual;
                    removido = true;
                }
            }
            if (!removido) {
                System.out.println("O Contato não foi encontrado");
            } else {
                writer.newLine();
                System.out.printf("O contato: %s foi removido\n", contatoRemovido);
            }

            if (arquivo.delete()) {
                if (!arquivoTemp.renameTo(arquivo)) {
                    System.out.println("Falha ao renomear o arquivo temporário.");
                }
            } else {
                System.out.println("Falha ao excluir o arquivo original.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de E/S: " + e.getMessage());
        }
    }
}

