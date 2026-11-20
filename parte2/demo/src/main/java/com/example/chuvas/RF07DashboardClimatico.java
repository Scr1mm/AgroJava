package com.example.chuvas;
import com.example.util.Formatador;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public final class RF07DashboardClimatico {

    private RF07DashboardClimatico() {
    }

    public static String gerarDashboardClimatico(List<SemanaChuva> semanas) {
        if (semanas.isEmpty()) {
            return RF05ConsultarHistorico.SEM_REGISTROS;
        }
        double acumulado = semanas.stream().mapToDouble(SemanaChuva::total).sum();
        SemanaChuva maisChuvosa = RF06SemanaMaisChuvosa.semanaMaisChuvosa(semanas).orElseThrow();
        SemanaChuva maisSeca = semanas.stream().min(Comparator.comparingDouble(SemanaChuva::total)).orElseThrow();

        return String.join(System.lineSeparator(),
                "===== DASHBOARD =====",
                "Semanas cadastradas: " + semanas.size(),
                "Volume acumulado: " + Formatador.numero(acumulado) + " mm",
                "Maior chuva (semana mais chuvosa): " + descrever(maisChuvosa),
                "Menor chuva (semana mais seca): " + descrever(maisSeca),
                "Média geral: " + Formatador.umaCasa(acumulado / semanas.size()) + " mm");
    }

    private static String descrever(SemanaChuva semana) {
        return Formatador.numero(semana.total()) + " mm em " + semana.getData();
    }

    public static void main(String[] args) throws IOException {
        List<SemanaChuva> semanas = RF05ConsultarHistorico.consultarHistoricoChuvas(RF04RegistrarSemana.ARQUIVO_HISTORICO);
        System.out.println(gerarDashboardClimatico(semanas));
    }
}
