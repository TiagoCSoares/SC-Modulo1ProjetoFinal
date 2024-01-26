package org.example;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class RemoverContato {

    public static void removerContato() {
        boolean vazio = verificaListaVazia();
        if(vazio) {
            System.out.println("A agenda está vazia. Não há contatos a serem removidos.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o id do contato que você deseja remover:");
        long idRemove = scanner.nextLong();

        try {
            File arquivo = new File("src/agenda.txt");
            File arquivoTemp = new File("src/temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp));

            String contatoRemovido = "";
            String linhaAtual;
            boolean removido = false;

            linhaAtual = reader.readLine();
            writer.write(linhaAtual + System.getProperty("line.separator"));

            while ((linhaAtual = reader.readLine()) != null) {

                String[] elementos = linhaAtual.split("\\s*\\|\\s*");
                if (Long.parseLong(elementos[0].trim()) != idRemove) {
                    writer.write(linhaAtual + System.getProperty("line.separator"));
                } else {
                    contatoRemovido = linhaAtual;
                    removido = true;
                }
            }
            if (!removido) {
                System.out.println("O Contato não foi encontrado");
            } else {
                //System.out.printf("O contato com ID %d foi removido\n", idRemove);
                //System.out.printf("O contato: %s foi removido", contatoRemovido);
            }
            reader.close();
            writer.close();

            if (arquivo.delete()) {
                if (!arquivoTemp.renameTo(arquivo)) {
                    System.out.println("Falha ao renomear o arquivo temporário.");
                }
            } else {
                System.out.println("Falha ao excluir o arquivo original.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean verificaListaVazia() {

        try {
            File arquivo = new File("src/agenda.txt");
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linhaAtual;
            linhaAtual = reader.readLine(); //Retira o cabeçalho, caso exista
            linhaAtual = reader.readLine(); //A próxima linha já é os contatos
            if(!arquivo.exists() || arquivo.length() == 0  || linhaAtual == null) {
                return true;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}