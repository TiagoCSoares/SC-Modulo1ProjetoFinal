package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditarContato {

    public static void editarContato() {
        boolean vazio = verificaListaVazia();
        if (vazio) {
            System.out.println("A agenda está vazia. Não há contatos a serem editados.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o id do contato que você deseja editar:");
        long idEdita = scanner.nextLong();

        try {
            File arquivo = new File("src/agenda.txt");
            File arquivoTemp = new File("src/temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp));

            String linhaAtual;
            boolean editado = false;

            linhaAtual = reader.readLine();
            writer.write(linhaAtual + System.getProperty("line.separator"));

            while ((linhaAtual = reader.readLine()) != null) {
                String[] elementos = linhaAtual.split("\\s*\\|\\s*");
                if (Long.parseLong(elementos[0].trim()) != idEdita) {
                    writer.write(linhaAtual + System.getProperty("line.separator"));
                } else {
                    //Editar o contato
                    //Contato contatatoEditado = novasInformações(linhaAtual);
                    List<Telefone> listaTelefones = new ArrayList<>();
                    long idTel  = 2;
                    long numero = 3;
                    Telefone tel = new Telefone(idTel, "32", numero);
                    listaTelefones.add(tel);
                    long lakaka = 21;
                    Contato contatoEditado = new Contato(lakaka, "nome.","sobrenome", listaTelefones);
                    writer.write(String.format("%8d | ", contatoEditado.getId()));
                    writer.write(String.format("%-45s | ", contatoEditado.getNomeCompleto()));

                    // Escreve os telefones
                    for (Telefone telefone : contatoEditado.getTelefones()) {
                        writer.write(String.format("(%s)  %9d", telefone.getDdd(), telefone.getNumero()));
                        //writer.write("(" + telefone.getDdd() + ")   " + telefone.getNumero() + "    ");
                    }
                    writer.newLine();
                    editado = true;
                    System.out.println("lakaka");
                }
            }
            if (!editado) {
                System.out.println("O Contato não foi encontrado");
            } else {
                //System.out.printf("O contato com ID %d foi removido\n", idRemove);
                //System.out.printf("O contato: %s foi removido", contatoRemovido);
            }
            reader.close();
            writer.close();

            if (arquivo.delete()) {
                if (!arquivoTemp.renameTo(arquivo)) {
                    System.out.println("Falha ao renomear o arquivo temporário.");
                }
            } else {
                System.out.println("Falha ao excluir o arquivo original.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean verificaListaVazia() {

        try {
            File arquivo = new File("src/agenda.txt");
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linhaAtual;
            linhaAtual = reader.readLine(); //Retira o cabeçalho, caso exista
            linhaAtual = reader.readLine(); //A próxima linha já é os contatos
            if (!arquivo.exists() || arquivo.length() == 0 || linhaAtual == null) {
                return true;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static Contato novasInformações(String linhaAtual) {

        String[] elementos = linhaAtual.split("\\s*\\|\\s*");
        long idContato = Long.parseLong(elementos[0].trim());
        String nome = elementos[1].trim().substring(0, 15);
        String sobreNome = elementos[2].trim().substring(0, 30);

        List<Telefone> listaTelefones = new ArrayList<>();
        long idTelefone = Long.parseLong(elementos[3].trim());
        int ddd = Integer.parseInt(elementos[4].trim());
        String dddString = Integer.toString(ddd);
        long numero = Long.parseLong(elementos[5].trim());
        int indiceElementos = 6;

        Telefone novoTel = new Telefone(idTelefone, dddString, numero);
        listaTelefones.add(novoTel);
        while (indiceElementos < elementos.length) {
            // Verificar se existem elementos suficientes para formar um número de telefone
            if (indiceElementos + 2 < elementos.length) {
                idTelefone = Long.parseLong(elementos[indiceElementos].trim());
                ddd = Integer.parseInt(elementos[indiceElementos + 1].trim());
                dddString = Integer.toString(ddd);
                numero = Long.parseLong(elementos[indiceElementos + 2].trim());

                // Criar um objeto Telefone e adicioná-lo à lista
                Telefone telefone = new Telefone(idTelefone, dddString, numero);
                listaTelefones.add(telefone);

                // Atualizar o índice para os próximos elementos
                indiceElementos += 3;
            } else {
                // Caso não haja elementos suficientes, interromper o loop
                break;
            }
        }

        System.out.println("Qual informação você gostaria de editar?");
        System.out.println("1 - Nome\n" +
                "2 - Sobrenome\n" +
                "3 - Telefone\n" +
                "4 - Sair de Editar");
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        while (opcao != 4) {
            switch (opcao) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    opcao = scanner.nextInt();
            }
        }
            Contato contatoEditado = new Contato(idContato, nome, sobreNome, listaTelefones);
            return contatoEditado;
    }
}
