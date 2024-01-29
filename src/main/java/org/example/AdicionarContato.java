package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.EscreverNaAgenda.*;
import static org.example.LerAgenda.proximaIdDisponivel;

public class AdicionarContato {

    public static void adicionarContato(Scanner scanner) {

        criaArquivo();
        // Busca no arquivo a última Id cadastrada
        long idContato = proximaIdDisponivel();

        System.out.print("Nome do contato:");
        String nome = scanner.nextLine();
        System.out.print("Sobrenome do contato:");
        String sobrenome = scanner.nextLine();

        // Lista para armazenar os telefones
        List<Telefone> listaTelefones = new ArrayList<>();
        long idTelefone = 1;
        listaTelefones = adicionarTelefones(listaTelefones, idTelefone, scanner);
        idTelefone++;

        for (char tem = ' '; tem != 'N'; ) {
            System.out.print("Para adicionar um novo telefone " +
                    "digite S/s. Se o contato não possuir mais " +
                    "telefones a serem adicionados digite N/n");

            // transforma 'tem' em maiúsculo para facilitar a verificação
            tem = scanner.next().charAt(0);
            tem = Character.toUpperCase(tem);
            if (tem == 'S') {
                listaTelefones = adicionarTelefones(listaTelefones, idTelefone, scanner);
                idTelefone++;
            } else if (tem != 'N') {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }

        Contato novoContato = new Contato(idContato, nome, sobrenome, listaTelefones);

        escreverNaAgenda(novoContato);
    }




    public static List<Telefone> adicionarTelefones(List<Telefone> listaTelefones, long idTelefone, Scanner scanner) {

        int ddd;
        String dddString;
        long numero;

        // Variável para verificar se o número já existe
        boolean existe;

        // String recebe o novo número
        String verificaTelefone;

        do {
            System.out.print("DDD:");

            // Loop até que seja digitado um inteiro
            while (!scanner.hasNextInt()) {
                System.out.print("Digite um valor válido para DDD (Ex: 1-99): ");
                scanner.next(); // Consome a entrada inválida
            }
            ddd = scanner.nextInt();

            while (ddd > 99 || ddd < 1) {
                System.out.print("Digite um valor válido para DDD (Ex: 1-99): ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Digite um valor válido para DDD (Ex: 1-99): ");
                    scanner.next(); // Consome a entrada inválida
                }
                ddd = scanner.nextInt();
            }


            System.out.print("Número:");
            // Loop até que seja digitado um long
            while (!scanner.hasNextLong()) {
                System.out.print("Número inválido, tente novamente (Ex: 987654321): ");
                scanner.next(); // Consome a entrada inválida
            }
            numero = scanner.nextLong();
            while (numero > 1000000000 || numero < 1) {
                System.out.print("Número inválido, tente novamente (Ex: 987654321): ");
                while (!scanner.hasNextLong()) {
                    System.out.print("Número inválido, tente novamente (Ex: 987654321): ");
                    scanner.next(); // Consome a entrada inválida
                }
                numero = scanner.nextLong();
            }

            dddString = Integer.toString(ddd);

            // Passa o novo número para a String verificaTelefone
            verificaTelefone = dddString + " " + numero;

            // Verifica se o número já existe
            existe = telefoneExiste(verificaTelefone);

            // Caso o número já exista o loop continua
            if(existe)
                System.out.println("Esse telefone já está cadastrado para outro Contato! Tente novamente.");
        } while(telefoneExiste(verificaTelefone)); // chama a função telefoneExiste, caso retorne true pede outro número

        Telefone novoTel = new Telefone(idTelefone, dddString, numero);
        listaTelefones.add(novoTel);
        idTelefone++;
        return listaTelefones;
    }




    public static boolean telefoneExiste (String verificaTelefone) {

        File arquivo = new File("src/agenda.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))){

            // O cabecalho consome a primeira linha do arquivo
            String cabecalho = reader.readLine();
            String linhaAtual;

            // Percorre o arquivo em busca de um número igual ao verificaTelefone
            while((linhaAtual = reader.readLine()) != null) {

                // Divide a linha em elementos, os elementos são separados por | e -
                String[] elementos = linhaAtual.split("\\s*[|\\-]+\\s*");

                // indiceElementos recebe 2, referente ao primeiro elemento que contém números,
                // os primeiros são  0 = Id;  1 = Nome; 2 = Primeiro telefone: id (ddd) numero
                int indiceElementos = 2;

                do {
                    // Cada elemento é dividido novamente pelos espaços em branco
                    String[] subElementos = elementos[indiceElementos].trim().split("\\s+");

                    // subElementos[1] é o ddd, subElementos[2] é o número
                    String telefone = subElementos[1] + " " + subElementos[2];

                    // Retira os parenteses da String telefone. Os parenteses foram atribuidos junto com o ddd
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
