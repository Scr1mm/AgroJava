package com.example.util;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public final class Formatador {
    private Formatador() {
    }

    public static String numero(double valor) {
        return BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    public static String umaCasa(double valor) {
        return String.format(Locale.US, "%.1f", valor);
    }
}
