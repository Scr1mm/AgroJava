package com.example.chuvas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RF05ConsultarHistoricoTest {

    @TempDir
    Path pasta;

    @Test
    void leTodosOsRegistrosIgnorandoCabecalho() throws Exception {
        Path arquivo = pasta.resolve("historico.csv");
        Files.write(arquivo, Arrays.asList(
                SemanaChuva.CABECALHO_CSV,
                "10/11/2026,10,12,0,5,20,15,8,10.0,70",
                "17/11/2026,1,1,1,1,1,1,1,1.0,7"));

        List<SemanaChuva> semanas = RF05ConsultarHistorico.consultarHistoricoChuvas(arquivo);

        assertEquals(2, semanas.size());
        String texto = RF05ConsultarHistorico.formatarHistorico(semanas);
        assertTrue(texto.contains("Semana: 10/11/2026"));
        assertTrue(texto.contains("Média: 10 mm"));
        assertTrue(texto.contains("Total: 70 mm"));
        assertTrue(texto.contains("Semana: 17/11/2026"));
    }

    @Test
    void arquivoInexistenteRetornaListaVazia() throws Exception {
        List<SemanaChuva> semanas = RF05ConsultarHistorico.consultarHistoricoChuvas(pasta.resolve("nao-existe.csv"));
        assertTrue(semanas.isEmpty());
        assertEquals("Nenhuma semana registrada.", RF05ConsultarHistorico.formatarHistorico(semanas));
    }
}
