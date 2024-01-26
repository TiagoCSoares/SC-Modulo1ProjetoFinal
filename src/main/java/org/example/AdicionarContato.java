package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.EscreverNaAgenda.escreverNaAgenda;
import static org.example.LerAgenda.proximaIdDisponivel;

public class AdicionarContato {

    public static void adicionarContato(List<Contato> listaContatos) {
        long idContato = proximaIdDisponivel();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome do contato:");
        String nome = scanner.next();
        System.out.print("Sobrenome do contato:");
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
        String dddString;
        long numero;
        boolean existe;
        String verificaTelefone;
        do {
            System.out.println("DDD:");
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
            dddString = Integer.toString(ddd);
            verificaTelefone = dddString + " " + numero;
            existe = telefoneExiste(verificaTelefone);
            if(existe)
                System.out.println("Esse telefone já está cadastrado para outro Contato! Tente novamente.");
        } while(telefoneExiste(verificaTelefone));

        Telefone novoTel = new Telefone(idTelefone, dddString, numero);
        listaTelefones.add(novoTel);
        idTelefone++;
        return listaTelefones;
    }




    public static boolean telefoneExiste (String verificaTelefone) {

        try {
            File arquivo = new File("src/agenda.txt");

            BufferedReader reader = new BufferedReader(new FileReader(arquivo));

            String cabecalho = reader.readLine();
            String linhaAtual;

            while((linhaAtual = reader.readLine()) != null) {
                String[] elementos = linhaAtual.split("\\s*[|\\-]+\\s*");
                int indiceElementos = 2;

                do {
                    String[] subElementos = elementos[indiceElementos].trim().split("\\s+");
                    String telefone = subElementos[1] + " " + subElementos[2];
                    telefone = telefone.replace("(", "").replace(")", "");

                    if (telefone.equals(verificaTelefone))
                        return true;

                    indiceElementos ++;
                } while (indiceElementos < elementos.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
