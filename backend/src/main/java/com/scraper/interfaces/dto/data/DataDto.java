package com.scraper.interfaces.dto.data;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class DataDto {
    private Long id;
    private Map<String, Object> data;
    private String sourceUrl;
    private LocalDateTime scrapedAt;
    private Long scraperId;
    private Long jobId;
}