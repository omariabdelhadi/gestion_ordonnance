package com.abdelhadielomari.ordonnancebot.config;

import io.modelcontextprotocol.client.transport.customizer.McpSyncHttpClientRequestCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpClientConfig {

    @Bean
    public McpSyncHttpClientRequestCustomizer authCustomizer() {
        return (requestBuilder, method, uri, body, context) -> {
            // Ajoute le token d'authentification à chaque requête MCP
            requestBuilder.header("Authorization", "Bearer TOKEN_ICI");
        };
    }
}
