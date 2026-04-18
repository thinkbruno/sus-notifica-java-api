package com.bruno.esus.client;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class SusClient {

    /**
     * Lê o CSV local da pasta resources.
     * Mantem o retorno como Flux para não quebrar a assinatura do  Service.
     */
    public Flux<String> downloadDataStream() {
        try {
            Path path = new ClassPathResource("esus-dados.csv").getFile().toPath();
            String content = Files.readString(path);
            return Flux.just(content);
        } catch (Exception e) {
            return Flux.error(new RuntimeException("Erro ao ler arquivo CSV local: " + e.getMessage()));
        }
    }
}