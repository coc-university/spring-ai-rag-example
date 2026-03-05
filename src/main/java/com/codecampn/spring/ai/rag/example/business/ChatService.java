package com.codecampn.spring.ai.rag.example.business;

import com.codecampn.spring.ai.rag.example.api.ChatResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ChatService {

    private final ChatClient chatClient;

//    public ChatResponseDto chatWithLlm() {
//        String systemMessage = "you are a football manager";
//        String userMessage = "can you name two young players from germany";
//        return chatWithLlm(systemMessage, userMessage);
//    }

    public ChatResponseDto chatWithLlm(Message systemMessage, Message userMessage) {
        log.info("ask llm for chat response ... so let's wait some seconds");

        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        ChatResponse chatResponse = chatClient.call(prompt);
        String answer = chatResponse.getResult().getOutput().getContent();

        return new ChatResponseDto(systemMessage.getContent(), userMessage.getContent(), answer);
    }

//    public ChatResponseDto chatWithLlm(String systemMessage, String userMessage) {
//        Prompt prompt = new Prompt(List.of(new SystemMessage(systemMessage), new UserMessage(userMessage)));
//        log.info("ask llm for chat response ... so let's wait some seconds");
//        ChatResponse chatResponse = chatClient.call(prompt);
//        String answer = chatResponse.getResult().getOutput().getContent();
//        return new ChatResponseDto(systemMessage, userMessage, answer);
//    }
}
