package com.example.talhoes;
import com.example.util.Formatador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static com.example.talhoes.RF02MapaUmidade.COLUNAS;
import static com.example.talhoes.RF02MapaUmidade.LINHAS;

public final class RF11RelatorioGerencial {

    public static final String TITULO = "------ RELATORIO DA FAZENDA --------";

    private RF11RelatorioGerencial() {
    }

    public static List<String> calcularIndicadores(double[][] mapa) {
        double soma = 0;
        int criticos = 0;
        int adequados = 0;
        int precisamIrrigacao = 0;
        int[] maisSeco = {0, 0};
        int[] maisUmido = {0, 0};

        for (int linha = 0; linha < LINHAS; linha++) {
            for (int coluna = 0; coluna < COLUNAS; coluna++) {
                double umidade = mapa[linha][coluna];
                StatusTalhao status = RF09ClassificarTalhoes.classificarTalhao(umidade);
                soma += umidade;
                if (status == StatusTalhao.CRITICO) {
                    criticos++;
                }
                if (status == StatusTalhao.ADEQUADO) {
                    adequados++;
                }
                if (status.precisaDeAcao()) {
                    precisamIrrigacao++;
                }
                if (umidade < mapa[maisSeco[0]][maisSeco[1]]) {
                    maisSeco = new int[]{linha, coluna};
                }
                if (umidade > mapa[maisUmido[0]][maisUmido[1]]) {
                    maisUmido = new int[]{linha, coluna};
                }
            }
        }
        int totalTalhoes = LINHAS * COLUNAS;

        return Arrays.asList(
                "Media Geral: " + Formatador.umaCasa(soma / totalTalhoes) + "%",
                "Talhoes Criticos: " + criticos,
                "Talhoes Adequados: " + adequados,
                "Necessidade de Irrigacao: " + Formatador.umaCasa(precisamIrrigacao * 100.0 / totalTalhoes) + "%",
                "Talhao mais seco: " + descrever(mapa, maisSeco),
                "Talhao mais umido: " + descrever(mapa, maisUmido));
    }

    private static String descrever(double[][] mapa, int[] posicao) {
        return RF02MapaUmidade.nomeTalhao(posicao[0], posicao[1]) + " - "
                + Formatador.numero(mapa[posicao[0]][posicao[1]]) + "%";
    }

    public static String gerarRelatorioGerencial(double[][] mapa) {
        List<String> linhas = new ArrayList<>();
        linhas.add(TITULO);
        linhas.addAll(calcularIndicadores(mapa));
        return String.join(System.lineSeparator(), linhas);
    }

    public static void main(String[] args) throws IOException {
        double[][] mapa = RF08SalvarMapaUmidade.obterMapa(new Scanner(System.in), RF08SalvarMapaUmidade.ARQUIVO_MAPA);
        System.out.println(gerarRelatorioGerencial(mapa));
    }
}
