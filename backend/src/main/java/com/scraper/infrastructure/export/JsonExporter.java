package com.scraper.infrastructure.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public class JsonExporter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public byte[] export(List<Map<String, Object>> data) throws Exception {
        return objectMapper.writeValueAsBytes(data);
    }
}