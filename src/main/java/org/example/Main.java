package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.AdicionarContato.adicionarContato;
import static org.example.EditarContato.editarContato;
import static org.example.ExibirAgenda.exibirAgenda;
import static org.example.RemoverContato.removerContato;


public class Main {

    public static void main(String[] args) {

        long idContato = 1;
        List<Contato> listaContatos = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("##################\n" +
                "##### AGENDA #####\n" +
                "##################");
        int opcao = 0;

        do {
            System.out.println("\n\n>>>> Menu <<<<\n" +
                    "1 - Exibir Agenda\n" +
                    "2 - Adicionar Contato\n" +
                    "3 - Remover Contato\n" +
                    "4 - Editar Contato\n" +
                    "5 - Sair");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    exibirAgenda();
                    break;
                case 2:
                    adicionarContato(listaContatos);
                    idContato++;
                    break;
                case 3:
                    removerContato();
                    break;
                case 4:
                    editarContato();
                    break;
                case 5:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}