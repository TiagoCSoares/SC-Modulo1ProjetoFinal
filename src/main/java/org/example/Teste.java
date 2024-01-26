package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Teste {

    public static Contato novasInformações(String linhaAtual) {

        String[] elementos = linhaAtual.split("\\s*[|\\-]+\\s*");
        long idContato = Long.parseLong(elementos[0].trim());
        String sobrenome = "";
        String nome = elementos[1].trim();
        System.out.printf("\n %s \n", nome);

        String telefone = elementos[2].trim();
        String[] subElementos = telefone.split("\\s+");
        List<Telefone> listaTelefones = new ArrayList<>();

        long idTelefone = Long.parseLong(subElementos[0]);
        String ddd = subElementos[1];
        String dddString = ddd.replace("(", "").replace(")", "");
        long numero = Long.parseLong(subElementos[2].trim());
        int indiceElementos = 3;
        int indiceSubElementos = 0;

        Telefone novoTel = new Telefone(idTelefone, dddString, numero);
        listaTelefones.add(novoTel);
        while (indiceElementos < elementos.length) {
            // Verificar se existem elementos suficientes para formar um número de telefone
            telefone = elementos[indiceElementos].trim();
            subElementos = telefone.split("\\s+");
            idTelefone = Long.parseLong(subElementos[0]);
            ddd = subElementos[1];
            dddString = ddd.replace("(", "").replace(")", "");
            numero = Long.parseLong(subElementos[2].trim());
            indiceElementos = 3;
            indiceSubElementos = 0;

            novoTel = new Telefone(idTelefone, dddString, numero);
            listaTelefones.add(novoTel);

                // Atualizar o índice para os próximos elementos
                indiceElementos++;

        }
        Contato contatoEditado = new Contato(idContato, nome, sobrenome, listaTelefones);
        return contatoEditado;
    }

    public static void main(String[] args) {
        String linhaAtual = "      21 | nome. sobrenome                               | 2  (32)          3  -  3  (34)   22\t";
        novasInformações(linhaAtual);
    }
}
