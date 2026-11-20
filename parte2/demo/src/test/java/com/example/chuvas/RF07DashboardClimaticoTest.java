package com.example.chuvas;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RF07DashboardClimaticoTest {

    @Test
    void calculaIndicadores() {
        List<SemanaChuva> semanas = Arrays.asList(
                new SemanaChuva("10/11/2026", new double[]{10, 10, 10, 10, 10, 10, 10}),
                new SemanaChuva("17/11/2026", new double[]{30, 0, 0, 0, 0, 0, 0}),
                new SemanaChuva("24/11/2026", new double[]{20, 20, 20, 20, 20, 20, 0}));

        String dashboard = RF07DashboardClimatico.gerarDashboardClimatico(semanas);

        assertTrue(dashboard.contains("Semanas cadastradas: 3"));
        assertTrue(dashboard.contains("Volume acumulado: 220 mm"));
        assertTrue(dashboard.contains("120 mm em 24/11/2026"));
        assertTrue(dashboard.contains("30 mm em 17/11/2026"));
        assertTrue(dashboard.contains("Média geral: 73.3 mm"));
    }

    @Test
    void semRegistros() {
        assertEquals("Nenhuma semana registrada.", RF07DashboardClimatico.gerarDashboardClimatico(Collections.emptyList()));
    }
}
