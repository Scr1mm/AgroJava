package com.example.talhoes;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RF03MenuSafraTest {

    @Test
    void fluxoCompletoDoMenu() {
        StringBuilder entrada = new StringBuilder("2\n9\n1\n");
        for (int i = 0; i < 16; i++) {
            entrada.append(i == 5 ? "10" : "50").append('\n');
        }
        entrada.append("2\n3\n4\n");

        String saida = executarCapturandoSaida(entrada.toString());

        assertTrue(saida.contains("Nenhum dado cadastrado"));
        assertTrue(saida.contains("Opção inválida."));
        assertTrue(saida.contains("Dados cadastrados com sucesso."));
        assertTrue(saida.contains("B2 - 10%"));
        assertTrue(saida.contains("Encerrando o sistema."));
    }

    private static String executarCapturandoSaida(String entrada) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer, true));
        try {
            RF03MenuSafra.executar(new Scanner(entrada));
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
    }
}
