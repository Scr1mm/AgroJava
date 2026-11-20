package com.example.talhoes;
import com.example.util.Formatador;
import java.io.IOException;
import java.util.Scanner;
import static com.example.talhoes.RF02MapaUmidade.COLUNAS;
import static com.example.talhoes.RF02MapaUmidade.LINHAS;

public final class RF09ClassificarTalhoes {
    private RF09ClassificarTalhoes() {
    }

    public static StatusTalhao classificarTalhao(double umidade) {
        if (umidade < 20) {
            return StatusTalhao.CRITICO;
        }
        if (umidade <= 30) {
            return StatusTalhao.ATENCAO;
        }
        return StatusTalhao.ADEQUADO;
    }

    public static String gerarTabelaClassificacao(double[][] mapa) {
        StringBuilder texto = new StringBuilder("talhao,umidade,status");
        for (int linha = 0; linha < LINHAS; linha++) {
            for (int coluna = 0; coluna < COLUNAS; coluna++) {
                double umidade = mapa[linha][coluna];
                texto.append(System.lineSeparator())
                        .append(RF02MapaUmidade.nomeTalhao(linha, coluna)).append(',')
                        .append(Formatador.numero(umidade)).append(',')
                        .append(classificarTalhao(umidade));
            }
        }
        return texto.toString();
    }

    public static void main(String[] args) throws IOException {
        double[][] mapa = RF08SalvarMapaUmidade.obterMapa(new Scanner(System.in), RF08SalvarMapaUmidade.ARQUIVO_MAPA);
        System.out.println(gerarTabelaClassificacao(mapa));
    }
}
