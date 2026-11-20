package com.example.chuvas;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RF01RegistroChuvasTest {

    private final double[] chuvas = {10, 12, 0, 5, 20, 15, 8};

    @Test
    void calculaTotalEMedia() {
        assertEquals(70.0, RF01RegistroChuvas.total(chuvas), 0.001);
        assertEquals(10.0, RF01RegistroChuvas.media(chuvas), 0.001);
    }

    @Test
    void encontraDiaMaisChuvoso() {
        assertEquals(4, RF01RegistroChuvas.indiceDoMaior(chuvas));
        assertEquals("sexta-feira", RF01RegistroChuvas.DIAS_DA_SEMANA[4]);
    }

    @Test
    void cadastroIgnoraValoresInvalidosEAceitaVirgula() {
        Scanner entrada = new Scanner("abc\n-5\n10\n12,5\n0\n5\n20\n15\n8\n");
        double[] lidas = RF01RegistroChuvas.cadastrarChuvas(entrada);
        assertEquals(10.0, lidas[0], 0.001);
        assertEquals(12.5, lidas[1], 0.001);
        assertEquals(8.0, lidas[6], 0.001);
    }
}
