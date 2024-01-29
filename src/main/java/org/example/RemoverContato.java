package org.example;

import java.io.*;
import java.util.Scanner;

import static org.example.LerAgenda.verificaListaVazia;

public class RemoverContato {

    public static void removerContato(Scanner scanner) {

        // Variável para verificar se há conteúdo na lista
        boolean vazio = verificaListaVazia();

        // Se a lista não possuir contatos o método é encerrado
        if (vazio) {
            System.out.println("A agenda está vazia. Não há contatos a serem removidos.");
            return;
        }

        System.out.print("Informe o id do contato que você deseja remover:");
        while (!scanner.hasNextLong()) {
            System.out.print("Digite um valor válido para ID: ");
            scanner.next(); // Consome a entrada inválida
        }
        long idRemove = scanner.nextLong();

        // Caminho para a agenda
        File arquivo = new File("src/agenda.txt");
        // Novo arquivo que será preenchido com os dados da agenda, exceto o contato removido
        File arquivoTemp = new File("src/temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo));
             BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp))) {

            String contatoRemovido = "";

            // Variável para verificar se o Contato foi removido
            boolean removido = false;

            // O cabecalho é escrito automaticamente no novo arquivo
            String cabecalho = reader.readLine();
            writer.write(cabecalho);

            String linhaAtual;

            // Loop que percorre todas as linhas do arquivo
            while ((linhaAtual = reader.readLine()) != null) {

                // Regex para separar a linha por espaços vazios ou por |
                String[] elementos = linhaAtual.split("\\s*\\|\\s*");

                // Caso o primeiro elemento encontrado na linha (Id) não seja o Id fornecido, sobreescreve a linha no novo arquivo
                if (Long.parseLong(elementos[0].trim()) != idRemove) {
                    writer.newLine();
                    writer.write(linhaAtual);
                } else {
                    // Contato removido recebe a linha removida
                    contatoRemovido = linhaAtual;
                    // Removido recebe true pra confirmar a remoção
                    removido = true;
                }
            }

            // Caso o Id não tenha sido encontrado retorna uma mensagem. Se não retorna o contato removido.
            if (!removido) {
                System.out.println("O Contato não foi encontrado");
            } else {
                writer.newLine();
                System.out.printf("O contato: %s foi removido\n", contatoRemovido);
            }

            // Deleta o arquivo original e renomeia o novo arquivo
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

