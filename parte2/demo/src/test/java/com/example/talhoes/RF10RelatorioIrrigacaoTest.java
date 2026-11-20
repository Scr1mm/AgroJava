package com.example.talhoes;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RF10RelatorioIrrigacaoTest {

    @Test
    void listaApenasTalhoesQuePrecisamDeAcao() {
        assertEquals(Arrays.asList("A1 - Crítico", "A2 - Atenção", "B2 - Atenção", "C2 - Crítico"),
                RF10RelatorioIrrigacao.talhoesComAlerta(MapaExemplo.criar()));
    }

    @Test
    void relatorioMostraTotalDeAlertas() {
        String relatorio = RF10RelatorioIrrigacao.gerarRelatorioIrrigacao(MapaExemplo.criar());
        assertTrue(relatorio.startsWith("===== ALERTAS ====="));
        assertTrue(relatorio.contains("Total de alertas: 4"));
        assertFalse(relatorio.contains("Adequado"));
    }
}
