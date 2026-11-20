package com.example.talhoes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RF12ExportarRelatorioTest {

    @TempDir
    Path pasta;

    @Test
    void gravaRelatorioComData() throws Exception {
        Path arquivo = pasta.resolve("relatorio_fazenda.txt");

        RF12ExportarRelatorio.exportarRelatorio(MapaExemplo.criar(), LocalDate.of(2026, 11, 17), arquivo);

        List<String> linhas = Files.readAllLines(arquivo);
        assertEquals(RF11RelatorioGerencial.TITULO, linhas.get(0));
        assertEquals("Data: 17/11/2026", linhas.get(1));
        assertEquals("Media Geral: 46.0%", linhas.get(2));
        assertEquals("Talhoes Criticos: 2", linhas.get(3));
        assertEquals("Talhoes Adequados: 12", linhas.get(4));
        assertEquals(8, linhas.size());
    }
}
