package com.scraper.interfaces.dto.scraper;

import lombok.Data;

@Data
public class ImportScraperRequest {
    private String content;
    private String format;
}