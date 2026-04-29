# Transporte API

API REST para rastreamento de encomendas, inspirada no modelo dos Correios. Permite criar encomendas, registrar movimentações e consultar o histórico completo de uma encomenda pelo código de rastreio.

## Deploy

A API está disponível em produção:

```
https://transporte-api-production-df09.up.railway.app
```

A documentação interativa com Swagger está disponível em:

```
https://transporte-api-production-df09.up.railway.app/swagger-ui.html
```

---

## Tecnologias

- Java 21
- Spring Boot 4.0.5
- Spring Data JPA
- PostgreSQL
- Lombok
- Swagger
- Railway

---

## Endpoints

### Criar encomenda

```
POST /encomendas
```

**Request body:**
```json
{
  "remetente": "João",
  "destinatario": "Maria"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "codigoDeRastreio": "A25ACAEE",
  "dataEntrega": null,
  "dataCriacao": "2026-04-29T04:12:37Z",
  "statusEncomenda": "COLETADO",
  "destinatario": "Maria",
  "remetente": "João"
}
```

---

### Buscar encomenda pelo código de rastreio

```
GET /encomendas/{codigo}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "codigoDeRastreio": "A25ACAEE",
  "dataEntrega": null,
  "dataCriacao": "2026-04-29T04:12:37Z",
  "statusEncomenda": "EM_TRANSITO",
  "destinatario": "Maria",
  "remetente": "João"
}
```

---

### Registrar evento (movimentação)

```
POST /encomendas/{codigo}/eventos
```

**Request body:**
```json
{
  "codigoDeRastreio": "A25ACAEE",
  "cidade": "Fortaleza",
  "statusEncomenda": "EM_TRANSITO"
}
```

**Status disponíveis:** `COLETADO`, `EM_TRANSITO`, `ENTREGUE`

> Encomendas com status `ENTREGUE` não aceitam novos eventos. A data de entrega é preenchida automaticamente ao marcar como entregue.

**Response (201 Created):**
```json
{
  "id": 1,
  "statusEncomenda": "EM_TRANSITO",
  "cidadeHistorico": "Fortaleza",
  "dataEHoraHistorico": "2026-04-29T05:00:00Z"
}
```

---

### Consultar histórico de eventos

```
GET /encomendas/{codigo}/eventos
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "statusEncomenda": "COLETADO",
    "cidadeHistorico": "Natal",
    "dataEHoraHistorico": "2026-04-29T04:12:37Z"
  },
  {
    "id": 2,
    "statusEncomenda": "EM_TRANSITO",
    "cidadeHistorico": "Fortaleza",
    "dataEHoraHistorico": "2026-04-29T05:00:00Z"
  }
]
```

---

## Rodando localmente

**Pré-requisitos:** Java 21, Maven, PostgreSQL

**1. Clone o repositório:**
```bash
git clone https://github.com/seu-usuario/transporte-api.git
cd transporte-api
```

**2. Crie o banco de dados:**
```sql
CREATE DATABASE transporte_db;
```

**3. Configure as variáveis de ambiente** criando o arquivo `src/main/resources/application.properties`:
```properties
DATABASE_URL=jdbc:postgresql://localhost:5432/transporte_db
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=sua_senha
```

**4. Suba o projeto:**
```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## Arquitetura

O projeto segue arquitetura em camadas com separação clara de responsabilidades:

```
entity       → modelos JPA mapeados para o banco
repository   → interface com o banco via Spring Data JPA
dto          → contratos de entrada e saída da API
infrastructure → tratamento global de exceções
service      → lógica de negócio
controller   → endpoints REST
```
