package com.example.chuvas;
import com.example.util.Entrada;
import com.example.util.Formatador;
import java.util.Scanner;

public final class RF01RegistroChuvas {

    public static final String[] DIAS_DA_SEMANA = {
            "segunda-feira", "terça-feira", "quarta-feira", "quinta-feira",
            "sexta-feira", "sábado", "domingo"
    };
    static final double CHUVA_MAXIMA_MM = 2000;

    private RF01RegistroChuvas() {
    }

    public static double[] cadastrarChuvas(Scanner scanner) {
        double[] chuvas = new double[DIAS_DA_SEMANA.length];
        for (int dia = 0; dia < chuvas.length; dia++) {
            chuvas[dia] = Entrada.lerDouble(scanner,
                    "Chuva de " + DIAS_DA_SEMANA[dia] + " (mm): ", 0, CHUVA_MAXIMA_MM);
        }
        return chuvas;
    }

    public static double total(double[] valores) {
        double soma = 0;
        for (double valor : valores) {
            soma += valor;
        }
        return soma;
    }

    public static double media(double[] valores) {
        return total(valores) / valores.length;
    }

    public static int indiceDoMaior(double[] valores) {
        int indice = 0;
        for (int i = 1; i < valores.length; i++) {
            if (valores[i] > valores[indice]) {
                indice = i;
            }
        }
        return indice;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] chuvas = cadastrarChuvas(scanner);
        int diaMaisChuvoso = indiceDoMaior(chuvas);

        System.out.println("Média semanal: " + Formatador.umaCasa(media(chuvas)) + " mm");
        System.out.println("Dia mais chuvoso: " + DIAS_DA_SEMANA[diaMaisChuvoso]
                + " (" + Formatador.numero(chuvas[diaMaisChuvoso]) + " mm)");
    }
}
