package com.guadgarcia.ollama.service;

import com.guadgarcia.ollama.model.RunSqlQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class OllamaService {

    private static final String PROMPT_TEMPLATE_PATH_FORMAT = "classpath:prompts/%s";
    private static final String SYSTEM_PROMPT_FILE_NAME = "systemTextToSql.md";
    private static final String USER_PROMPT_FILE_NAME = "userTextToSql.md";
    private static final String USER_PROMPT_QUESTION_KEY = "question";

    private final ChatClient chatClient;
    private final ResourceLoader resourceLoader;
    private final ToolCallback sqlQueryToolCallback;

    public OllamaService(ChatClient.Builder chatClientBuilder, ResourceLoader resourceLoader,
                         ToolCallback sqlQueryToolCallback) {
        this.chatClient = chatClientBuilder.build();
        this.resourceLoader = resourceLoader;
        this.sqlQueryToolCallback = sqlQueryToolCallback;
    }

    public RunSqlQueryResponse query(String question) {
        var userPrompt = buildUserPrompt(question);
        var systemPrompt = getResource(SYSTEM_PROMPT_FILE_NAME);

        return chatClient.prompt(userPrompt)
                .system(systemPrompt)
                .toolCallbacks(sqlQueryToolCallback)
                .advisors(new SimpleLoggerAdvisor())
                .call().entity(RunSqlQueryResponse.class);
    }

    private Prompt buildUserPrompt(String question) {
        var userPromptResource = getResource(USER_PROMPT_FILE_NAME);
        var variablesMap = Map.<String, Object>of(USER_PROMPT_QUESTION_KEY, question);

        return PromptTemplate.builder()
                .resource(userPromptResource)
                .variables(variablesMap)
                .build().create();
    }

    private Resource getResource(String fileName) {
        var resourceLocation = String.format(PROMPT_TEMPLATE_PATH_FORMAT, fileName);
        return resourceLoader.getResource(resourceLocation);
    }
}
