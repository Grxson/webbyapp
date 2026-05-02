package com.scraper.application.port.in;

import com.scraper.domain.ExportTask;
import com.scraper.interfaces.dto.export.*;
import java.util.List;

public interface ExportService {
    List<ExportTask> getAllExports(Long userId);
    ExportTask getExportById(Long id, Long userId);
    ExportTask createExport(CreateExportRequest request, Long userId);
    byte[] downloadExport(Long id, Long userId);
}