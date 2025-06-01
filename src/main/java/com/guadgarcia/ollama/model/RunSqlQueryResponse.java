package com.guadgarcia.ollama.model;

import lombok.Data;

@Data
public class RunSqlQueryResponse {
    private Object result;
    private String error;
}
