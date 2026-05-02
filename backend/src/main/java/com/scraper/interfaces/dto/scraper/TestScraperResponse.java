package com.scraper.interfaces.dto.scraper;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class TestScraperResponse {
    private boolean success;
    private String message;
    private Map<String, Object> data;
    private Integer itemsScraped;
}