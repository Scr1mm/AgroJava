package com.example.chuvas;
import com.example.util.Formatador;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class RF05ConsultarHistorico {
    static final String SEM_REGISTROS = "Nenhuma semana registrada.";

    private RF05ConsultarHistorico() {
    }

    public static List<SemanaChuva> consultarHistoricoChuvas(Path arquivo) throws IOException {
        List<SemanaChuva> semanas = new ArrayList<>();
        if (Files.notExists(arquivo)) {
            return semanas;
        }
        try (BufferedReader leitor = Files.newBufferedReader(arquivo, StandardCharsets.UTF_8)) {
            leitor.readLine();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (!linha.isBlank()) {
                    semanas.add(SemanaChuva.fromCsv(linha));
                }
            }
        }
        return semanas;
    }

    public static String formatarHistorico(List<SemanaChuva> semanas) {
        if (semanas.isEmpty()) {
            return SEM_REGISTROS;
        }
        StringBuilder texto = new StringBuilder();
        for (SemanaChuva semana : semanas) {
            texto.append("Semana: ").append(semana.getData()).append(System.lineSeparator())
                    .append("Média: ").append(Formatador.numero(semana.media())).append(" mm").append(System.lineSeparator())
                    .append("Total: ").append(Formatador.numero(semana.total())).append(" mm").append(System.lineSeparator())
                    .append(System.lineSeparator());
        }
        return texto.toString().trim();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(formatarHistorico(consultarHistoricoChuvas(RF04RegistrarSemana.ARQUIVO_HISTORICO)));
    }
}
