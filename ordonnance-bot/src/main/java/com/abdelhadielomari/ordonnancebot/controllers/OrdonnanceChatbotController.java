package com.abdelhadielomari.ordonnancebot.controllers;

import com.abdelhadielomari.ordonnancebot.agents.OrdonnanceAIAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdonnanceChatbotController {

    @Autowired
    private OrdonnanceAIAgent ordonnanceAIAgent;

    @GetMapping("/chat")
    public String chat(
            @RequestParam(name="query", defaultValue="Bonjour") String query,
            @RequestParam(name="conversationId", defaultValue="default") String conversationId) {

        return ordonnanceAIAgent.chat(query,conversationId);
    }
}
