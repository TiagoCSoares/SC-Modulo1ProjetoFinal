package org.example;

import java.util.List;
import java.util.Scanner;

import static org.example.EditarContato.*;

public class MenuEdicao {

    public static Contato menuEdicao (Contato contatoEditado, List<Telefone> listaTelefones, Scanner scanner) {

        int opcao;
        String nome = contatoEditado.getNome();
        String sobrenome = contatoEditado.getSobrenome();
        long idContato = contatoEditado.getId();


        do {
            System.out.println("""
                           \n>>>> Menu de Edição <<<<   \s
                    Qual informação você gostaria de editar?
                    1 - Nome
                    2 - Editar Telefone
                    3 - Adicionar Telefone
                    4 - Remover Telefone
                    5 - Sair de Editar""");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.print("Novo Nome: ");
                    nome = scanner.next();
                    System.out.print("Novo Sobrenome: ");
                    sobrenome = scanner.next();
                    break;
                case 2:
                    listaTelefones = editarTelefone(listaTelefones, scanner);
                    break;
                case 3:
                    listaTelefones = novoTelefone(listaTelefones, scanner);
                    break;
                case 4:
                    listaTelefones = removerTelefone(listaTelefones, scanner);
                    break;
                case 5:
                    System.out.println("Saindo do Menu de Edição");
                    break;
                default:
                    System.out.print("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);

        contatoEditado = new Contato(idContato, nome, sobrenome, listaTelefones);
        return contatoEditado;
    }
}
