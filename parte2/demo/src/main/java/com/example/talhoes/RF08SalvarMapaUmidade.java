package com.example.talhoes;
import com.example.util.Formatador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import static com.example.talhoes.RF02MapaUmidade.COLUNAS;
import static com.example.talhoes.RF02MapaUmidade.LINHAS;

public final class RF08SalvarMapaUmidade {
    public static final Path ARQUIVO_MAPA = Paths.get("dados", "mapa_umidade.csv");
    private static final String CABECALHO_CSV = "talhao,umidade";

    private RF08SalvarMapaUmidade() {
    }

    public static void salvarMapaTalhoes(double[][] mapa, Path arquivo) throws IOException {
        Files.createDirectories(arquivo.toAbsolutePath().getParent());
        try (BufferedWriter escritor = Files.newBufferedWriter(arquivo, StandardCharsets.UTF_8)) {
            escritor.write(CABECALHO_CSV);
            escritor.newLine();
            for (int linha = 0; linha < LINHAS; linha++) {
                for (int coluna = 0; coluna < COLUNAS; coluna++) {
                    escritor.write(RF02MapaUmidade.nomeTalhao(linha, coluna) + "," + Formatador.numero(mapa[linha][coluna]));
                    escritor.newLine();
                }
            }
        }
    }

    public static double[][] carregarMapaTalhoes(Path arquivo) throws IOException {
        double[][] mapa = new double[LINHAS][COLUNAS];
        int lidos = 0;
        try (BufferedReader leitor = Files.newBufferedReader(arquivo, StandardCharsets.UTF_8)) {
            leitor.readLine();
            String linhaCsv;
            while ((linhaCsv = leitor.readLine()) != null) {
                if (linhaCsv.isBlank()) {
                    continue;
                }
                String[] campos = linhaCsv.split(",");
                String nome = campos[0].trim();
                int linha = nome.charAt(0) - 'A';
                int coluna = Integer.parseInt(nome.substring(1)) - 1;
                if (linha < 0 || linha >= LINHAS || coluna < 0 || coluna >= COLUNAS) {
                    throw new IOException("Talhão inválido no arquivo: " + nome);
                }
                mapa[linha][coluna] = Double.parseDouble(campos[1].trim());
                lidos++;
            }
        }
        if (lidos != LINHAS * COLUNAS) {
            throw new IOException("Arquivo de mapa incompleto: " + lidos + " de " + LINHAS * COLUNAS + " talhões.");
        }
        return mapa;
    }

    public static double[][] obterMapa(Scanner scanner, Path arquivo) throws IOException {
        if (Files.exists(arquivo)) {
            System.out.println("Usando mapa salvo em " + arquivo.toAbsolutePath());
            return carregarMapaTalhoes(arquivo);
        }
        System.out.println("Nenhum mapa salvo. Cadastre os talhões:");
        double[][] mapa = RF02MapaUmidade.cadastrarTalhoes(scanner);
        salvarMapaTalhoes(mapa, arquivo);
        return mapa;
    }

    public static void main(String[] args) throws IOException {
        double[][] mapa = RF02MapaUmidade.cadastrarTalhoes(new Scanner(System.in));
        salvarMapaTalhoes(mapa, ARQUIVO_MAPA);
        System.out.println("16 talhões salvos em " + ARQUIVO_MAPA.toAbsolutePath());
    }
}
