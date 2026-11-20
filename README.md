# AgroJava

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)](https://www.oracle.com/java/)
[![Status](https://img.shields.io/badge/Status-Concluído-success?style=for-the-badge)](https://github.com/)
[![License](https://img.shields.io/badge/License-Acadêmico-blue?style=for-the-badge)](https://github.com/)

Sistema desenvolvido em **Java** para monitoramento de dados agrícolas, com foco no controle de **chuva semanal** e **umidade do solo em talhões**.

## Partes do projeto

| Parte | Conteúdo | Onde está |
|:-----:|----------|-----------|
| **1** | Marcos (RF01 a RF03): chuva semanal em vetor, umidade em matriz 4x4 e menu | esta página e a pasta [`demo/`](demo/) |
| **2** | Evoluções AgroJavaAnalytics e AgroJavaSmartFarm (RF01 a RF12): histórico em CSV, dashboard, classificação dos talhões, relatórios e testes | ➡️ [`parte2/`](parte2/README.md) |

## Sobre o projeto

O AgroJava permite cadastrar e analisar informações de uma propriedade agrícola por meio de uma aplicação executada no terminal.

O sistema possui:

* Cadastro do volume de chuva durante os 7 dias da semana;
* Cadastro da umidade de 16 talhões em uma matriz 4x4;
* Visualização do mapa de umidade;
* Cálculo da média semanal de chuva;
* Identificação do dia com maior volume de chuva;
* Alertas para talhões com umidade abaixo de 30%.

## Tecnologias

* Java
* Scanner
* Arrays
* Matrizes bidimensionais
* Estruturas condicionais
* Estruturas de repetição

## Funcionamento

O sistema apresenta um menu interativo:

```text
=========================
   SISTEMA AGROJAVA
=========================
1) Cadastrar Dados
2) Exibir Mapa do Campo
3) Relatório de Alertas
4) Sair
```

Após o cadastro, o usuário pode visualizar os dados dos talhões e gerar um relatório com os principais indicadores.

## Exemplo de alerta

```text
Alerta de Irrigação (Umidade abaixo de 30%):

-> Talhão da Linha 1, Coluna 3 está com 28.0% de umidade!
-> Talhão da Linha 3, Coluna 1 está com 27.5% de umidade!
```

## Como executar

Clone o repositório:

```bash
git clone https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git
```

Compile o projeto:

```bash
javac -d . AgroJava.java
```

Execute:

```bash
java com.example.AgroJava
```

## Estrutura

```text
AgroJava/
├── src/
│   └── com/
│       └── example/
│           └── AgroJava.java
└── README.md
```
