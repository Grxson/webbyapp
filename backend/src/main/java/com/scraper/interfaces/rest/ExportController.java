package com.scraper.interfaces.rest;

import com.scraper.application.port.in.ExportService;
import com.scraper.interfaces.dto.export.CreateExportRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/export")
public class ExportController {

    private final ExportService exportService;

    public ExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    @GetMapping
    public ResponseEntity<?> getAllExports() {
        return ResponseEntity.ok(exportService.getAllExports(null));
    }

    @PostMapping
    public ResponseEntity<?> createExport(@RequestBody CreateExportRequest request) {
        return ResponseEntity.ok(exportService.createExport(request, null));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadExport(@PathVariable Long id) {
        byte[] data = exportService.downloadExport(id, null);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=export.csv")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(data);
    }
}