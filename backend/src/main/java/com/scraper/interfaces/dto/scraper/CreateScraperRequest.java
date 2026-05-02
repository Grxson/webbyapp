package com.scraper.interfaces.dto.scraper;

import com.scraper.domain.enums.ScraperType;
import lombok.Data;
import java.util.Map;

@Data
public class CreateScraperRequest {
    private String name;
    private String description;
    private ScraperType type;
    private String targetUrl;
    private Map<String, Object> config;
    private String cronExpression;
    private boolean proxyEnabled;
    private boolean jsRender;
    private Integer rateLimitMs;
}