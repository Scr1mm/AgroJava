package com.example.chuvas;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RF06SemanaMaisChuvosaTest {

    @Test
    void encontraSemanaComMaiorTotal() {
        List<SemanaChuva> semanas = Arrays.asList(
                new SemanaChuva("10/11/2026", new double[]{10, 10, 10, 10, 10, 10, 10}),
                new SemanaChuva("24/11/2026", new double[]{20, 20, 20, 20, 20, 20, 0}),
                new SemanaChuva("17/11/2026", new double[]{5, 5, 5, 5, 5, 5, 5}));

        assertEquals("24/11/2026", RF06SemanaMaisChuvosa.semanaMaisChuvosa(semanas).orElseThrow().getData());
        String relatorio = RF06SemanaMaisChuvosa.gerarRelatorio(semanas);
        assertTrue(relatorio.contains("24/11/2026"));
        assertTrue(relatorio.contains("120 mm"));
        assertFalse(relatorio.contains("10/11/2026"));
    }

    @Test
    void semRegistros() {
        assertFalse(RF06SemanaMaisChuvosa.semanaMaisChuvosa(Collections.emptyList()).isPresent());
    }
}
