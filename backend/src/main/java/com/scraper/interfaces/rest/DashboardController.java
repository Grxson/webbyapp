package com.scraper.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(Map.of(
            "totalScrapers", 0,
            "activeJobs", 0,
            "totalDataItems", 0,
            "alerts", 0
        ));
    }

    @GetMapping("/recent-activity")
    public ResponseEntity<?> getRecentActivity() {
        return ResponseEntity.ok(java.util.List.of());
    }
}