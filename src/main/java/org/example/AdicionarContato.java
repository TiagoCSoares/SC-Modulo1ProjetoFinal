package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.EscreverNaAgenda.escreverNaAgenda;
import static org.example.LerAgenda.proximaIdDisponivel;

public class AdicionarContato {

    public static void adicionarContato(List<Contato> listaContatos) {
        long idContato = proximaIdDisponivel();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nome do contato:");
        String nome = scanner.next();
        System.out.println("Informe o sobrenome do contato:");
        String sobrenome = scanner.next();

        List<Telefone> listaTelefones = new ArrayList<>();
        long idTelefone = 1;
        listaTelefones = adicionarTelefones(listaTelefones, idTelefone);
        idTelefone++;

        for (char tem = ' '; tem != 'N'; ) {
            System.out.println("Para adicionar um novo telefone " +
                    "digite S/s. Se o contato não possuir mais" +
                    "telefones a serem adicionados digite N/n");
            tem = scanner.next().charAt(0);
            tem = Character.toUpperCase(tem);
            if (tem == 'S') {
                System.out.println("lakaka");
                listaTelefones = adicionarTelefones(listaTelefones, idTelefone);
                idTelefone++;
            } else if (tem != 'N') {
                System.out.println("Opção inválida.");
            }
        }

        Contato novoContato = new Contato(idContato, nome, sobrenome, listaTelefones);

        escreverNaAgenda(novoContato);
    }




    public static List<Telefone> adicionarTelefones(List<Telefone> listaTelefones, long idTelefone) {
        Scanner scanner = new Scanner(System.in);
        int ddd;
        long numero;
        System.out.println("Digite o ddd:");
        ddd = scanner.nextInt();
        while (ddd > 99 || ddd < 1) {
            System.out.println("DDD inválido, tente novamente: ");
            ddd = scanner.nextInt();
        }
        System.out.println("Digite o número:");
        numero = scanner.nextInt();
        while (numero > 1000000000 || numero < 1) {
            System.out.println("Número inválido, tente novamente: ");
            numero = scanner.nextLong();
        }
        String dddString = Integer.toString(ddd);
        Telefone novoTel = new Telefone(idTelefone, dddString, numero);
        listaTelefones.add(novoTel);
        idTelefone++;
        return listaTelefones;
    }
}
