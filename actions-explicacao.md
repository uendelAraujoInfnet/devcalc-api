# 📘 Diferença entre Workflows e Actions

## ✅ Workflows

Um **workflow** é um conjunto de instruções declaradas em um arquivo `.yml`, localizado em `.github/workflows/`. Ele define **quando** e **como** uma sequência de ações deve ser executada no GitHub Actions.

Exemplo:
- Executar testes ao fazer push na branch `main`
- Rodar análise de código em pull requests
- Executar manualmente um deploy

## ✅ Actions

Uma **action** é uma tarefa reutilizável, como um bloco de código encapsulado, que pode ser chamada dentro de um workflow para realizar uma ação específica (como instalar Java, fazer checkout do repositório ou rodar testes).

Existem três tipos:
- Actions oficiais do GitHub (ex: `actions/checkout`)
- Actions da comunidade (ex: `codeql-action`)
- Actions personalizadas (criadas pela própria equipe)

---

# 🧬 Estrutura Interna de uma Action

Uma action é geralmente definida por um repositório próprio e deve conter um arquivo chamado `action.yml` (ou `action.yaml`), que especifica:

- `name`: nome da action
- `description`: o que ela faz
- `inputs`: parâmetros que ela pode receber
- `outputs`: resultados gerados
- `runs`: qual comando ou linguagem a action executa

### Exemplo resumido de um `action.yml`:

```yaml
name: Hello World
description: Exibe Hello World
inputs:
  name:
    description: Nome a ser saudado
    required: true
    default: Mundo
runs:
  using: "node12"
  main: "index.js"
