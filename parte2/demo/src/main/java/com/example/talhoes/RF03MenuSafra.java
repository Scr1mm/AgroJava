package com.example.talhoes;
import com.example.util.Entrada;
import java.util.Scanner;

public final class RF03MenuSafra {
    private static final int SAIR = 4;

    private RF03MenuSafra() {
    }

    public static void executar(Scanner scanner) {
        double[][] mapa = null;
        int opcao;
        do {
            System.out.println();
            System.out.println("===== AGROJAVA - SAFRA =====");
            System.out.println("1) Cadastrar Dados");
            System.out.println("2) Exibir Mapa do Campo");
            System.out.println("3) Relatório de Alertas de Irrigação");
            System.out.println("4) Sair");
            opcao = Entrada.lerOpcao(scanner, "Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    mapa = RF02MapaUmidade.cadastrarTalhoes(scanner);
                    System.out.println("Dados cadastrados com sucesso.");
                    break;
                case 2:
                    System.out.println(mapa == null ? semDados() : RF02MapaUmidade.formatarMapa(mapa));
                    break;
                case 3:
                    System.out.println(mapa == null ? semDados() : RF02MapaUmidade.formatarAlertas(mapa));
                    break;
                case SAIR:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != SAIR);
    }

    private static String semDados() {
        return "Nenhum dado cadastrado. Use a opção 1 primeiro.";
    }

    public static void main(String[] args) {
        executar(new Scanner(System.in));
    }
}
