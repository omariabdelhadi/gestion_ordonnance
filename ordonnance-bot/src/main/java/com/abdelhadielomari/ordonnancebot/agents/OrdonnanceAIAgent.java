package com.abdelhadielomari.ordonnancebot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;


@Service
public class OrdonnanceAIAgent {
    private ChatClient chatClient;


    public OrdonnanceAIAgent(ChatClient.Builder chatClient, ChatMemory chatMemory, ToolCallbackProvider tools) {

        this.chatClient = chatClient
                .defaultSystem("""
                        si la demande ou la question de client n'est pas liée a les ordonnances ou les medicals ou les patients, alors tu doit reponde par je sait pas
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
