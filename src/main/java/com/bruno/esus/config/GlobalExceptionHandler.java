package com.bruno.esus.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Captura erros 401 e 403 da API do SUS
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<Map<String, String>> handleUnauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Falha na autenticação com o DATASUS. Verifique as credenciais no .env."));
    }

    // Captura erros de busca (ex: JSON mal formatado enviado ao Elasticsearch)
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(HttpClientErrorException.BadRequest ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", "Erro na requisição enviada ao e-SUS: " + ex.getResponseBodyAsString()));
    }

    // Erro genérico para falhas inesperadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Erro interno no servidor: " + ex.getMessage()));
    }
}