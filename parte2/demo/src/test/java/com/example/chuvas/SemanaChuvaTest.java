package com.example.chuvas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SemanaChuvaTest {

    @Test
    void geraLinhaCsvIgualAoExemploDoEnunciado() {
        SemanaChuva semana = new SemanaChuva("10/11/2026", new double[]{10, 12, 0, 5, 20, 15, 8});
        assertEquals("10/11/2026,10,12,0,5,20,15,8,10.0,70", semana.toCsv());
    }

    @Test
    void leLinhaCsv() {
        SemanaChuva semana = SemanaChuva.fromCsv("10/11/2026,10,12,0,5,20,15,8,10.0,70");
        assertEquals("10/11/2026", semana.getData());
        assertEquals(70.0, semana.total(), 0.001);
    }

    @Test
    void rejeitaSemanaSemSeteDias() {
        assertThrows(IllegalArgumentException.class, () -> new SemanaChuva("10/11/2026", new double[]{1, 2}));
    }
}
