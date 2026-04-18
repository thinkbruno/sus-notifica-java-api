# sus-notifica-java-api

Backend desenvolvido em **Java 17** e **Kotlin** para consumo e tratamento de registros do ecossistema e-SUS NOTIFICA.

## Mudança de Abordagem: Dados Abertos
Originalmente projetado para a API Restful do e-SUS, o projeto foi adaptado para consumir o **Portal de Dados Abertos da Saúde**. Esta mudança visa facilitar o acesso a grandes volumes de dados sem as barreiras de autenticação complexas da API de vigilância em tempo real.

## Uso de CSV Local (Mocking)
Atualmente, a aplicação consome um arquivo CSV localizado em `src/main/resources/esus-dados.csv`.
**Motivos desta decisão:**
1. **Resiliência de Rede:** Evita falhas de resolução de DNS (ex: `s3.saude.gov.br`) comuns em ambientes de desenvolvimento locais.
2. **Velocidade de Teste:** Permite validar a lógica de parsing e normalização de datas instantaneamente.
3. **Segurança de Dados:** Utiliza dados controlados para garantir que o tratamento de campos como `registroAtual` e `resultadoTeste` funcione conforme o manual técnico.

##  Tecnologias
- **Java 17 / Kotlin 1.9**
- **Spring Boot 3.2** (WebFlux para streaming de dados)
- **Apache Commons CSV** (Parsing de alta performance com delimitador `;`)
- **Dotenv** (Gestão de variáveis de ambiente)

## Como Testar
1. Certifique-se de que o arquivo `.env` está configurado.
2. Execute a aplicação via `SusApplication.kt`.
3. Utilize o Postman para acessar:
   `GET http://localhost:8080/api/opendata/fetch?limit=30`