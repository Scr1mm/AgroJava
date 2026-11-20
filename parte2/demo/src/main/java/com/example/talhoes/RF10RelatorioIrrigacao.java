package com.example.talhoes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static com.example.talhoes.RF02MapaUmidade.COLUNAS;
import static com.example.talhoes.RF02MapaUmidade.LINHAS;

public final class RF10RelatorioIrrigacao {

    private RF10RelatorioIrrigacao() {
    }

    public static List<String> talhoesComAlerta(double[][] mapa) {
        List<String> alertas = new ArrayList<>();
        for (int linha = 0; linha < LINHAS; linha++) {
            for (int coluna = 0; coluna < COLUNAS; coluna++) {
                StatusTalhao status = RF09ClassificarTalhoes.classificarTalhao(mapa[linha][coluna]);
                if (status.precisaDeAcao()) {
                    alertas.add(RF02MapaUmidade.nomeTalhao(linha, coluna) + " - " + status);
                }
            }
        }
        return alertas;
    }

    public static String gerarRelatorioIrrigacao(double[][] mapa) {
        List<String> linhas = new ArrayList<>();
        linhas.add("===== ALERTAS =====");
        List<String> alertas = talhoesComAlerta(mapa);
        linhas.addAll(alertas);
        linhas.add("Total de alertas: " + alertas.size());
        return String.join(System.lineSeparator(), linhas);
    }

    public static void main(String[] args) throws IOException {
        double[][] mapa = RF08SalvarMapaUmidade.obterMapa(new Scanner(System.in), RF08SalvarMapaUmidade.ARQUIVO_MAPA);
        System.out.println(gerarRelatorioIrrigacao(mapa));
    }
}
