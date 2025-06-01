package com.guadgarcia.ollama.model;

import lombok.Data;

@Data
public class RunSqlQueryRequest {
    private String sql;
}
