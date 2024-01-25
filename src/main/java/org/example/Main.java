package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.AdicionarContato.adicionarContato;
import static org.example.ExibirAgenda.exibirAgenda;
import static org.example.RemoverContato.removerContato;


public class Main {

    public static void main(String[] args) {

        long idContato = 1;
        List<Contato> listaContatos = new ArrayList<>();

        System.out.println("##################\n" +
                "##### AGENDA #####\n" +
                "##################");
        Scanner scanner = new Scanner(System.in);

        System.out.println(">>>> Menu <<<<\n" +
                "1 - Exibir Agenda\n" +
                "2 - Adicionar Contato\n" +
                "3 - Remover Contato\n" +
                "4 - Editar Contato\n" +
                "5 - Exibir os Contatos\n" +
                "6 - Sair");
        int opcao = scanner.nextInt();

        while (opcao != 5) {
            switch (opcao) {
                case 1:
                    exibirAgenda();
                    break;
                case 2:
                    adicionarContato(listaContatos);
                    idContato++;
                    break;
                case 3:
                    removerContato(listaContatos);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
            System.out.println(">>>> Menu <<<<\n" +
                    "1 - Exibir Agenda\n" +
                    "2 - Adicionar Contato\n" +
                    "3 - Remover Contato\n" +
                    "4 - Editar Contato\n" +
                    "5 - Exibir os Contatos\n" +
                    "6 - Sair");
            opcao = scanner.nextInt();
        }

        scanner.close();
    }
}