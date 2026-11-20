package com.example.talhoes;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RF02MapaUmidadeTest {

    @Test
    void nomeiaTalhoesPorLinhaEColuna() {
        assertEquals("A1", RF02MapaUmidade.nomeTalhao(0, 0));
        assertEquals("C2", RF02MapaUmidade.nomeTalhao(2, 1));
        assertEquals("D4", RF02MapaUmidade.nomeTalhao(3, 3));
    }

    @Test
    void alertaTalhoesAbaixoDe30() {
        assertEquals(Arrays.asList("A1 - 18%", "A2 - 25%", "B2 - 22%", "C2 - 14%"),
                RF02MapaUmidade.talhoesParaIrrigar(MapaExemplo.criar()));
    }

    @Test
    void formataMapaComTodasAsLinhas() {
        String mapa = RF02MapaUmidade.formatarMapa(MapaExemplo.criar());
        assertTrue(mapa.contains(" A "));
        assertTrue(mapa.contains(" D "));
        assertTrue(mapa.contains("85%"));
    }

    @Test
    void cadastraOs16TalhoesERejeitaUmidadeAcimaDe100() {
        StringBuilder entrada = new StringBuilder("150\n");
        for (int i = 1; i <= 16; i++) {
            entrada.append(i * 5).append('\n');
        }
        double[][] mapa = RF02MapaUmidade.cadastrarTalhoes(new Scanner(entrada.toString()));
        assertEquals(5.0, mapa[0][0], 0.001);
        assertEquals(80.0, mapa[3][3], 0.001);
    }
}
