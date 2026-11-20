package com.example.talhoes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RF08SalvarMapaUmidadeTest {

    @TempDir
    Path pasta;

    @Test
    void exportaOs16TalhoesCriandoOArquivo() throws Exception {
        Path arquivo = pasta.resolve("nova-pasta/mapa.csv");

        RF08SalvarMapaUmidade.salvarMapaTalhoes(MapaExemplo.criar(), arquivo);

        List<String> linhas = Files.readAllLines(arquivo);
        assertEquals(17, linhas.size());
        assertEquals("talhao,umidade", linhas.get(0));
        assertEquals("A1,18", linhas.get(1));
        assertEquals("D4,85", linhas.get(16));
    }

    @Test
    void carregaOMapaSalvo() throws Exception {
        Path arquivo = pasta.resolve("mapa.csv");
        RF08SalvarMapaUmidade.salvarMapaTalhoes(MapaExemplo.criar(), arquivo);

        double[][] mapa = RF08SalvarMapaUmidade.carregarMapaTalhoes(arquivo);

        assertEquals(14.0, mapa[2][1], 0.001);
        assertEquals(85.0, mapa[3][3], 0.001);
    }

    @Test
    void rejeitaArquivoIncompleto() throws Exception {
        Path arquivo = pasta.resolve("mapa.csv");
        Files.write(arquivo, Arrays.asList("talhao,umidade", "A1,45"));
        assertThrows(IOException.class, () -> RF08SalvarMapaUmidade.carregarMapaTalhoes(arquivo));
    }
}
