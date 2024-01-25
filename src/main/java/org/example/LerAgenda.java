package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LerAgenda {
    public static long proximaIdDisponivel() {

        BufferedReader reader = null;
        long idDisponivel = 0;

        try {
            File arquivo = new File("src/agenda.txt");

            if(!arquivo.exists() || arquivo.length() == 0) {
                idDisponivel = 1;
                return idDisponivel;
            }

            reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            boolean cabecalho = true;

            while((linha = reader.readLine()) != null) {
                if(cabecalho) {
                    cabecalho = false;
                    continue;
                }
                String[] elementos = linha.split("\\s*\\|\\s*"); // Dividir a linha pelos delimitadores "|"
                if (elementos.length > 0) {
                    // O primeiro elemento após a divisão é a ID
                    idDisponivel = Long.parseLong(elementos[0].trim()) + 1;
                    // Agora idDisponivel contém a ID da linha atual
                }
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
        return idDisponivel;
    }
}
