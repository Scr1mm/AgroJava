package com.example.talhoes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RF09ClassificarTalhoesTest {

    @Test
    void classificaNosLimitesDasFaixas() {
        assertEquals(StatusTalhao.CRITICO, RF09ClassificarTalhoes.classificarTalhao(19.9));
        assertEquals(StatusTalhao.ATENCAO, RF09ClassificarTalhoes.classificarTalhao(20));
        assertEquals(StatusTalhao.ATENCAO, RF09ClassificarTalhoes.classificarTalhao(30));
        assertEquals(StatusTalhao.ADEQUADO, RF09ClassificarTalhoes.classificarTalhao(30.1));
    }

    @Test
    void todoTalhaoRecebeStatus() {
        String[] linhas = RF09ClassificarTalhoes.gerarTabelaClassificacao(MapaExemplo.criar()).split("\\R");

        assertEquals(17, linhas.length);
        assertEquals("talhao,umidade,status", linhas[0]);
        assertEquals("A1,18,Crítico", linhas[1]);
        assertEquals("A2,25,Atenção", linhas[2]);
        assertEquals("A3,40,Adequado", linhas[3]);
        for (int i = 1; i < linhas.length; i++) {
            assertEquals(3, linhas[i].split(",").length);
        }
    }
}
