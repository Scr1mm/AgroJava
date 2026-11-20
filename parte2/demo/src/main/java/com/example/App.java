package com.example;
import com.example.chuvas.RF04RegistrarSemana;
import com.example.chuvas.RF05ConsultarHistorico;
import com.example.chuvas.RF06SemanaMaisChuvosa;
import com.example.chuvas.RF07DashboardClimatico;
import com.example.chuvas.SemanaChuva;
import com.example.talhoes.RF02MapaUmidade;
import com.example.talhoes.RF08SalvarMapaUmidade;
import com.example.talhoes.RF09ClassificarTalhoes;
import com.example.talhoes.RF10RelatorioIrrigacao;
import com.example.talhoes.RF11RelatorioGerencial;
import com.example.talhoes.RF12ExportarRelatorio;
import com.example.util.Entrada;
import com.example.util.Formatador;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public final class App {
    private static final int SAIR = 0;
    private static final Path HISTORICO = RF04RegistrarSemana.ARQUIVO_HISTORICO;
    private static final Path MAPA = RF08SalvarMapaUmidade.ARQUIVO_MAPA;

    private App() {
    }

    public static void main(String[] args) {
        menuPrincipal(new Scanner(System.in));
    }

    public static void menuPrincipal(Scanner scanner) {
        int opcao;
        do {
            exibirMenu();
            opcao = Entrada.lerOpcao(scanner, "Escolha uma opção: ");
            try {
                executarOpcao(opcao, scanner);
            } catch (IOException | RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != SAIR);
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("========= AGROJAVA =========");
        System.out.println("-- Clima (AgroJavaAnalytics) --");
        System.out.println("1) Registrar semana de chuvas");
        System.out.println("2) Consultar histórico de chuvas");
        System.out.println("3) Semana mais chuvosa");
        System.out.println("4) Dashboard climático");
        System.out.println("-- Talhões (AgroJavaSmartFarm) --");
        System.out.println("5) Cadastrar talhões");
        System.out.println("6) Exibir mapa e classificação");
        System.out.println("7) Relatório de irrigação");
        System.out.println("8) Relatório gerencial");
        System.out.println("9) Exportar relatório");
        System.out.println("0) Sair");
    }

    private static void executarOpcao(int opcao, Scanner scanner) throws IOException {
        switch (opcao) {
            case 1:
                SemanaChuva semana = RF04RegistrarSemana.cadastrarSemana(scanner);
                RF04RegistrarSemana.salvarHistoricoChuvas(semana, HISTORICO);
                System.out.println("Semana registrada. Total: " + Formatador.numero(semana.total()) + " mm");
                break;
            case 2:
                System.out.println(RF05ConsultarHistorico.formatarHistorico(historico()));
                break;
            case 3:
                System.out.println(RF06SemanaMaisChuvosa.gerarRelatorio(historico()));
                break;
            case 4:
                System.out.println(RF07DashboardClimatico.gerarDashboardClimatico(historico()));
                break;
            case 5:
                RF08SalvarMapaUmidade.salvarMapaTalhoes(RF02MapaUmidade.cadastrarTalhoes(scanner), MAPA);
                System.out.println("Mapa salvo em " + MAPA.toAbsolutePath());
                break;
            case 6:
                double[][] mapa = mapaSalvo();
                System.out.println(RF02MapaUmidade.formatarMapa(mapa));
                System.out.println();
                System.out.println(RF09ClassificarTalhoes.gerarTabelaClassificacao(mapa));
                break;
            case 7:
                System.out.println(RF10RelatorioIrrigacao.gerarRelatorioIrrigacao(mapaSalvo()));
                break;
            case 8:
                System.out.println(RF11RelatorioGerencial.gerarRelatorioGerencial(mapaSalvo()));
                break;
            case 9:
                RF12ExportarRelatorio.exportarRelatorio(mapaSalvo(), LocalDate.now(), RF12ExportarRelatorio.ARQUIVO_RELATORIO);
                System.out.println("Relatório exportado para " + RF12ExportarRelatorio.ARQUIVO_RELATORIO.toAbsolutePath());
                break;
            case SAIR:
                System.out.println("Encerrando o AgroJava.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static List<SemanaChuva> historico() throws IOException {
        return RF05ConsultarHistorico.consultarHistoricoChuvas(HISTORICO);
    }

    private static double[][] mapaSalvo() throws IOException {
        if (Files.notExists(MAPA)) {
            throw new IllegalStateException("Nenhum mapa cadastrado. Use a opção 5 primeiro.");
        }
        return RF08SalvarMapaUmidade.carregarMapaTalhoes(MAPA);
    }
}
