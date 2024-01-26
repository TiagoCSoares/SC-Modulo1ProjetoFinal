package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Teste.novasInformações;

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
                    Contato contatoEditado = novasInformações(linhaAtual);
                   // List<Telefone> listaTelefones = contatoEditado.getTelefones();
                    /*List<Telefone> listaTelefones = new ArrayList<>();
                    long idTel  = 2;
                    long numero = 3;
                    Telefone tel = new Telefone(idTel, "32", numero);
                    listaTelefones.add(tel);
                    long lakaka = 21;

                    Contato contatoEditado = new Contato(lakaka, "nome.","sobrenome", listaTelefones);
                     */
                    writer.write(String.format("%8d | ", contatoEditado.getId()));
                    writer.write(String.format("%-45s | ", contatoEditado.getNomeCompleto()));

                    // Escreve os telefones
                    for (Telefone telefone : contatoEditado.getTelefones()) {
                        writer.write(String.format("%d  (%s)  %9d\t", telefone.getId(),telefone.getDdd(), telefone.getNumero()));
                        //writer.write("(" + telefone.getDdd() + ")   " + telefone.getNumero() + "    ");
                    }
                    writer.newLine();
                    editado = true;
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

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        Contato contatoEditado = new Contato(idContato, nome, sobrenome, listaTelefones);

        do {
            System.out.println( "       >>>> Menu de Edição <<<<    \n" +
                                "Qual informação você gostaria de editar?\n" +
                                "1 - Nome\n" +
                                "2 - Editar Telefone\n" +
                                "3 - Adicionar Telefone\n" +
                                "4 - Remover Telefone\n" +
                                "5 - Sair de Editar");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Nome: ");
                    nome = scanner.next();
                    System.out.println("Sobrenome: ");
                    sobrenome = scanner.next();
                    break;
                case 2:
                    listaTelefones = editarTelefone(listaTelefones);
                    break;
                case 3:
                    listaTelefones = adicionarTelefone(listaTelefones);
                    break;
                case 4:
                    listaTelefones = removerTelefone(listaTelefones);
                    break;
                case 5:
                    System.out.println("Saindo do Menu de Edição");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente:");
                    opcao = scanner.nextInt();
            }
        } while (opcao != 4);

            contatoEditado = new Contato(idContato, nome, sobrenome, listaTelefones);
            return contatoEditado;
    }


    public static List<Telefone> editarTelefone(List<Telefone> listaTelefones) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual o id do telefone?");
        long idEdita = scanner.nextLong();
        List<Telefone> novaListaEditada = new ArrayList<>();
        boolean encontrou = false;
        for(Telefone telefone: listaTelefones) {
            if(telefone.getId() == idEdita) {
                System.out.println("DDD:");
                String ddd = scanner.next();
                System.out.println("Número:");
                long numero = scanner.nextLong();
                telefone = new Telefone(idEdita, ddd, numero);
                novaListaEditada.add(telefone);
                encontrou = true;
                break;
            }
            novaListaEditada.add(telefone);
        }

        if(!encontrou) {
            System.out.println("Não foi possível encontrar o telefone.");
        }

        return novaListaEditada;
    }


    public static List<Telefone> adicionarTelefone(List<Telefone> listaTelefones) {
        Scanner scanner = new Scanner(System.in);

        long ultimoId = 0;
        for (Telefone telefone : listaTelefones) {
            if (telefone.getId() > ultimoId) {
                ultimoId = telefone.getId();
            }
        }

        System.out.println("DDD:");
        String ddd = scanner.next();
        System.out.println("Número:");
        long numero = scanner.nextLong();
        Telefone novoTelefone = new Telefone(ultimoId+1, ddd, numero);
        listaTelefones.add(novoTelefone);

        return listaTelefones;
    }


    public static List<Telefone> removerTelefone(List<Telefone> listaTelefones) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual o id do telefone?");
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
