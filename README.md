# DevCalc API

![CI](https://github.com/uendelives/devcalc-api/actions/workflows/ci.yml/badge.svg)


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
Execução via push

### ✅ Observações sobre os Gatilhos de Execução

Durante a execução da atividade, foram testados dois tipos de gatilhos do GitHub Actions:

- **Execução automática via push:** o workflow foi disparado imediatamente após uma alteração no código-fonte da aplicação. Essa abordagem é ideal para garantir validações contínuas ao longo do desenvolvimento.
- **Execução manual via botão (workflow_dispatch):** permitiu controlar manualmente quais partes do pipeline deveriam ser executadas por meio de parâmetros booleanos (`run_tests` e `run_lint`). Essa opção é útil em situações específicas, como execuções parciais ou verificações de código isoladas.

Ambos os gatilhos funcionaram conforme esperado, permitindo flexibilidade e automação no processo de integração contínua.


## 🚀 Teste de Performance 3 (TP3) - DevCalc

Este terceiro Teste de Performance aprofunda o controle e a segurança do pipeline DevCalc, consolidando conhecimentos em GitHub Actions com foco em runners auto-hospedados, gestão segura de variáveis, escopos de ambiente, permissões e ambientes separados de deploy.

---

### ✅ Etapa 1 - Configuração de Runner Auto-Hospedado

Um runner auto-hospedado foi configurado com sucesso em uma máquina local. Foi criado o workflow `runner-local.yml`, que executa:

```yaml
runs-on: self-hosted
steps:
  - name: Exibir SO e Java
    run: |
      echo "OS: $Env:OS"
      java -version

  - name: Instalar 7-Zip (via choco)
    run: choco install 7zip -y

  - name: Verificar instalação do 7-Zip
    shell: powershell
    run: |
      if (Get-Command "7z" -ErrorAction SilentlyContinue) {
        Write-Output "7z encontrado!"
      } else {
        Write-Output "7z não encontrado."
      }
```

---

### ✅ Etapa 2 - Uso de Variáveis e Secrets no Workflow

Foram criadas variáveis e secrets no repositório:
- `APP_MODE = production`
- `SUPPORT_EMAIL = suporte@devcalc.com`
- `PROD_TOKEN = abc123xyz`

Workflow `variaveis-e-secrets.yml` utilizou:

```yaml
${{ vars.APP_MODE }}
${{ vars.SUPPORT_EMAIL }}
${{ secrets.PROD_TOKEN }}
```

---

### ✅ Etapa 3 - Contextos e Escopos de Variáveis de Ambiente

O workflow `env-context-demo.yml` demonstrou o uso de escopos:
- `env:` em nível de **workflow**, **job** e **step**
- Impressão de:
  - `github.actor`
  - `runner.os`
  - `STAGE`

> 🧠 Resultado: escopos sobrepostos corretamente (`step > job > workflow`)

---

### ✅ Etapa 4 - Permissões e Uso do GITHUB_TOKEN

Workflow `criar-issue-condicional.yml`:
- Define `permissions: issues: write`
- Usa o `GITHUB_TOKEN` para abrir uma issue se `PROD_TOKEN` estiver ausente.

> 🛡️ Simulação de alerta automatizado em pipelines

---

### ✅ Etapa 5 - Deploy com Ambientes Dev e Prod

Ambientes criados:
- **dev**: deploy automático
- **prod**: deploy com aprovação manual

Configuração do workflow `deploy-ambientes.yml`:

```yaml
jobs:
  deploy-dev:
    if: github.ref == 'refs/heads/dev'
    environment: dev

  deploy-prod:
    if: github.ref == 'refs/heads/main'
    environment: prod
```

---

### ✅ Etapa 6 - Implementação do endpoint `/sqrt`

Implementado novo endpoint na API: `GET /sqrt?x=16`

**CalculatorService.java**:
```java
public double sqrt(double x) {
  if (x < 0) throw new IllegalArgumentException("Negativo");
  return Math.sqrt(x);
}
```

**CalculatorController.java**:
```java
get("/sqrt", ctx -> {
  double x = Double.parseDouble(ctx.queryParam("x"));
  ctx.result(String.valueOf(calculatorService.sqrt(x)));
});
```

**Testes:**
```java
@Test
void testSqrtComValorPositivo() {
  assertEquals(4.0, service.sqrt(16), 0.0001);
}

@Test
void testSqrtComValorNegativo() {
  assertThrows(IllegalArgumentException.class, () -> service.sqrt(-9));
}
```

> ✅ Testes passaram no CI  

---

### 📂 Evidências

Todos os prints, logs e execuções estão salvos em:

```
/evidencias/TP3/
```

---

### 📌 Status Final

✅ TP3 concluído com sucesso, pipeline profissional, seguro e com documentação completa.

---
