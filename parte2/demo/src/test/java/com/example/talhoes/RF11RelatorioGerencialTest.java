package com.example.talhoes;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RF11RelatorioGerencialTest {

    @Test
    void calculaTodosOsIndicadores() {
        assertEquals(Arrays.asList(
                        "Media Geral: 46.0%",
                        "Talhoes Criticos: 2",
                        "Talhoes Adequados: 12",
                        "Necessidade de Irrigacao: 25.0%",
                        "Talhao mais seco: C2 - 14%",
                        "Talhao mais umido: D3 - 90%"),
                RF11RelatorioGerencial.calcularIndicadores(MapaExemplo.criar()));
    }

    @Test
    void recalculaQuandoOMapaMuda() {
        double[][] mapa = MapaExemplo.criar();
        mapa[0][0] = 50;
        String relatorio = RF11RelatorioGerencial.gerarRelatorioGerencial(mapa);
        assertTrue(relatorio.startsWith(RF11RelatorioGerencial.TITULO));
        assertTrue(relatorio.contains("Talhoes Criticos: 1"));
    }
}
