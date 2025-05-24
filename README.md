# DevCalc API

A DevCalc é uma API REST escrita em Java que oferece operações matemáticas simples como adição, subtração, multiplicação e divisão. O projeto será utilizado para testar pipelines de CI/CD com GitHub Actions.

## Ferramenta de build
Este projeto utiliza o **Apache Maven** para gerenciamento de dependências e build.

## Como executar localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/devcalc-api.git
   cd devcalc-api



## Registros referentes a SEGUNDA ETAPA do projeto
### ✅Registro da Atividade 1 — Integração de Actions Externas

Nesta etapa, o pipeline da aplicação DevCalc foi aprimorado com a integração de duas actions externas do GitHub Actions Marketplace: o github/codeql-action, para análise de segurança estática do código Java, e o maven-checkstyle-plugin, para verificação de estilo e padronização de código. Ambas foram adicionadas ao arquivo ci.yml como jobs separados, com dependências encadeadas utilizando a instrução needs:. O job codeql-analysis foi configurado para inicializar e executar a análise com base no código Java compilado, enquanto o job lint-check executa o plugin maven site, gerando um relatório HTML com os resultados do Checkstyle. A execução dos dois jobs foi validada na aba “Actions” do GitHub, e os resultados foram registrados com sucesso, comprovando a correta integração das ferramentas ao pipeline.