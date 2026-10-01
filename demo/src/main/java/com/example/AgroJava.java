package com.example;
import java.util.Scanner;

public class AgroJava {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] chuvaSemanal = new double[7];
        String[] diasDaSemana = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
        double[][] umidadeTalhoes = new double[4][4];
        boolean dadosCadastrados = false;
        int opcao;

        do {
            System.out.println("\n=========================");
            System.out.println("   SISTEMA AGROJAVA    ");
            System.out.println("============================");
            System.out.println(" 1) Cadastrar Dados ");
            System.out.println(" 2) Exibir Mapa do Campo ");
            System.out.println(" 3) Relatório de Alertas");
            System.out.println(" 4) Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- CADASTRO DE DADOS ---");
                    
                  
                    System.out.println("\n[1/2] Digite o volume de chuva (em mm) para os 7 dias da semana:");
                    for (int i = 0; i < 7; i++) {
                        System.out.print(diasDaSemana[i] + ": ");
                        chuvaSemanal[i] = scanner.nextDouble();
                    }

                    System.out.println("\n[2/2] Digite a umidade (%) dos talhões (Matriz 4x4):");
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            System.out.print("Talhão [Linha " + (i + 1) + ", Coluna " + (j + 1) + "]: ");
                            umidadeTalhoes[i][j] = scanner.nextDouble();
                        }
                    }

                    dadosCadastrados = true;
                    System.out.println("\nDados cadastrados com sucesso!");
                    break;

                case 2:
                    if (!dadosCadastrados) {
                        System.out.println("\n[Aviso] Você precisa cadastrar os dados primeiro (Opção 1).");
                        break;
                    }

                    System.out.println("\n--- MAPA DE UMIDADE DO CAMPO (%) ---");
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            System.out.printf("[%.1f%%] ", umidadeTalhoes[i][j]);
                        }
                        System.out.println();
                    }
                    break;

                case 3:
                    if (!dadosCadastrados) {
                        System.out.println("\n[Aviso] Você precisa cadastrar os dados primeiro (Opção 1).");
                        break;
                    }

                    System.out.println("\n--- RELATÓRIO DE CONSOLIDAÇÃO E ALERTAS ---");

                  
                    double somaChuvas = 0;
                    double maiorChuva = chuvaSemanal[0];
                    int diaMaiorChuva = 0;

                    for (int i = 0; i < 7; i++) {
                        somaChuvas += chuvaSemanal[i];
                        if (chuvaSemanal[i] > maiorChuva) {
                            maiorChuva = chuvaSemanal[i];
                            diaMaiorChuva = i;
                        }
                    }
                    double mediaChuvas = somaChuvas / 7;

                    System.out.printf(" Média semanal de pluviosidade: %.2f mm\n", mediaChuvas);
                    System.out.println(" Dia com maior índice de chuva: " + diasDaSemana[diaMaiorChuva] + " (" + maiorChuva + " mm)");
                    System.out.println("\n Alerta de Irrigação (Umidade abaixo de 30%):");
                    boolean alertaEncontrado = false;

                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (umidadeTalhoes[i][j] < 30.0) {
                                System.out.printf(" -> Talhão da Linha %d, Coluna %d está com %.1f%% de umidade!\n", 
                                    (i + 1), (j + 1), umidadeTalhoes[i][j]);
                                alertaEncontrado = true;
                            }
                        }
                    }

                    if (!alertaEncontrado) {
                        System.out.println("Nenhum talhão necessita de irrigação no momento. Todos com umidade adequada.");
                    }
                    break;

                case 4:
                    System.out.println("\nEncerrando o sistema AgroJava. Até logo!");
                    break;

                default:
                    System.out.println("\n[Erro] Opção inválida! Escolha um número entre 1 e 4.");
            }

        } while (opcao != 4);

        scanner.close();
    }
}
   