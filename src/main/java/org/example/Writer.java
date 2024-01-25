package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Writer {

    public static void escreverNaAgenda(Contato novoContato) {

        BufferedWriter writer = null;


        try {
            File arquivo = new File("src/agenda.txt");
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter("src/agenda.txt", true));

            writer.write("ID: " + novoContato.getId() + "\t");
            writer.write("Nome: " + novoContato.getNome() + "\t");
            writer.write("Sobrenome: " + novoContato.getSobrenome() + "\t");

            // Escreve os telefones
            for (Telefone telefone : novoContato.getTelefones()) {
                writer.write("Telefone - DDD: " + telefone.getDdd() + ", Número: " + telefone.getNumero() + "\t");
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
}