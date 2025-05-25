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
### ✅ Atividade 1 — Integração de Actions Externas

Nesta etapa, o pipeline da aplicação **DevCalc** foi aprimorado com a integração de duas *actions* externas do GitHub Actions Marketplace:

- [`github/codeql-action`](https://github.com/github/codeql-action): utilizada para **análise de segurança estática** do código Java.
- [`maven-checkstyle-plugin`](https://maven.apache.org/plugins/maven-checkstyle-plugin/): utilizada para **verificação de estilo e padronização de código**.

Ambas foram adicionadas ao arquivo `ci.yml` como *jobs* separados, com dependências encadeadas utilizando a instrução `needs:`.

O *job* `codeql-analysis` foi configurado para inicializar e executar a análise com base no código Java compilado. Já o *job* `lint-check` executa o comando `mvn site`, gerando um relatório HTML localizado em `target/site/checkstyle.html` com os resultados do Checkstyle.

A execução dos dois *jobs* foi validada na aba **Actions** do GitHub, e os resultados foram registrados com sucesso, comprovando a correta integração das ferramentas ao pipeline.


### ✅ Atividade 3 — Separação do Pipeline com Workflows Reutilizáveis

Nesta etapa, o pipeline da aplicação **DevCalc** foi refatorado para utilizar **workflows reutilizáveis**, promovendo modularização e melhor organização do código de automação.

Foi criado um novo arquivo chamado `.github/workflows/lint-and-test.yml`, contendo dois *jobs*:

- `test`: responsável por executar os testes automatizados com Maven.
- `lint-check`: executa o plugin Checkstyle e gera um relatório HTML com a análise de estilo do código.

O workflow principal `ci.yml` foi ajustado para chamar este workflow reutilizável por meio da diretiva:

```yaml
uses: ./.github/workflows/lint-and-test.yml
```


### ✅Atividade 4 — Execução Manual com Parâmetros Booleanos

Nesta etapa, o workflow principal da aplicação **DevCalc** foi aprimorado para permitir a **execução manual** diretamente pela interface do GitHub Actions, com suporte a **parâmetros booleanos configuráveis**.

Foram adicionados dois parâmetros na instrução `workflow_dispatch`:

- `run_tests`: define se os testes automatizados devem ser executados.
- `run_lint`: define se o linting com Checkstyle deve ser executado.

Esses parâmetros são utilizados dentro do workflow reutilizável `lint-and-test.yml` por meio de expressões condicionais `if: ${{ inputs.run_tests }}` e `if: ${{ inputs.run_lint }}`. Isso permite controlar dinamicamente quais *jobs* devem ser executados, tornando o pipeline mais flexível e inteligente.

A funcionalidade foi validada com sucesso ao executar o workflow manualmente, escolhendo diferentes combinações de valores para os parâmetros. A execução refletiu corretamente as escolhas feitas, demonstrando a adaptabilidade do pipeline.

### ✅ Atividade 5 — Provocando e Corrigindo um Erro no Pipeline

Nesta etapa, foram provocadas falhas intencionais no pipeline da aplicação **DevCalc** com o objetivo de exercitar a depuração e correção de workflows no GitHub Actions. Os erros incluíram:

- Referência incorreta à versão de uma action (`github/codeql-action@v4`, inexistente até o momento).
- Omissão obrigatória do parâmetro `distribution` na action `setup-java`.
- Um erro proposital no job `build` com o comando `exit 1`.

Após a execução do pipeline com falha, a análise foi realizada por meio da aba **Actions** do GitHub, utilizando os logs detalhados de cada job para localizar a origem dos erros. As correções foram aplicadas com base nas mensagens de erro, ajustando as versões e parâmetros esperados pelas actions. Em seguida, foi feito um novo push e a execução do pipeline foi validada com sucesso. Essa atividade contribuiu para o desenvolvimento de habilidades de depuração, leitura de logs e correção de problemas reais em pipelines de integração contínua.
