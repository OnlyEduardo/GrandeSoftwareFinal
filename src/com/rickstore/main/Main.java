package com.rickstore.main;

import com.rickstore.inventory.InstrumentsGenerator;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        boolean isRunning = true;

        InstrumentsGenerator.getInstrumentsToGenerate();
        Thread.sleep(3000);

        clearConsole();

        System.out.println("Bem vindo(a) a loja do Rick, versão final!");
        System.out.println("Digite '#comandos'");

        while (isRunning){
            Scanner scan = new Scanner(System.in);

            // Get input
            System.out.print("> ");
            String input = scan.next();

            checkCommands(input);

            if(input.equals("#exit"))
                isRunning = false;
        }

    }

    private static void checkCommands(String input) {

    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ignored) {}
    }
}
