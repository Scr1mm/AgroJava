package com.example.util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public final class Entrada {
    public static final DateTimeFormatter FORMATO_DATA =
    DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private Entrada() {
    }

    public static double lerDouble(Scanner scanner, String rotulo, double minimo, double maximo) {
        while (true) {
            System.out.print(rotulo);
            String texto = scanner.nextLine().trim().replace(',', '.');
            try {
                double valor = Double.parseDouble(texto);
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
            } catch (NumberFormatException ignorada) {
            }
            System.out.printf("Valor inválido. Informe um número entre %s e %s.%n",
                    Formatador.numero(minimo), Formatador.numero(maximo));
        }
    }

    public static String lerData(Scanner scanner, String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String texto = scanner.nextLine().trim();
            try {
                LocalDate.parse(texto, FORMATO_DATA);
                return texto;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato dd/MM/aaaa.");
            }
        }
    }

    public static int lerOpcao(Scanner scanner, String rotulo) {
        System.out.print(rotulo);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
