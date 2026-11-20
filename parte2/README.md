# AgroJava — Sistema Integrado do Agronegócio

![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-projeto-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![VS Code](https://img.shields.io/badge/VS%20Code-editor-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Status](https://img.shields.io/badge/status-concluído-brightgreen?style=for-the-badge)
![Requisitos](https://img.shields.io/badge/requisitos-12-blue?style=for-the-badge)
![Testes](https://img.shields.io/badge/testes-29-blueviolet?style=for-the-badge)

Projeto desenvolvido em Java para a Unidade Curricular **Lógica de Programação e Algoritmos** (SENAI-SP).
Um sistema de console que controla a **produção agrícola** e monitora os **talhões** de uma propriedade rural,
praticando a manipulação de dados em **arrays**, **matrizes** e **arquivos CSV**.

> ⬅️ Esta é a **Parte 2**. A **Parte 1** (versão inicial, com o RF01 ao RF03 em um único arquivo) está na
> [raiz do repositório](../README.md).

---

## Sobre o projeto

O sistema começa como uma aplicação temporária (dados só em memória) e evolui em duas etapas:

- **Marcos (RF01 a RF03):** registro de chuvas em um vetor, mapa de umidade do solo em uma matriz 4x4 e um menu de safra;
- **Evolução 1 — AgroJavaAnalytics (RF04 a RF07):** histórico de chuvas salvo em CSV, consulta, semana mais chuvosa e dashboard climático;
- **Evolução 2 — AgroJavaSmartFarm (RF08 a RF12):** mapa de umidade salvo em CSV, classificação dos talhões, alertas de irrigação, relatório gerencial e exportação.

Cada requisito está em **uma classe própria, com seu próprio `main` e seu próprio teste**, então pode ser executado e
testado de forma individual. O `App` junta tudo em um menu principal.

O projeto aplica os seguintes conceitos:

- Variáveis, tipos de dados e operadores;
- Entrada de dados com `Scanner`, com validação;
- Estruturas de decisão (`if/else` e `switch`);
- Estruturas de repetição (`for`, `while` e `do-while`);
- Vetores (`double[7]`) e matrizes (`double[4][4]`);
- Funções (métodos `static`) e modularização;
- Leitura e escrita de arquivos (`BufferedReader` e `BufferedWriter`);
- Manipulação de Strings (`split`, `trim`, `replace`, `String.join`);
- Tratamento de exceções (`try/catch`);
- Estatística básica (soma, média, maior e menor valor, percentual);
- Testes automatizados com **JUnit 5**.

---

## Requisitos implementados

| ID | Requisito | Etapa | Onde está no código | Menu |
|:--:|-----------|-------|---------------------|:----:|
| RF01 | Registro de Chuvas (vetor) | Marcos | `RF01RegistroChuvas.cadastrarChuvas()` / `media()` / `indiceDoMaior()` | 1 |
| RF02 | Mapeamento de Umidade do Solo (matriz) | Marcos | `RF02MapaUmidade.cadastrarTalhoes()` / `formatarMapa()` / `formatarAlertas()` | 5, 6 |
| RF03 | Relatório de Consolidação de Safra | Marcos | `RF03MenuSafra.executar()` | próprio |
| RF04 | Registrar Semana Agrícola | Evolução 1 | `RF04RegistrarSemana.salvarHistoricoChuvas()` | 1 |
| RF05 | Consultar Histórico de Chuvas | Evolução 1 | `RF05ConsultarHistorico.consultarHistoricoChuvas()` | 2 |
| RF06 | Identificar Semana Mais Chuvosa | Evolução 1 | `RF06SemanaMaisChuvosa.semanaMaisChuvosa()` | 3 |
| RF07 | Dashboard Climático | Evolução 1 | `RF07DashboardClimatico.gerarDashboardClimatico()` | 4 |
| RF08 | Salvar Mapa de Umidade | Evolução 2 | `RF08SalvarMapaUmidade.salvarMapaTalhoes()` | 5 |
| RF09 | Classificar Talhões | Evolução 2 | `RF09ClassificarTalhoes.classificarTalhao()` | 6 |
| RF10 | Gerar Relatório de Irrigação | Evolução 2 | `RF10RelatorioIrrigacao.gerarRelatorioIrrigacao()` | 7 |
| RF11 | Relatório Gerencial da Fazenda | Evolução 2 | `RF11RelatorioGerencial.gerarRelatorioGerencial()` | 8 |
| RF12 | Exportação dos Relatórios | Evolução 2 | `RF12ExportarRelatorio.exportarRelatorio()` | 9 |

> O RF03 tem um menu próprio (1 a 4), que é aberto rodando a classe `RF03MenuSafra`.

### Decisões tomadas onde o enunciado era ambíguo

- **Necessidade de irrigação (RF11):** conta os talhões **Crítico + Atenção**, a mesma regra do relatório de alertas (RF10).
  O exemplo do enunciado (18% com 3 críticos) parece contar só os críticos; para isso, basta mudar uma linha em `RF11RelatorioGerencial`.
- **Umidade de exatamente 30%:** é **Atenção** no RF09 (faixa 20–30), mas não entra no alerta do RF02, que pede "abaixo de 30%".
  Cada requisito segue o próprio texto.

---

## Funcionalidades

- **Cadastro das chuvas** dos 7 dias da semana, com média semanal e dia mais chuvoso;
- **Histórico de chuvas em CSV**, sempre acrescentado ao final do arquivo (nunca sobrescreve semanas anteriores);
- **Consulta do histórico**, que continua funcionando depois de reiniciar o programa;
- **Semana mais chuvosa** e **dashboard climático** com semanas cadastradas, volume acumulado, maior chuva, menor chuva e média geral;
- **Mapa do campo** em uma matriz 4x4 (linhas A–D, colunas 1–4), exibido formatado na tela;
- **Mapa de umidade salvo em CSV**, com os 16 talhões e a pasta criada automaticamente;
- **Classificação automática** de cada talhão em Crítico, Atenção ou Adequado;
- **Relatório de irrigação** com os talhões que precisam de ação e o total de alertas;
- **Relatório gerencial** com média de umidade, totais por status, percentual de irrigação, talhão mais seco e mais úmido;
- **Exportação do relatório** para `relatorio_fazenda.txt`, com a data do dia;
- **Validação das entradas:** aceita vírgula ou ponto decimal e repete a pergunta quando o valor ou a data são inválidos.

---

## Menu

```
========= AGROJAVA =========
-- Clima (AgroJavaAnalytics) --
1) Registrar semana de chuvas
2) Consultar histórico de chuvas
3) Semana mais chuvosa
4) Dashboard climático
-- Talhões (AgroJavaSmartFarm) --
5) Cadastrar talhões
6) Exibir mapa e classificação
7) Relatório de irrigação
8) Relatório gerencial
9) Exportar relatório
0) Sair
```

Menu do RF03 (classe `RF03MenuSafra`):

```
===== AGROJAVA - SAFRA =====
1) Cadastrar Dados
2) Exibir Mapa do Campo
3) Relatório de Alertas de Irrigação
4) Sair
```

### Exemplo de saída

```
------ RELATORIO DA FAZENDA --------
Media Geral: 46.0%
Talhoes Criticos: 2
Talhoes Adequados: 12
Necessidade de Irrigacao: 25.0%
Talhao mais seco: C2 - 14%
Talhao mais umido: D3 - 90%
```

---

## Arquivos de dados

Os arquivos são **criados automaticamente** pelo sistema na pasta `dados/`, dentro da pasta onde o programa é executado.

| Arquivo | Colunas | Criado por | Usado em |
|---------|---------|------------|----------|
| `historico_chuvas.csv` | data, seg, ter, qua, qui, sex, sab, dom, media, total | RF04 (modo append) | RF05, RF06, RF07 |
| `mapa_umidade.csv` | talhao, umidade | RF08 | RF09, RF10, RF11, RF12 |
| `relatorio_fazenda.txt` | relatório gerencial com a data | RF12 | — |

Exemplo de linha do histórico:

```
data,seg,ter,qua,qui,sex,sab,dom,media,total
10/11/2026,10,12,0,5,20,15,8,10.0,70
```

> As classes RF09 a RF12, quando rodadas sozinhas, usam o mapa salvo pelo RF08. Se ele ainda não existir, pedem o cadastro dos 16 talhões e o salvam.

### Valores de referência adotados

| Item | Regra |
|------|-------|
| Chuva diária | de 0 a 2000 mm |
| Umidade do talhão | de 0 a 100% |
| Data da semana | formato `dd/MM/aaaa`, validada |
| Alerta de irrigação (RF02) | umidade abaixo de 30% |
| Crítico (RF09) | umidade abaixo de 20% |
| Atenção (RF09) | umidade de 20% a 30% |
| Adequado (RF09) | umidade acima de 30% |
| Nome do talhão | letra da linha + número da coluna (`A1` a `D4`) |
| Média geral do dashboard | volume acumulado ÷ semanas cadastradas |

---

## Tecnologias e conceitos

- **Java 17+**
- **Maven**
- **JUnit 5** (testes)
- **VS Code** + Extension Pack for Java
- `Scanner`
- `BufferedReader` / `Files.newBufferedReader`
- `BufferedWriter` / `Files.newBufferedWriter` (modo append)
- Vetores (`double[]`, `String[]`)
- Matrizes (`double[][]`)
- `if / else`, `switch`, `for`, `while`, `do-while`
- Métodos `static` com e sem retorno
- `enum` (`StatusTalhao`)
- `List` / `ArrayList`
- `try / catch`
- `split()`, `trim()`, `replace()`, `String.join()`
- `Integer.parseInt()` / `Double.parseDouble()`
- `printf` / `String.format`
- `LocalDate` / `DateTimeFormatter` (validação e data do relatório)

---

## Como executar

### Requisitos

- **JDK 17** ou superior instalado.
- **Maven** (ou o VS Code com a extensão *Extension Pack for Java*, que já roda projetos Maven).

Para conferir a instalação:

```bash
java -version
mvn -version
```

### Pelo VS Code

1. Abra a pasta **`parte2/demo`** no VS Code (*File > Open Folder*). Não abra a raiz do repositório, porque cada parte tem seu próprio `pom.xml`.
2. Abra `src/main/java/com/example/App.java` para o sistema completo, ou qualquer classe `RFxx` para um exercício isolado.
3. Clique em **Run**, acima do método `main`.

> Os arquivos são gravados em `dados/`, dentro da pasta aberta. Abra sempre a pasta `parte2/demo` para o histórico continuar no mesmo lugar.

### Pelo terminal (Maven)

Entre na pasta do projeto:

```bash
cd parte2/demo
```

Execute o sistema completo:

```bash
mvn compile exec:java
```

Execute um exercício isolado (exemplo com o RF01):

```bash
mvn compile exec:java -Dexec.mainClass=com.example.chuvas.RF01RegistroChuvas
```

> No PowerShell, coloque o parâmetro entre aspas: `"-Dexec.mainClass=com.example.chuvas.RF01RegistroChuvas"`.

### Pelo terminal (sem Maven)

No PowerShell, dentro da pasta `parte2/demo`:

```powershell
javac -encoding UTF-8 -d target/classes (Get-ChildItem -Recurse src/main/java -Filter *.java).FullName
java -cp target/classes com.example.App
```

---

## Testes

Cada requisito tem um teste próprio em `src/test/java`, com **29 testes** no total.

Rodar todos os testes:

```bash
mvn test
```

Rodar o teste de um exercício só:

```bash
mvn test -Dtest=RF09ClassificarTalhoesTest
```

---

## Estrutura

```
parte2/
├── README.md
└── demo/
    ├── pom.xml
    ├── dados/                              → criada ao rodar o sistema
    │   ├── historico_chuvas.csv
    │   ├── mapa_umidade.csv
    │   └── relatorio_fazenda.txt
    └── src/
        ├── main/java/com/example/
        │   ├── App.java                    → menu principal com todos os requisitos
        │   ├── chuvas/
        │   │   ├── RF01RegistroChuvas.java     → vetor de 7 dias, média e dia mais chuvoso
        │   │   ├── SemanaChuva.java            → uma semana de chuva e sua linha no CSV
        │   │   ├── RF04RegistrarSemana.java    → grava a semana no histórico
        │   │   ├── RF05ConsultarHistorico.java → lê e exibe o histórico
        │   │   ├── RF06SemanaMaisChuvosa.java  → semana com maior total
        │   │   └── RF07DashboardClimatico.java → indicadores climáticos
        │   ├── talhoes/
        │   │   ├── RF02MapaUmidade.java        → matriz 4x4, mapa e alertas
        │   │   ├── RF03MenuSafra.java          → menu de safra
        │   │   ├── RF08SalvarMapaUmidade.java  → salva e carrega o mapa em CSV
        │   │   ├── StatusTalhao.java           → Crítico, Atenção e Adequado
        │   │   ├── RF09ClassificarTalhoes.java → classificação de cada talhão
        │   │   ├── RF10RelatorioIrrigacao.java → talhões que precisam de ação
        │   │   ├── RF11RelatorioGerencial.java → indicadores da fazenda
        │   │   └── RF12ExportarRelatorio.java  → grava o relatório em .txt
        │   └── util/
        │       ├── Entrada.java                → leitura validada do teclado
        │       └── Formatador.java             → formatação dos números
        └── test/java/com/example/
            ├── chuvas/                         → testes do RF01, RF04 a RF07 e SemanaChuva
            └── talhoes/                        → testes do RF02, RF03 e RF08 a RF12
```

---

## Objetivo

Projeto desenvolvido com fins acadêmicos para praticar **lógica de programação e algoritmos em Java**,
com foco em **arrays, matrizes, persistência em arquivos e relatórios**, mantendo a rastreabilidade
entre cada requisito do enunciado e a classe que o implementa.
