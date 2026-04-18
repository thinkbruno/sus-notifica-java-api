# sus-notifica-java-api

Backend para integração com o ecossistema **e-SUS NOTIFICA** (Módulo Covid-19) do Ministério da Saúde.

## Tecnologias
- Java 17 / Kotlin 1.9
- Spring Boot 3.2 (Web, Validation)
- Maven
- Elasticsearch (Protocolo RESTful)

## Arquitetura
O projeto utiliza uma abordagem híbrida, aproveitando a segurança de tipos e *data classes* do Kotlin para modelos de dados, e Java para a camada de serviços e clientes HTTP.

## Configuração
Renomeie o arquivo `.env.example` para `.env` e preencha as credenciais de acesso fornecidas pelo DATASUS conforme o [Manual de Integração](https://datasus.saude.gov.br/wp-content/uploads/2022/02/Manual-de-Utilizacao-da-API-e-Sus-Notifica.pdf).