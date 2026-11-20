package com.example.chuvas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RF04RegistrarSemanaTest {

    @TempDir
    Path pasta;

    @Test
    void acrescentaRegistrosSemSobrescrever() throws Exception {
        Path arquivo = pasta.resolve("sub/historico.csv");

        RF04RegistrarSemana.salvarHistoricoChuvas(new SemanaChuva("10/11/2026", new double[]{10, 12, 0, 5, 20, 15, 8}), arquivo);
        RF04RegistrarSemana.salvarHistoricoChuvas(new SemanaChuva("17/11/2026", new double[]{1, 1, 1, 1, 1, 1, 1}), arquivo);

        List<String> linhas = Files.readAllLines(arquivo);
        assertEquals(3, linhas.size());
        assertEquals(SemanaChuva.CABECALHO_CSV, linhas.get(0));
        assertEquals("10/11/2026,10,12,0,5,20,15,8,10.0,70", linhas.get(1));
        assertEquals("17/11/2026,1,1,1,1,1,1,1,1.0,7", linhas.get(2));
    }

    @Test
    void cadastroValidaAData() {
        Scanner entrada = new Scanner("32/13/2026\n10/11/2026\n10\n12\n0\n5\n20\n15\n8\n");
        SemanaChuva semana = RF04RegistrarSemana.cadastrarSemana(entrada);
        assertEquals("10/11/2026", semana.getData());
        assertEquals(70.0, semana.total(), 0.001);
    }
}
