package com.example.chuvas;
import com.example.util.Formatador;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public final class RF06SemanaMaisChuvosa {

    private RF06SemanaMaisChuvosa() {
    }

    public static Optional<SemanaChuva> semanaMaisChuvosa(List<SemanaChuva> semanas) {
        return semanas.stream().max(Comparator.comparingDouble(SemanaChuva::total));
    }

    public static String gerarRelatorio(List<SemanaChuva> semanas) {
        return semanaMaisChuvosa(semanas)
                .map(semana -> String.join(System.lineSeparator(),
                        "Semana mais chuvosa:", semana.getData(),
                        "Total:", Formatador.numero(semana.total()) + " mm"))
                .orElse(RF05ConsultarHistorico.SEM_REGISTROS);
    }

    public static void main(String[] args) throws IOException {
        List<SemanaChuva> semanas = RF05ConsultarHistorico.consultarHistoricoChuvas(RF04RegistrarSemana.ARQUIVO_HISTORICO);
        System.out.println(gerarRelatorio(semanas));
    }
}
