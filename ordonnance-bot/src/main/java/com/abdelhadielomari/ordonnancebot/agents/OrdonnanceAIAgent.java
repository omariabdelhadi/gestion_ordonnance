package com.abdelhadielomari.ordonnancebot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;


@Service
public class OrdonnanceAIAgent {
    // H
    private ChatClient chatClient;


    public OrdonnanceAIAgent(ChatClient.Builder chatClient, ChatMemory chatMemory, ToolCallbackProvider tools) {

        this.chatClient = chatClient
                .defaultSystem("""
                        Utilise les outils mis à ta disposition pour répondre. N'invente jamais de données.
                        """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()).defaultTools(tools)
                .build();

    }


    public String chat(String query, String conversationId) {

        return chatClient.prompt(query)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }


}
