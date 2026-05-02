package com.scraper.infrastructure.scraping.engine;

import com.scraper.domain.ScraperDefinition;
import java.util.Map;

public interface ScraperEngine {
    String fetch(ScraperDefinition scraper, String proxyHost, int proxyPort) throws Exception;
    Map<String, Object> extractData(String html, ScraperDefinition scraper) throws Exception;
}