package com.example.chuvas;
import com.example.util.Entrada;
import com.example.util.Formatador;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public final class RF04RegistrarSemana {

    public static final Path ARQUIVO_HISTORICO = Paths.get("dados", "historico_chuvas.csv");

    private RF04RegistrarSemana() {
    }

    public static SemanaChuva cadastrarSemana(Scanner scanner) {
        String data = Entrada.lerData(scanner, "Data da semana (dd/MM/aaaa): ");
        return new SemanaChuva(data, RF01RegistroChuvas.cadastrarChuvas(scanner));
    }

    public static void salvarHistoricoChuvas(SemanaChuva semana, Path arquivo) throws IOException {
        Files.createDirectories(arquivo.toAbsolutePath().getParent());
        boolean arquivoNovo = Files.notExists(arquivo) || Files.size(arquivo) == 0;

        try (BufferedWriter escritor = Files.newBufferedWriter(arquivo, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            if (arquivoNovo) {
                escritor.write(SemanaChuva.CABECALHO_CSV);
                escritor.newLine();
            }
            escritor.write(semana.toCsv());
            escritor.newLine();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        SemanaChuva semana = cadastrarSemana(scanner);
        salvarHistoricoChuvas(semana, ARQUIVO_HISTORICO);

        System.out.println("Média semanal: " + Formatador.umaCasa(semana.media()) + " mm");
        System.out.println("Total de precipitação: " + Formatador.numero(semana.total()) + " mm");
        System.out.println("Registro salvo em " + ARQUIVO_HISTORICO.toAbsolutePath());
    }
}
