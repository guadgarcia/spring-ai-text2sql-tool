package com.guadgarcia.ollama.tool;

import com.guadgarcia.ollama.model.RunSqlQueryRequest;
import com.guadgarcia.ollama.model.RunSqlQueryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
@Slf4j
public class SqlQueryTool implements Function<RunSqlQueryRequest, RunSqlQueryResponse> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public RunSqlQueryResponse apply(RunSqlQueryRequest runSqlQueryRequest) {
        var runSqlQueryResponse = new RunSqlQueryResponse();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(runSqlQueryRequest.getSql())) {
            log.info("Running sql {}", runSqlQueryRequest.getSql());

            var result = convert(resultSet);
            log.info("Result from sql query {}", result);
            runSqlQueryResponse.setResult(result);
        } catch (Exception e) {
            log.error("Exception running sql {}", runSqlQueryRequest.getSql(), e);
            runSqlQueryResponse.setError(String.format("SQL error occurred for SQL query %s",
                    runSqlQueryRequest.getSql()));
        }
        return runSqlQueryResponse;
    }

    List<Map<String, Object>> convert(ResultSet resultSet) throws Exception {
        var list = new ArrayList<Map<String, Object>>();
        var metaData = resultSet.getMetaData();
        var columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            var row = new HashMap<String, Object>();
            for (var index = 1; index <= columnCount; index++) {
                row.put(metaData.getColumnName(index), resultSet.getObject(index));
            }
            list.add(row);
        }
        return list;
    }

}
