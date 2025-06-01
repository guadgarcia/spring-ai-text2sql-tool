package com.guadgarcia.ollama.controller;

import com.guadgarcia.ollama.model.RunSqlQueryResponse;
import com.guadgarcia.ollama.service.OllamaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ollama")
@RequiredArgsConstructor
public class OllamaController {

    private final OllamaService ollamaService;

    @GetMapping("/query")
    public RunSqlQueryResponse query(@RequestParam(value = "question") String question) {
        return ollamaService.query(question);
    }

}
