package com.scraper.interfaces.rest;

import com.scraper.application.port.in.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public ResponseEntity<?> getAllData(
            @RequestParam(required = false) Long scraperId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(dataService.getAllData(null, scraperId, page, size));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteData(
            @RequestParam Long scraperId,
            @RequestBody List<Long> dataIds) {
        dataService.deleteData(null, scraperId, dataIds);
        return ResponseEntity.noContent().build();
    }
}