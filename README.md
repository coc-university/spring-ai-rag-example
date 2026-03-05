# Spring AI RAG Example

## ToDo:

- this project uses an old version of spring-ai and has to be updated
- but the general idea is still the same
- with newer versions (e.g. 1.1.2) you can use Advisors to embed RAG in the ChatClient
- so you do not have to call vectorStore.similaritySearch() manually
- e.g. QuestionAnswerAdvisor + SearchRequest
- e.g. chatClient.defaultAdvisor(qaAdvisor)

## Based on:

- https://github.com/rd-1-2022/ai-azure-retrieval-augmented-generation/tree/main
- https://github.com/markpollack/spring-ai-carina-faq/tree/main
- https://github.com/coffee-software-show/llm-rag-with-spring-ai/tree/main
- https://github.com/klindziukp/spring-ai-ollama-llama
- so mainly oriented on repo's of mark pollack, spring-ai project lead

## In general:

- this project uses snapshot dependencies (work in progress)
- so they will definitely change in the future
- check the following:
  - [Snapshot Artifactory](https://repo.spring.io/ui/native/snapshot/org/springframework/ai/)
  - [Milestone Artifactory](https://repo.spring.io/ui/native/milestone/org/springframework/ai/)
  - [Release Calendar](https://calendar.spring.io/)
- maybe adjust pom.xml

## Setup:

- the whole project can run locally without an api-key
- choose your vectorStore in DbConfig (Postgres or in-mem Db)
- run ```docker-compose up``` (ollama and postgres)
- and then ```docker exec -it ollama ollama run llama2``` [check out docker-hub](https://hub.docker.com/r/ollama/ollama)
- use StartupRunner to fill the database

## Run project:

- trigger ChatController to query db for similar documents 
- service layer will stuff the prompt
- and ask llm for answer of your question


## Dependencies and Flowchart: 
- [spring-ai-rag-example.drawio.pdf](spring-ai-rag-example.drawio.pdf)
 