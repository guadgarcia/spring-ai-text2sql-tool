package com.guadgarcia.ollama.config;

import com.guadgarcia.ollama.model.RunSqlQueryRequest;
import com.guadgarcia.ollama.tool.SqlQueryTool;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ToolsConfiguration {

    @Bean
    SqlQueryTool sqlQueryTool(JdbcTemplate jdbcTemplate) {
        return new SqlQueryTool(jdbcTemplate);
    }

    @Bean
    ToolCallback sqlQueryToolCallback(SqlQueryTool sqlQueryTool) {
        return FunctionToolCallback
                .builder("runSqlQuery", sqlQueryTool)
                .description("Run SQL query against database")
                .inputType(RunSqlQueryRequest.class)
                .build();
    }

}
