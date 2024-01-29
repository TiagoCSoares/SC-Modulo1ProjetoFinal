package org.example;

import java.util.Scanner;

import static org.example.Menu.menu;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        menu(scanner);

        scanner.close();
    }
}