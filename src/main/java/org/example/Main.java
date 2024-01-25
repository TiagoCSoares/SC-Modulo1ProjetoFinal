package org.example;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static org.example.AdicionarContato.adicionarContato;
import static org.example.RemoverContato.removerContato;
import static org.example.Writer.escreverNaAgenda;


public class Main {

    public static void main(String[] args) {

        long idContato = 1;
        List<Contato> listaContatos = new ArrayList<>();

        System.out.println("##################\n" +
                "##### AGENDA #####\n" +
                "##################");
        Scanner scanner = new Scanner(System.in);

        System.out.println(">>>> Menu <<<<\n" +
                "1 - Adicionar Contato\n" +
                "2 - Remover Contato\n" +
                "3 - Editar Contato\n" +
                "4 - Exibir os Contatos\n" +
                "5 - Sair");
        int opcao = scanner.nextInt();

        while (opcao != 5) {
            switch (opcao) {
                case 1:
                    adicionarContato(listaContatos, idContato);
                    idContato++;
                    break;
                case 2:
                    removerContato(listaContatos);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
            System.out.println(">>>> Menu <<<<\n" +
                    "1 - Adicionar Contato\n" +
                    "2 - Remover Contato\n" +
                    "3 - Editar Contato\n" +
                    "4 - Sair");
            opcao = scanner.nextInt();
        }

    }
}