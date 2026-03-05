package com.codecampn.spring.ai.rag.example.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RagService {

    @Value("classpath:/data/footballer.json")
    private Resource footballerJson;

    @Value("classpath:/prompts/footballer-prompt.st")
    private Resource footballerPrompt;

    private final VectorStore vectorStore;

    public RagService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void saveDocuments() {
        JsonReader jsonReader = new JsonReader(footballerJson, "name", "age ", "team", "country");
        List<Document> documents = jsonReader.get();
        log.info("ask llm for embeddings and store them in db ... so let's wait some seconds");
        vectorStore.accept(documents);
        log.info("saved all {} documents", documents.size());
    }

    public Message loadSimilarDocumentsAndCreateSystemMessage(String query) {
        SearchRequest searchRequest = SearchRequest
                .query(query)                   // use question of user
                .withTopK(2)                    // result count
                .withSimilarityThreshold(0.1);  // a little bit similar
        // TODO: check search, because there is still a problem (response: 1x germany, 1x england)
        List<Document> similarDocuments = vectorStore.similaritySearch(searchRequest);
        String documents = similarDocuments
                .stream()
                .map(Document::getContent)
                .collect(Collectors.joining("\n"));

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(footballerPrompt);
        log.info("System Message documents: \n \n" + documents);
        return systemPromptTemplate.createMessage(Map.of("documents", documents));
    }
}
