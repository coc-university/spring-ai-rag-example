package com.codecampn.spring.ai.rag.example.api;

import com.codecampn.spring.ai.rag.example.business.RagService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StartupRunner implements CommandLineRunner {

    private final RagService ragService;

    @Override
    public void run(String... args) {
        //ragService.saveDocuments();
    }
}
