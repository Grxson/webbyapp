package com.scraper.application.port.in;

import com.scraper.domain.ScraperDefinition;
import com.scraper.interfaces.dto.scraper.*;
import java.util.List;

public interface ScraperService {
    List<ScraperDefinition> getAllScrapers(Long userId);
    ScraperDefinition getScraperById(Long id, Long userId);
    ScraperDefinition createScraper(CreateScraperRequest request, Long userId);
    ScraperDefinition updateScraper(Long id, UpdateScraperRequest request, Long userId);
    void deleteScraper(Long id, Long userId);
    ScraperDefinition toggleScraper(Long id, Long userId);
    TestScraperResponse testScraper(Long id, Long userId);
    ScraperDefinition importScraper(ImportScraperRequest request, Long userId);
}