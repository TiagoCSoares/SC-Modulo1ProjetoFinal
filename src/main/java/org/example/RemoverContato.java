package org.example;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class RemoverContato {

    public static void removerContato(List<Contato> listaContatos) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o id do contato que você deseja remover:");
        long idRemove = scanner.nextLong();


        for (Iterator<Contato> iterator = listaContatos.iterator(); iterator.hasNext();) {
            Contato contato = iterator.next();

            // Verifica se o ID do contato é igual ao ID informado
            if (contato.getId() == idRemove) {
                // Remove o contato da lista
                iterator.remove();
                System.out.println("Contato removido com sucesso.");
                return; // Termina a execução após remover o contato
            }
        }

        // Se chegou até aqui, o contato com o ID informado não foi encontrado
        System.out.println("Contato com o ID " + idRemove + " não encontrado na lista.");
    }
}