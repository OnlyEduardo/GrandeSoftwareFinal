package com.rickstore.util;

public class UserOut {

    public static String commands = new StringBuilder()
    .append("\nCommandos da loja do Rick!\n\n").append(ConsoleColors.YELLOW)
    .append("comandos").append(ConsoleColors.RESET).append("       mostra todos os comandos.\n").append(ConsoleColors.YELLOW)
    .append("exit").append(ConsoleColors.RESET).append("           encerra a execução do programa.\n").append(ConsoleColors.YELLOW)
    .append("mostra").append(ConsoleColors.RESET).append("         mostra os instrumentos com os filtros atuais.\n").append(ConsoleColors.YELLOW)
    .append("mostra-filtro").append(ConsoleColors.RESET).append("  mostra os filtros atuais.\n").append(ConsoleColors.YELLOW)
    .append("filtra-madeira").append(ConsoleColors.RESET).append(" pede o tipo de madeira para filtrar.\n").append(ConsoleColors.YELLOW)
    .append("filtra-metal").append(ConsoleColors.RESET).append("   pede o tipo de metal para filtrar.\n").append(ConsoleColors.YELLOW)
    .append("filtra-tipo").append(ConsoleColors.RESET).append("    pede o tipo de instrumento para filtrar.\n").append(ConsoleColors.YELLOW)
    .append("filtra-familia").append(ConsoleColors.RESET).append(" pede a familia do instrumento para filtrar.\n").append(ConsoleColors.YELLOW)
    .append("filtra-valor").append(ConsoleColors.RESET).append("   pede os valores minimo e máximo que deseja pagar.\n").append(ConsoleColors.YELLOW)
    .append("limpa").append(ConsoleColors.RESET).append("          reseta os filtros colocados.\n").append(ConsoleColors.YELLOW)
    .toString();
}
