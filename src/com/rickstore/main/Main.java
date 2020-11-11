package com.rickstore.main;

import com.rickstore.enumerators.*;
import com.rickstore.inventory.InstrumentsGenerator;
import com.rickstore.inventory.Inventory;
import com.rickstore.specs.Instrument;
import com.rickstore.specs.PercussionInstrument;
import com.rickstore.specs.StringInstrument;
import com.rickstore.specs.WindInstrument;
import com.rickstore.util.ConsoleColors;
import com.rickstore.util.UserOut;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static  final NumberFormat formatter = new DecimalFormat("###,###,###.##");

    private static boolean isRunning = true;
    private static final List<Instrument> actualInstruments = new ArrayList<>();

    private static float priceFiltermin = Float.MIN_VALUE;
    private static float priceFiltermax = Float.MAX_VALUE;
    private static Metal metalFilter = Metal.Nenhum;
    private static TradeMark tradeMarkFilter = TradeMark.Nenhum;
    private static Type typeFilter = Type.Nenhum;
    private static Wood woodFilter = Wood.Nenhum;
    private static Family familiaFilter = Family.Nenhum;

    public static void main(String[] args) {
        InstrumentsGenerator.getInstrumentsToGenerate();
        actualInstruments.addAll(Inventory.allInstruments);

        System.out.println(ConsoleColors.BLUE_BOLD);
        System.out.println("Bem vindo(a) a loja do Rick, versão final!");
        System.out.print(ConsoleColors.RESET);
        System.out.println("Digite "+ ConsoleColors.YELLOW +"comandos" + ConsoleColors.RESET);

        while (isRunning){
            Scanner scan = new Scanner(System.in);

            System.out.print(ConsoleColors.GREEN + "> " + ConsoleColors.RESET);
            String input = scan.next();

            checkCommands(input.toLowerCase().replace(" ", ""));
        }

        System.out.println(ConsoleColors.RED + "Encerrando programa" + ConsoleColors.RESET);
    }

    private static void checkCommands(String input) {
        switch (input){
            case "comandos":
                System.out.println(UserOut.commands);
                break;
            case "exit":
                isRunning = false;
                break;
            case "mostra":
                showInstruments();
                break;
            case "mostra-filtro":
                showFilters();
                break;
            case "filtra-madeira":
                filtraWood();
                break;
            case "filtra-metal":
                filtraMetal();
                break;
            case "filtra-tipo":
                filtraType();
                break;
            case "filtra-familia":
                filtraFamily();
                break;
            case "filtra-valor":
                filtraValue();
                break;
            case "limpa":
                priceFiltermin = Float.MIN_VALUE;
                priceFiltermax = Float.MAX_VALUE;
                metalFilter = Metal.Nenhum;
                tradeMarkFilter = TradeMark.Nenhum;
                typeFilter = Type.Nenhum;
                woodFilter = Wood.Nenhum;
                familiaFilter = Family.Nenhum;
                break;
            default:
                System.out.println("Comando '"+ input +"' desconhecido");
        }
    }

    private static void showInstruments() {
        actualInstruments.clear();

        if (priceFiltermin == Float.MIN_VALUE &&
        priceFiltermax == Float.MAX_VALUE &&
        metalFilter == Metal.Nenhum &&
        tradeMarkFilter == TradeMark.Nenhum &&
        typeFilter == Type.Nenhum &&
        woodFilter == Wood.Nenhum &&
        familiaFilter == Family.Nenhum){
            actualInstruments.addAll(Inventory.allInstruments);
            System.out.println("Há " + ConsoleColors.CYAN + actualInstruments.size() + ConsoleColors.RESET + " instrumentos disponíveis");
            showActIns();
            return;
        }

        for(Instrument in: Inventory.allInstruments){
            int score = 0;

            // Type search
            if(typeFilter.equals(Type.Nenhum)){
                score++;
            } else if(in instanceof PercussionInstrument){
                if(in.getInstrumentType().equals(typeFilter))
                    score++;
            } else if(in instanceof StringInstrument) {
                if (in.getInstrumentType().equals(typeFilter))
                    score++;
            }

            // TradeMark search
            if (tradeMarkFilter.equals(TradeMark.Nenhum) || in.matchTradeMark(tradeMarkFilter)){
                score++;
            }

            // Family search
            if (familiaFilter.equals(Family.Nenhum) || in.matchFamily(familiaFilter)){ score++; }

            // Wood and Metal search
            if(in instanceof WindInstrument){
                if(((WindInstrument)in).matchParts(woodFilter, metalFilter)){
                    score++;
                }
            } else if(in instanceof StringInstrument){
                if((((StringInstrument) in).matchWood(woodFilter) || woodFilter.equals(Wood.Nenhum)) && metalFilter.equals(Metal.Nenhum)){
                    score++;
                }
            } else if(in instanceof PercussionInstrument){
                if((((PercussionInstrument) in).matchParts(woodFilter, metalFilter) && metalFilter.equals(Metal.Nenhum))){
                    score++;
                }
            }

            // Price search
            if(in.matchRangeValue(priceFiltermin, priceFiltermax))
                score++;

            // Valid by score
            if (score == 5)
                actualInstruments.add(in);
        }

        if(actualInstruments.size() == 0)
            System.out.println("Infelizmente não há instrumentos compátiveis com esses filtros :(");
        else {
            System.out.println("Há " + ConsoleColors.CYAN + actualInstruments.size() + ConsoleColors.RESET + " instrumentos disponíveis para esses filtro");
            showActIns();
        }
    }

    private static void showActIns(){
        Scanner scan = new Scanner(System.in);

        System.out.print("Deseja mostrar tudo de uma vez ? (Y/n) ");

        boolean yn = true;

        try{
            String val = scan.nextLine();

            yn = !(val.equals("n") ||
                   val.equals("não") ||
                   val.equals("no") ||
                   val.equals("nao"));

        } catch (Exception ignored){}

        if(yn){
            System.out.println("========================");
            actualInstruments.forEach(in -> {
                System.out.println(in);
                System.out.println("========================");
            });
            return;
        }

        int helper = 0;
        System.out.println(ConsoleColors.BLUE + "Mostrando de 5 em 5 instrumentos" + ConsoleColors.RESET);
        System.out.println("========================");

        for (Instrument ai : actualInstruments) {

            if (helper == 5) {
                scan = new Scanner(System.in);
                System.out.println("Digite qualquer tecla para mostrar mais 5.");
                System.out.println("aperte " + ConsoleColors.YELLOW + "s" + ConsoleColors.RESET + " para terminar de mostrar tudo.");
                String input = scan.nextLine();

                if(!input.equals("s"))
                    helper = 0;
            }

            System.out.println(ai);
            System.out.println("========================");
            helper++;
        }

    }

    private static void showFilters() {
        System.out.println(ConsoleColors.BLUE + "Filtros atuais:" + ConsoleColors.RESET);

        String filters = ConsoleColors.YELLOW +
                "Preço mínimo: " + ConsoleColors.RESET + "R$" + formatter.format(priceFiltermin) + "\n" + ConsoleColors.YELLOW +
                "Preço máximo: " + ConsoleColors.RESET + "R$" + formatter.format(priceFiltermax) + "\n" + ConsoleColors.YELLOW +
                "Madeira: " + ConsoleColors.RESET + woodFilter + "\n" + ConsoleColors.YELLOW +
                "Metal: " + ConsoleColors.RESET + metalFilter + "\n" + ConsoleColors.YELLOW +
                "Marca: " + ConsoleColors.RESET + tradeMarkFilter + "\n" + ConsoleColors.YELLOW +
                "Familia: " + ConsoleColors.RESET + familiaFilter + "\n" + ConsoleColors.YELLOW +
                "Tipo: " + ConsoleColors.RESET + typeFilter + "\n" + ConsoleColors.YELLOW;

        System.out.println(filters);
    }

    private static void filtraWood() {
        Scanner scan;

        System.out.println("Digite o nome da madeira que deseja filtrar");

        for(Wood w: Wood.values()){
            System.out.println("    " + w);
        }

        while (true){
            scan = new Scanner(System.in);

            try {
                String wood = scan.next();
                woodFilter = Wood.valueOf(wood);
                break;
            } catch (Exception ignores){
                System.out.println("Madeira digitada inválida");
            }
        }

        System.out.println("Filtro para " + woodFilter + " criado com sucesso!");
    }

    private static void filtraMetal() {
        Scanner scan;

        System.out.println("Digite o nome do metal que deseja filtrar");

        for(Metal m: Metal.values()){
            System.out.println("    " + m);
        }

        while (true){
            scan = new Scanner(System.in);

            try {
                String metal = scan.next();
                metalFilter = Metal.valueOf(metal);
                break;
            } catch (Exception ignores){
                System.out.println("Metal digitado inválido");
            }
        }

        System.out.println("Filtro para " + metalFilter + " criado com sucesso!");
    }

    private static void filtraType() {
        Scanner scan;

        System.out.println("Digite o nome do tipo de instrumento que deseja filtrar");

        for(Type t: Type.values()){
            System.out.println("    " + t);
        }

        while (true){
            scan = new Scanner(System.in);

            try {
                String type = scan.next();
                typeFilter = Type.valueOf(type);
                break;
            } catch (Exception ignores){
                System.out.println("Tipo digitado inválido");
            }
        }

        System.out.println("Filtro para " + typeFilter + " criado com sucesso!");
    }

    private static void filtraFamily() {
        Scanner scan;

        System.out.println("Digite o nome da familia que deseja filtrar");

        for(Family m: Family.values()){
            System.out.println("    " + m);
        }

        while (true){
            scan = new Scanner(System.in);

            try {
                String family = scan.next();
                familiaFilter = Family.valueOf(family);
                break;
            } catch (Exception ignores){
                System.out.println("Familia digitada inválida");
            }
        }

        System.out.println("Filtro para " + familiaFilter + " criado com sucesso!");
    }

    private static void filtraValue() {

        Scanner scan;

        while (true){
            scan = new Scanner(System.in);

            try {
                System.out.print("Preço minimo: ");
                priceFiltermin = scan.nextFloat();
            } catch (Exception ignored) {
                System.out.println("Preço digitado inválido");
                continue;
            }

            scan = new Scanner(System.in);

            try {
                System.out.print("Preço Máximo: ");
                priceFiltermax = scan.nextFloat();
                break;
            } catch (Exception ignored) {
                System.out.println("Preço digitado inválido");
            }
        }

        System.out.println("Filtro para preço minimo criado: R$" + formatter.format(priceFiltermin));
        System.out.println("Filtro para preço maximo criado: R$" + formatter.format(priceFiltermax));
    }
}
