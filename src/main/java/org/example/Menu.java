package org.example;

import java.util.Scanner;

import static org.example.AdicionarContato.adicionarContato;
import static org.example.EditarContato.editarContato;
import static org.example.ExibirAgenda.exibirAgenda;
import static org.example.RemoverContato.removerContato;

public class Menu {
    public static void menu(Scanner scanner) {

        int opcao;

        do {
            System.out.println("""
                    \n
                    ##################
                    ##### AGENDA #####
                    ##################""");
            System.out.println("""
                    >>>> Menu <<<<\s
                    1 - Exibir Agenda
                    2 - Adicionar Contato
                    3 - Remover Contato
                    4 - Editar Contato
                    5 - Sair
                    """);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    exibirAgenda();
                    break;
                case 2:
                    adicionarContato(scanner);
                    break;
                case 3:
                    removerContato(scanner);
                    break;
                case 4:
                    editarContato(scanner);
                    break;
                case 5:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 5);

    }
}
