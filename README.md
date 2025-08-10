# Projeto Microsserviços - Sistema de Pedidos e Catálogo

Este projeto é um desafio proposto durante o **bootcamp NTT DATA - Java e IA para Iniciantes** na plataforma **DIO**,  
que implementa um sistema simples de microsserviços para gerenciamento de produtos e pedidos.

Consiste em:
- **Microsserviço de catálogo de produtos**
- **Microsserviço de pedidos**
- Integrados via **API REST** e acessados por um **API Gateway** com interface web.

---

## Funcionalidades

### **Microsserviço 1 - Catálogo de Produtos**
- Listar produtos
- Criar novos produtos
- Excluir produtos existentes  
  Cada produto possui:
  - **ID**
  - **Nome**
  - **Descrição**
  - **Preço**

### **Microsserviço 2 - Pedidos**
- Criar pedidos enviando um array de IDs de produtos selecionados.
- Registro do pedido no microsserviço de pedidos.

### **API Gateway - Interface Web**
- Páginas: **Home**, **Catálogo**, **Pedidos**
- Visualização da lista de produtos
- Seleção de produtos via checkboxes
- Criação de pedidos com produtos selecionados

---

## Tecnologias Utilizadas
- **Java 21** + Spring Boot
- **Eureka Server** (descoberta de serviços)
- **H2 Database** (em memória)
- **HTML + CSS + JavaScript** (frontend)
- **Fetch API** para comunicação frontend ↔ backend
- **Maven** (build e dependências)
- **PowerShell Script** para start automático dos serviços

---

## Como Executar

### Pré-requisitos
- Java 21 instalado e configurado no PATH
- Maven instalado
- PowerShell (Windows) para executar o script

### Passos
1. **Clonar o repositório**
```console
git clone https://github.com/JZLZ/catalogo-pedidos_desafioDIO.git
cd catalogo-pedidos_desafioDIO
```

2. **Executar o script para iniciar todos os microsserviços**  
   No PowerShell (Windows):
```console
.run-all.ps1
```
Esse script inicia:
- **catalog-service**
- **order-service**
- **Eureka Server**
  na ordem correta.

3. **Acessar no navegador**
```
http://localhost:8799
```
Você verá a página inicial com links para:
- **Spring Eureka**
- **Catálogo de Produtos**
- **Lista de Pedidos**

---

## Principais Endpoints

### **Produtos**
- `GET` → `http://localhost:8799/produtos/produtos` — Lista todos os produtos.
- `POST` → `http://localhost:8799/produtos/createProduct` — Cria um novo produto (JSON: nome, descrição, preço).
- `DELETE` → `http://localhost:8799/produtos/delete/{id}` — Remove produto pelo ID.

### **Pedidos**
- `POST` → `http://localhost:8799/pedidos/pedidos` — Cria um pedido (JSON: `{"ids":[id1,id2,...]}`).

---

## Exemplos de Uso com `curl`

💡 **Observação:** Ajuste os IDs de acordo com os produtos cadastrados no seu ambiente.

```console
# Criar produto
curl -X POST http://localhost:8799/produtos/createProduct -H "Content-Type: application/json" -d "{\"nome\":\"Mouse Logitech\", \"descricao\":\"Mouse USB\", \"preco\":150.00}"
```
> Cria um produto com nome, descrição e preço.

```console
# Deletar produto
curl -X DELETE http://localhost:8799/produtos/delete/145
```
> Remove o produto com ID `145`.

```console
# Criar pedido com 2 produtos
curl -X POST http://localhost:8799/pedidos/pedidos -H "Content-Type: application/json" -d "{\"ids\":[5,41]}"
```
> Cria um pedido contendo os produtos de ID `5` e `41`.

---

## Estrutura do Projeto
```
catalog-service/   → Microsserviço de produtos
order-service/     → Microsserviço de pedidos
eureka-server/     → Servidor Eureka
frontend/          → HTML, CSS e JS (interface web)
run-all.ps1        → Script para start automático
```

---

## 📫 Contato
- **LinkedIn:** [Luiz Amorim](https://www.linkedin.com/in/jzlz)
- **GitHub:** [Luiz Amorim](https://github.com/jzlz)

Desenvolvido por **Luiz Amorim** para o **Desafio Técnico - Microsserviços** da **NTT DATA**.
