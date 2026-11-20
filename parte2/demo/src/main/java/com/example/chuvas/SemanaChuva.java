package com.example.chuvas;
import com.example.util.Formatador;

public final class SemanaChuva {
    public static final String CABECALHO_CSV = "data,seg,ter,qua,qui,sex,sab,dom,media,total";
    private static final int DIAS = 7;

    private final String data;
    private final double[] chuvas;

    public SemanaChuva(String data, double[] chuvas) {
        if (chuvas.length != DIAS) {
            throw new IllegalArgumentException("A semana deve ter " + DIAS + " dias de chuva.");
        }
        this.data = data;
        this.chuvas = chuvas.clone();
    }

    public String getData() {
        return data;
    }

    public double media() {
        return RF01RegistroChuvas.media(chuvas);
    }

    public double total() {
        return RF01RegistroChuvas.total(chuvas);
    }

    public String toCsv() {
        StringBuilder linha = new StringBuilder(data);
        for (double chuva : chuvas) {
            linha.append(',').append(Formatador.numero(chuva));
        }
        linha.append(',').append(Formatador.umaCasa(media()));
        linha.append(',').append(Formatador.numero(total()));
        return linha.toString();
    }

    public static SemanaChuva fromCsv(String linha) {
        String[] campos = linha.split(",");
        if (campos.length < 1 + DIAS) {
            throw new IllegalArgumentException("Linha de chuva inválida: " + linha);
        }
        double[] chuvas = new double[DIAS];
        for (int dia = 0; dia < DIAS; dia++) {
            chuvas[dia] = Double.parseDouble(campos[dia + 1].trim());
        }
        return new SemanaChuva(campos[0].trim(), chuvas);
    }
}
