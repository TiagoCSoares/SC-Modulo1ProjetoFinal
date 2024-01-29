package org.example;

import java.io.*;

public class EscreverNaAgenda {

    public static void escreverNaAgenda(Contato novoContato) {

        File arquivo = new File("src/agenda.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {

            writer.write(String.format("%-8d | ", novoContato.getId()));
            writer.write(String.format("%-45s | " ,novoContato.getNomeCompleto()));

            // Escreve os telefones
            boolean primeiro = true;
            for (Telefone telefone : novoContato.getTelefones()) {
                if(primeiro) {
                    writer.write(String.format("%d  (%s)  %-9d\t", telefone.getId(), telefone.getDdd(), telefone.getNumero()));
                    primeiro = false;
                } else {
                    writer.write(String.format(" - %d  (%s)  %-9d\t", telefone.getId(), telefone.getDdd(), telefone.getNumero()));
                }
            }

            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }



    public static void escreverCabecalho() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/agenda.txt", true))) {

            // Escreve o cabeçalho no arquivo
            writer.write(String.format("%-8s | %-45s | %-10s", "Id", "Nome", "Telefone(s): Id   (DDD)   Numero"));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao escrever o cabeçalho: " + e.getMessage());
        }
    }



    public static void criaArquivo() {

        File arquivo = new File("src/agenda.txt");

        try  {
            if (!arquivo.exists()) {
                if (arquivo.createNewFile()) {
                    escreverCabecalho();
                }
            } else if (arquivo.length() == 0) {
                escreverCabecalho();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
