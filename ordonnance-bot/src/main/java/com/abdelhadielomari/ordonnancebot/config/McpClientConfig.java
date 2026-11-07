/*
package com.abdelhadielomari.ordonnancebot.config;

import io.modelcontextprotocol.client.transport.customizer.McpSyncHttpClientRequestCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class McpClientConfig {

    @Bean
    public McpSyncHttpClientRequestCustomizer authCustomizer() {
        return (requestBuilder, method, uri, body, context) -> {

            // 1. On récupère la requête HTTP actuelle (celle qui vient d'Angular)
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attributes != null) {
                // 2. On extrait le header "Authorization" (qui contient "Bearer eyJ...")
                String authHeader = attributes.getRequest().getHeader("Authorization");

                // 3. Si le header existe, on le transmet à la requête MCP
                if (authHeader != null && !authHeader.isEmpty()) {
                    requestBuilder.header("Authorization", authHeader);
                }
            }
        };
    }
}*/
