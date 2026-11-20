package com.example.talhoes;

public enum StatusTalhao {
    CRITICO("Crítico"),
    ATENCAO("Atenção"),
    ADEQUADO("Adequado");

    private final String descricao;

    StatusTalhao(String descricao) {
        this.descricao = descricao;
    }

    public boolean precisaDeAcao() {
        return this != ADEQUADO;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
