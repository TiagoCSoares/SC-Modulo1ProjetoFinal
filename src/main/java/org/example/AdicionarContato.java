package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Writer.escreverNaAgenda;

public class AdicionarContato {

    public static void adicionarContato(List<Contato> listaContatos, long idContato) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nome do Contato:");
        String nome = scanner.next();
        System.out.println("Informe o sobrenome do Contato:");
        String sobrenome = scanner.next();
        System.out.println("Informe o telefone do contato:");

        List<Telefone> listaTelefones = new ArrayList<>();
        long idTelefone = 1;
        String ddd;
        long numero;
        System.out.println("Digite o ddd:");
        ddd = scanner.next();
        System.out.println("Digite o número:");
        numero = scanner.nextInt();

        Telefone novoTel = new Telefone(idTelefone, ddd, numero);
        listaTelefones.add(novoTel);

        for(char tem = 'S'; tem != 'N'; ) {
            System.out.println("Para adicionar um novo telefone " +
                    "digite S/s. Se o contato não possuir mais" +
                    "telefones a serem adicionados digite N/n");
            tem = scanner.next().charAt(0);
            tem = Character.toUpperCase(tem);
            if(tem == 'S') {
                System.out.println("Digite o ddd:");
                ddd = scanner.next();
                System.out.println("Digite o número:");
                numero = scanner.nextInt();
            } else if(tem != 'N') {
                System.out.println("Opção inválida.");
            }
            novoTel = new Telefone(idTelefone, ddd, numero);
            listaTelefones.add(novoTel);
        }

        Contato novoContato = new Contato(idContato, nome, sobrenome, listaTelefones);
        listaContatos.add(novoContato);
        escreverNaAgenda(novoContato);
    }
}