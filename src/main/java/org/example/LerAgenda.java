package org.example;

import java.io.*;

public class LerAgenda {
    public static long proximaIdDisponivel() {


        long idDisponivel = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader("src/agenda.txt"))){

            String linha;
            String cabecalho = reader.readLine();

            while((linha = reader.readLine()) != null) {
                String[] elementos = linha.split("\\s*\\|\\s*"); // Dividir a linha pelos delimitadores "|"
                if (elementos.length > 0 && !elementos[0].trim().isEmpty()) {
                    // O primeiro elemento após a divisão é a ID
                    idDisponivel = Long.parseLong(elementos[0].trim()) + 1;
                    // Agora idDisponivel contém a ID da linha atual
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler a agenda: " + e.getMessage());
        }
        return idDisponivel;
    }


    public static boolean verificaListaVazia() {

        File arquivo = new File("src/agenda.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))){

            String cabecalho = reader.readLine(); // Retira o cabeçalho, caso exista
            String linhaAtual = reader.readLine(); // A próxima linha já é os contatos
            if(!arquivo.exists() || arquivo.length() == 0  || linhaAtual == null) {
                return true;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado: " + e.getMessage());
            return true;
        } catch (IOException e) {
            System.out.println("Erro de E/S: " + e.getMessage());
            return true;
        }
        return false;
    }
}
