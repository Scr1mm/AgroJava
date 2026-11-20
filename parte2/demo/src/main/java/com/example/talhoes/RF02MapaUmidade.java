package com.example.talhoes;
import com.example.util.Entrada;
import com.example.util.Formatador;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class RF02MapaUmidade {
    public static final int LINHAS = 4;
    public static final int COLUNAS = 4;
    public static final double LIMITE_IRRIGACAO = 30;

    private RF02MapaUmidade() {
    }

    public static String nomeTalhao(int linha, int coluna) {
        return (char) ('A' + linha) + String.valueOf(coluna + 1);
    }

    public static double[][] cadastrarTalhoes(Scanner scanner) {
        double[][] mapa = new double[LINHAS][COLUNAS];
        for (int linha = 0; linha < LINHAS; linha++) {
            for (int coluna = 0; coluna < COLUNAS; coluna++) {
                mapa[linha][coluna] = Entrada.lerDouble(scanner,
                        "Umidade do talhão " + nomeTalhao(linha, coluna) + " (%): ", 0, 100);
            }
        }
        return mapa;
    }

    public static String formatarMapa(double[][] mapa) {
        StringBuilder texto = new StringBuilder("   ");
        for (int coluna = 0; coluna < COLUNAS; coluna++) {
            texto.append(String.format("%8d", coluna + 1));
        }
        for (int linha = 0; linha < LINHAS; linha++) {
            texto.append(System.lineSeparator()).append(" ").append((char) ('A' + linha)).append(" ");
            for (int coluna = 0; coluna < COLUNAS; coluna++) {
                texto.append(String.format("%8s", Formatador.numero(mapa[linha][coluna]) + "%"));
            }
        }
        return texto.toString();
    }

    public static List<String> talhoesParaIrrigar(double[][] mapa) {
        List<String> alertas = new ArrayList<>();
        for (int linha = 0; linha < LINHAS; linha++) {
            for (int coluna = 0; coluna < COLUNAS; coluna++) {
                if (mapa[linha][coluna] < LIMITE_IRRIGACAO) {
                    alertas.add(nomeTalhao(linha, coluna) + " - " + Formatador.numero(mapa[linha][coluna]) + "%");
                }
            }
        }
        return alertas;
    }

    public static String formatarAlertas(double[][] mapa) {
        List<String> alertas = talhoesParaIrrigar(mapa);
        if (alertas.isEmpty()) {
            return "Nenhum talhão precisa de irrigação.";
        }
        return "ALERTA - talhões abaixo de 30% (necessitam irrigação):" + System.lineSeparator()
                + String.join(System.lineSeparator(), alertas);
    }

    public static void main(String[] args) {
        double[][] mapa = cadastrarTalhoes(new Scanner(System.in));
        System.out.println(formatarMapa(mapa));
        System.out.println();
        System.out.println(formatarAlertas(mapa));
    }
}
