package com.example.talhoes;
import com.example.util.Entrada;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class RF12ExportarRelatorio {
    public static final Path ARQUIVO_RELATORIO = Paths.get("dados", "relatorio_fazenda.txt");

    private RF12ExportarRelatorio() {
    }

    public static void exportarRelatorio(double[][] mapa, LocalDate data, Path arquivo) throws IOException {
        List<String> linhas = new ArrayList<>();
        linhas.add(RF11RelatorioGerencial.TITULO);
        linhas.add("Data: " + data.format(Entrada.FORMATO_DATA));
        linhas.addAll(RF11RelatorioGerencial.calcularIndicadores(mapa));

        Files.createDirectories(arquivo.toAbsolutePath().getParent());
        try (BufferedWriter escritor = Files.newBufferedWriter(arquivo, StandardCharsets.UTF_8)) {
            for (String linha : linhas) {
                escritor.write(linha);
                escritor.newLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        double[][] mapa = RF08SalvarMapaUmidade.obterMapa(new Scanner(System.in), RF08SalvarMapaUmidade.ARQUIVO_MAPA);
        exportarRelatorio(mapa, LocalDate.now(), ARQUIVO_RELATORIO);
        System.out.println("Relatório exportado para " + ARQUIVO_RELATORIO.toAbsolutePath());
    }
}
