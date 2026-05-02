package com.scraper.interfaces.rest;

import com.scraper.application.port.in.ScraperService;
import com.scraper.interfaces.dto.scraper.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scrapers")
public class ScraperController {

    private final ScraperService scraperService;

    public ScraperController(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllScrapers() {
        return ResponseEntity.ok(scraperService.getAllScrapers(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getScraperById(@PathVariable Long id) {
        return ResponseEntity.ok(scraperService.getScraperById(id, null));
    }

    @PostMapping
    public ResponseEntity<?> createScraper(@RequestBody CreateScraperRequest request) {
        return ResponseEntity.ok(scraperService.createScraper(request, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateScraper(@PathVariable Long id, @RequestBody UpdateScraperRequest request) {
        return ResponseEntity.ok(scraperService.updateScraper(id, request, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScraper(@PathVariable Long id) {
        scraperService.deleteScraper(id, null);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/toggle")
    public ResponseEntity<?> toggleScraper(@PathVariable Long id) {
        return ResponseEntity.ok(scraperService.toggleScraper(id, null));
    }

    @PostMapping("/{id}/test")
    public ResponseEntity<?> testScraper(@PathVariable Long id) {
        return ResponseEntity.ok(scraperService.testScraper(id, null));
    }
}