package com.scraper.interfaces.dto.export;

import lombok.Data;
import java.util.Map;

@Data
public class CreateExportRequest {
    private String format;
    private Long scraperId;
    private Map<String, Object> filters;
}