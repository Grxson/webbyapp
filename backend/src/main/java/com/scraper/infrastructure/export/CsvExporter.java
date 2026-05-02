package com.scraper.infrastructure.export;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public class CsvExporter {

    private final CsvMapper csvMapper = new CsvMapper();

    public byte[] export(List<Map<String, Object>> data) throws Exception {
        if (data.isEmpty()) {
            return new byte[0];
        }

        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        Map<String, Object> firstRow = data.get(0);
        for (String key : firstRow.keySet()) {
            schemaBuilder.addColumn(key);
        }

        CsvSchema schema = schemaBuilder.build().withHeader();
        return csvMapper.writer(schema).writeValueAsBytes(data);
    }
}