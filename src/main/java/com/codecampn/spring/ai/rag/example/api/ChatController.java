package com.codecampn.spring.ai.rag.example.api;

import com.codecampn.spring.ai.rag.example.business.ChatService;
import com.codecampn.spring.ai.rag.example.business.RagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class ChatController {

    private final RagService ragService;
    private final ChatService chatService;

    @GetMapping("/")
    public ChatResponseDto chat(@RequestParam String query) {
        UserMessage userMessage = new UserMessage(query);
        log.info("User Message query: \n \n" + query + "\n");

        Message systemMessage = ragService.loadSimilarDocumentsAndCreateSystemMessage(query);

        ChatResponseDto dto = chatService.chatWithLlm(systemMessage, userMessage);

        log.info("Answer: \n \n" + dto.getAnswer());
        return dto;
    }
}
