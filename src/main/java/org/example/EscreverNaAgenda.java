package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class EscreverNaAgenda {

    public static void escreverNaAgenda(Contato novoContato) {

        BufferedWriter writer = null;

        try {
            File arquivo = new File("src/agenda.txt");

            if (!arquivo.exists()) {
                arquivo.createNewFile();
                escreverCabecalho();
            } else if(arquivo.length() == 0) {
                escreverCabecalho();
            }

            writer = new BufferedWriter(new FileWriter("src/agenda.txt", true));
            writer.write(String.format("%8d | ", novoContato.getId()));
            writer.write(String.format("%-45s | " ,novoContato.getNomeCompleto()));

            // Escreve os telefones
            for (Telefone telefone : novoContato.getTelefones()) {
                writer.write(String.format("(%s)  %9d", telefone.getDdd(), telefone.getNumero()));
                //writer.write("(" + telefone.getDdd() + ")   " + telefone.getNumero() + "    ");
            }

            writer.newLine();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private static void escreverCabecalho() throws IOException {
        BufferedWriter writer = null;
        try {
            // Abre o arquivo para escrita
            writer = new BufferedWriter(new FileWriter("src/agenda.txt", true));

            // Escreve o cabeçalho no arquivo
            writer.write(String.format("%-8s | %-45s | %-10s", "Id", "Nome", "Telefone(s)"));
            writer.newLine();
        } finally {
            // Fecha o BufferedWriter no bloco finally para garantir que seja fechado mesmo em caso de exceção
            if (writer != null) {
                writer.close();
            }
        }
    }
}