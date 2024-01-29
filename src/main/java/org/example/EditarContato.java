package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.AdicionarContato.adicionarTelefones;
import static org.example.AdicionarContato.telefoneExiste;
import static org.example.LerAgenda.verificaListaVazia;
import static org.example.MenuEdicao.menuEdicao;

public class EditarContato {

    public static void editarContato(Scanner scanner) {

        boolean vazio = verificaListaVazia();
        if (vazio) {
            System.out.println("A agenda está vazia. Não há contatos a serem editados.");
            return;
        }

        System.out.print("Informe o id do contato que você deseja editar:");
        long idEdita = scanner.nextLong();
        File arquivo = new File("src/agenda.txt");
        File arquivoTemp = new File("src/temp.txt");

        try  (BufferedReader reader = new BufferedReader(new FileReader(arquivo));
              BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp))){

            boolean editado = false;

            String cabecalho = reader.readLine();
            writer.write(cabecalho);
            writer.newLine();
            String linhaAtual;

            while ((linhaAtual = reader.readLine()) != null) {
                String[] elementos = linhaAtual.split("\\s*\\|\\s*");
                if (Long.parseLong(elementos[0].trim()) != idEdita) {
                    writer.write(linhaAtual);
                    writer.newLine();
                } else {
                    //Editar o contato
                    Contato contatoEditado = novasInformacoes(linhaAtual, scanner);

                    writer.write(String.format("%-8d | ", contatoEditado.getId()));
                    writer.write(String.format("%-45s | ", contatoEditado.getNomeCompleto()));

                    // Escreve os telefones
                    boolean primeiro = true;
                    for (Telefone telefone : contatoEditado.getTelefones()) {
                        if(primeiro) {
                            writer.write(String.format("%d  (%s)  %-9d\t", telefone.getId(), telefone.getDdd(), telefone.getNumero()));
                            primeiro = false;
                        } else {
                            writer.write(String.format(" - %d  (%s)  %-9d\t", telefone.getId(), telefone.getDdd(), telefone.getNumero()));
                        }
                    }
                    writer.newLine();
                    editado = true;
                }
            }
            if (!editado)
                System.out.println("O Contato não foi encontrado");


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






    public static Contato novasInformacoes(String linhaAtual, Scanner scanner) {

        String[] elementos = linhaAtual.split("\\s*[|\\-]+\\s*");
        long idContato = Long.parseLong(elementos[0].trim());
        String nome = elementos[1].trim();
        String sobrenome = "";

        String telefone = elementos[2].trim();
        String[] subElementos = telefone.split("\\s+");
        List<Telefone> listaTelefones = new ArrayList<>();

        long idTelefone = Long.parseLong(subElementos[0].trim());
        String ddd = subElementos[1];
        String dddString = ddd.replace("(", "").replace(")", "");
        long numero = Long.parseLong(subElementos[2].trim());
        int indiceElementos = 3;

        Telefone novoTel = new Telefone(idTelefone, dddString, numero);
        listaTelefones.add(novoTel);

        while (indiceElementos < elementos.length) {
            // Verificar se existem elementos suficientes para formar um número de telefone
            telefone = elementos[indiceElementos].trim();
            subElementos = telefone.split("\\s+");

            idTelefone = Long.parseLong(subElementos[0].trim());
            ddd = subElementos[1];
            dddString = ddd.replace("(", "").replace(")", "");
            numero = Long.parseLong(subElementos[2].trim());

            // Criar um objeto Telefone e adicioná-lo à lista
            novoTel = new Telefone(idTelefone, dddString, numero);
            listaTelefones.add(novoTel);
            // Atualizar o índice para os próximos elementos
            indiceElementos += 3;
        }


        Contato contatoEditado = new Contato(idContato, nome, sobrenome, listaTelefones);
        contatoEditado = menuEdicao(contatoEditado, listaTelefones, scanner);

        return contatoEditado;
    }


    public static List<Telefone> editarTelefone(List<Telefone> listaTelefones, Scanner scanner) {

        System.out.print("Qual o id do telefone?");
        long idEdita = scanner.nextLong();
        List<Telefone> novaListaEditada = new ArrayList<>();
        boolean encontrou = false;
        for(Telefone telefone: listaTelefones) {
            if(telefone.getId() == idEdita) {
                String verificaTelefone;
                do {
                    System.out.println("DDD:");
                    int ddd = scanner.nextInt();
                    while (ddd > 99 || ddd < 1) {
                        System.out.print("DDD inválido, tente novamente: ");
                        ddd = scanner.nextInt();
                    }
                    System.out.print("Número:");
                    long numero = scanner.nextLong();
                    while (numero > 1000000000 || numero < 1) {
                        System.out.print("Número inválido, tente novamente: ");
                        numero = scanner.nextLong();
                    }
                    String dddString = Integer.toString(ddd);
                    verificaTelefone = dddString + " " + numero;
                    boolean existe = telefoneExiste(verificaTelefone);
                    if(existe)
                        System.out.println("Esse telefone já está cadastrado para outro Contato! Tente novamente.");
                } while(telefoneExiste(verificaTelefone));
                break;
            }
            novaListaEditada.add(telefone);
        }

        if(!encontrou) {
            System.out.println("Não foi possível encontrar o telefone.");
        }

        return novaListaEditada;
    }


    public static List<Telefone> novoTelefone(List<Telefone> listaTelefones, Scanner scanner) {

        long ultimoId = 0;
        for (Telefone telefone : listaTelefones) {
            if (telefone.getId() >= ultimoId) {
                ultimoId = telefone.getId();
            }
        }
        return adicionarTelefones(listaTelefones, ultimoId+1, scanner);
    }




    public static List<Telefone> removerTelefone(List<Telefone> listaTelefones, Scanner scanner) {

        System.out.print("Qual o id do telefone?");
        long idRemove = scanner.nextLong();
        List<Telefone> novaListaEditada = new ArrayList<>();

        boolean removeu = false;
        for(Telefone telefone: listaTelefones) {
            if(telefone.getId() == idRemove) {
                removeu = true;
                continue;
            }
            novaListaEditada.add(telefone);
        }

        if(!removeu) {
            System.out.println("Não foi possível encontrar o telefone.");
        }

        return novaListaEditada;
    }
}
